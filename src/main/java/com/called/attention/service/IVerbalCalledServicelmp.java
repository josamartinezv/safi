package com.called.attention.service;

import com.called.attention.domain.ProgramNumber;
import com.called.attention.domain.Users;
import com.called.attention.domain.VerbalCalled;
import com.called.attention.domain.enumeration.PhaseVerbalCalled;
import com.called.attention.domain.enumeration.TypeVerbalCalled;
import com.called.attention.repository.ProgramNumberRepository;
import com.called.attention.repository.UsersRepository;
import com.called.attention.repository.VerbalCalledRepository;
import com.called.attention.service.dto.VerbalCalled.VerbalCalledDTO;
import com.called.attention.service.dto.VerbalCalled.VerbalCalledFilterDocumentDTO;
import com.called.attention.service.dto.VerbalCalled.VerbalCalledFiltersDTO;
import com.called.attention.service.transformer.VerbalCalled.VerbalCalledFilterTransformer;
import com.called.attention.service.transformer.VerbalCalled.VerbalCalledTransformer;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.naming.NameNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class IVerbalCalledServicelmp implements IVerbalCalledService {

    @Autowired
    ProgramNumberRepository programNumberRepository;

    @Autowired
    VerbalCalledRepository verbalCalledRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    EmailService emailService;

    @Override
    public Page<VerbalCalledDTO> read(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return verbalCalledRepository.findAll(pageable)
                .map(VerbalCalledTransformer::getVerbalCalledDTOFromVerbalCalled);
    }

    @Override
    public void delete(Integer idVerbalCalled) {
        verbalCalledRepository.deleteById(idVerbalCalled);
    }

    @Override
    public Optional<VerbalCalledDTO> getById(Integer idVerbalCalled) {
        Optional<VerbalCalled> verbalCalled = verbalCalledRepository.findById(idVerbalCalled);
        return verbalCalled.map(VerbalCalledTransformer::getVerbalCalledDTOFromVerbalCalled);
    }

    @Override
    public VerbalCalledDTO update(VerbalCalledDTO verbalCalledDTO) {
        verbalCalledDTO.setDateCalled(LocalDateTime.now());
        VerbalCalled verbalCalled = verbalCalledRepository.save(VerbalCalledTransformer.getVerbalCalledFromVerbalCalledDTO(verbalCalledDTO));
        verbalCalledDTO.setInstructor(usersRepository.findById(verbalCalledDTO.getInstructor().getIdUsers()).get());
        //use sendEmail


        emailService.sendEmailVerbalCalledUpdate(verbalCalledDTO);

        return VerbalCalledTransformer.getVerbalCalledDTOFromVerbalCalled(verbalCalledRepository.save(verbalCalled));


    }


    @Override
    public ResponseEntity create(VerbalCalledDTO verbalCalledDTO) {
        verbalCalledDTO.setDateCalled(LocalDateTime.now());
        VerbalCalled verbalCalled = verbalCalledRepository.save(VerbalCalledTransformer.getVerbalCalledFromVerbalCalledDTO(verbalCalledDTO));
        verbalCalledDTO.setAprentice(usersRepository.findById(verbalCalledDTO.getAprentice().getIdUsers()).get());
        verbalCalledDTO.setInstructor(usersRepository.findById(verbalCalledDTO.getInstructor().getIdUsers()).get());

        //use sendMail


        emailService.sendEmailVerbalCalled(verbalCalledDTO);

        return new ResponseEntity<>(VerbalCalledTransformer.getVerbalCalledDTOFromVerbalCalled(verbalCalledRepository.save(verbalCalled)), HttpStatus.OK);
    }



    private void sendEmail(String to, String cc, String subject, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setCc(cc);
        msg.setSubject(subject);
        msg.setText(text);

        //send mail
        javaMailSender.send(msg);
    }

    public Page<VerbalCalledFilterDocumentDTO> getByfilterDocument(String documentNumberAprentice, Integer pageSize, Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Users users = usersRepository.findUsersByDocumentNumber(documentNumberAprentice).get();
        if (users != null) {
            return verbalCalledRepository.findByAprenticeOrderByDateCalledDesc(users, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalByDocumentDTO);
        }
        return null;
    }


    //users = aprendiz
    //users1 = instructor

    @Override
    public Page<VerbalCalledFiltersDTO> getByFilters(String documentNumber, String documentNumberInstructor, String number, Boolean state, TypeVerbalCalled typeVerbalCalled, PhaseVerbalCalled phaseVerbalCalled, Integer pageSize, Integer pageNumber) throws ObjectNotFoundException {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Users users = usersRepository.findUsersByDocumentNumber(documentNumber).orElse(null);
        ProgramNumber programNumber = programNumberRepository.findByNumber(number).orElse(null);
        Users users1 = usersRepository.findUsersByDocumentNumber(documentNumberInstructor).orElse(null);

        if (users1 != null && phaseVerbalCalled != null && users != null && programNumber != null && state != null && typeVerbalCalled != null) {
            return verbalCalledRepository.findByInstructorAndPhaseVerbalCalledAndAprenticeAndProgramNumberAndTypeVerbalCalledAndStateOrderByDateCalledDesc(users1, phaseVerbalCalled, users, programNumber,  typeVerbalCalled, state,pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users1 != null && phaseVerbalCalled != null && users != null && programNumber != null && typeVerbalCalled != null) {
            return verbalCalledRepository.findByInstructorAndPhaseVerbalCalledAndAprenticeAndProgramNumberAndTypeVerbalCalledOrderByDateCalledDesc(users1, phaseVerbalCalled, users, programNumber, typeVerbalCalled, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users1 != null && phaseVerbalCalled != null && programNumber != null && state != null && typeVerbalCalled != null) {
            return verbalCalledRepository.findByInstructorAndPhaseVerbalCalledAndProgramNumberAndTypeVerbalCalledAndStateOrderByDateCalledDesc(users1, phaseVerbalCalled, programNumber,  typeVerbalCalled, state, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users1 != null && phaseVerbalCalled != null && programNumber != null && typeVerbalCalled != null) {
            return verbalCalledRepository.findByInstructorAndPhaseVerbalCalledAndProgramNumberAndTypeVerbalCalledOrderByDateCalledDesc(users1, phaseVerbalCalled, programNumber, typeVerbalCalled, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users1 != null && phaseVerbalCalled != null && users != null && state != null && typeVerbalCalled != null) {
            return verbalCalledRepository.findByInstructorAndPhaseVerbalCalledAndAprenticeAndTypeVerbalCalledAndStateOrderByDateCalledDesc(users1, phaseVerbalCalled, users, typeVerbalCalled, state, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users1 != null && phaseVerbalCalled != null && users != null && programNumber != null && state != null) {
            return verbalCalledRepository.findByInstructorAndPhaseVerbalCalledAndAprenticeAndProgramNumberAndStateOrderByDateCalledDesc(users1, phaseVerbalCalled, users, programNumber, state, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users1 != null && phaseVerbalCalled != null && users != null && programNumber != null) {
            return verbalCalledRepository.findByInstructorAndPhaseVerbalCalledAndAprenticeAndProgramNumberOrderByDateCalledDesc(users1, phaseVerbalCalled, users, programNumber, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users1 != null && phaseVerbalCalled != null && programNumber != null && state != null) {
            return verbalCalledRepository.findByInstructorAndPhaseVerbalCalledAndProgramNumberAndStateOrderByDateCalledDesc(users1, phaseVerbalCalled, programNumber, state, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users1 != null && phaseVerbalCalled != null && users != null && typeVerbalCalled != null) {
            return verbalCalledRepository.findByInstructorAndPhaseVerbalCalledAndAprenticeAndTypeVerbalCalledOrderByDateCalledDesc(users1, phaseVerbalCalled, users, typeVerbalCalled, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users1 != null && phaseVerbalCalled != null && state != null && typeVerbalCalled != null) {
            return verbalCalledRepository.findByInstructorAndPhaseVerbalCalledAndTypeVerbalCalledAndStateOrderByDateCalledDesc(users1, phaseVerbalCalled,  typeVerbalCalled, state, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users1 != null && users != null && programNumber != null && state != null && typeVerbalCalled != null) {
            return verbalCalledRepository.findByInstructorAndAprenticeAndProgramNumberAndTypeVerbalCalledAndStateOrderByDateCalledDesc(users1, users, programNumber, typeVerbalCalled, state, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users1 != null && programNumber != null && state != null && typeVerbalCalled != null) {
            return verbalCalledRepository.findByInstructorAndProgramNumberAndTypeVerbalCalledAndStateOrderByDateCalledDesc(users1, programNumber,  typeVerbalCalled, state,pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users1 != null && phaseVerbalCalled != null && typeVerbalCalled != null) {
            return verbalCalledRepository.findByInstructorAndPhaseVerbalCalledAndTypeVerbalCalledOrderByDateCalledDesc(users1, phaseVerbalCalled, typeVerbalCalled, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users1 != null && users != null && state != null && typeVerbalCalled != null) {
            return verbalCalledRepository.findByInstructorAndAprenticeAndTypeVerbalCalledAndStateOrderByDateCalledDesc(users1, users,  typeVerbalCalled,state, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users1 != null && users != null && programNumber != null && state != null) {
            return verbalCalledRepository.findByInstructorAndAprenticeAndProgramNumberAndStateOrderByDateCalledDesc(users1, users, programNumber, state, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users1 != null && users != null && programNumber != null && typeVerbalCalled != null) {
            return verbalCalledRepository.findByInstructorAndAprenticeAndProgramNumberAndTypeVerbalCalledOrderByDateCalledDesc(users1, users, programNumber, typeVerbalCalled, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users1 != null && programNumber != null && typeVerbalCalled != null) {
            return verbalCalledRepository.findByInstructorAndProgramNumberAndTypeVerbalCalledOrderByDateCalledDesc(users1, programNumber, typeVerbalCalled, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users1 != null && state != null && typeVerbalCalled != null) {
            return verbalCalledRepository.findByInstructorAndTypeVerbalCalledAndStateOrderByDateCalledDesc(users1, typeVerbalCalled, state,  pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users1 != null && users != null && typeVerbalCalled != null) {
            return verbalCalledRepository.findByInstructorAndAprenticeAndTypeVerbalCalledOrderByDateCalledDesc(users1, users, typeVerbalCalled, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users1 != null && typeVerbalCalled != null) {
            return verbalCalledRepository.findByInstructorAndTypeVerbalCalledOrderByDateCalledDesc(users1, typeVerbalCalled, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users1 != null && phaseVerbalCalled != null && users != null && state != null) {
            return verbalCalledRepository.findByInstructorAndPhaseVerbalCalledAndAprenticeAndStateOrderByDateCalledDesc(users1, phaseVerbalCalled, users, state, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users1 != null && phaseVerbalCalled != null && state != null) {
            return verbalCalledRepository.findByInstructorAndPhaseVerbalCalledAndStateOrderByDateCalledDesc(users1, phaseVerbalCalled, state, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users1 != null && users != null && state != null) {
            return verbalCalledRepository.findByInstructorAndAprenticeAndStateOrderByDateCalledDesc(users1, users, state, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users1 != null && users != null && programNumber != null) {
            return verbalCalledRepository.findByInstructorAndAprenticeAndProgramNumberOrderByDateCalledDesc(users1, users, programNumber, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users1 != null && programNumber != null && state != null) {
            return verbalCalledRepository.findByInstructorAndProgramNumberAndStateOrderByDateCalledDesc(users1, programNumber, state, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users1 != null && state != null) {
            return verbalCalledRepository.findByInstructorAndStateOrderByDateCalledDesc(users1, state, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users1 != null && phaseVerbalCalled != null && programNumber != null) {
            return verbalCalledRepository.findByInstructorAndPhaseVerbalCalledAndProgramNumberOrderByDateCalledDesc(users1, phaseVerbalCalled, programNumber, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users1 != null && programNumber != null) {
            return verbalCalledRepository.findByInstructorAndProgramNumberOrderByDateCalledDesc(users1, programNumber, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users1 != null && phaseVerbalCalled != null && users != null) {
            return verbalCalledRepository.findByInstructorAndPhaseVerbalCalledAndAprenticeOrderByDateCalledDesc(users1, phaseVerbalCalled, users, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users1 != null && users != null) {
            return verbalCalledRepository.findByInstructorAndAprenticeOrderByDateCalledDesc(users1, users, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users1 != null && phaseVerbalCalled != null) {
            return verbalCalledRepository.findByInstructorAndPhaseVerbalCalledOrderByDateCalledDesc(users1, phaseVerbalCalled, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (phaseVerbalCalled != null && users != null && programNumber != null && state != null && typeVerbalCalled != null) {
            return verbalCalledRepository.findByPhaseVerbalCalledAndAprenticeAndProgramNumberAndTypeVerbalCalledAndStateOrderByDateCalledDesc(phaseVerbalCalled, users, programNumber,  typeVerbalCalled, state,pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (phaseVerbalCalled != null && users != null && programNumber != null && typeVerbalCalled != null) {
            return verbalCalledRepository.findByPhaseVerbalCalledAndAprenticeAndProgramNumberAndTypeVerbalCalledOrderByDateCalledDesc(phaseVerbalCalled, users, programNumber, typeVerbalCalled, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (phaseVerbalCalled != null && programNumber != null && state != null && typeVerbalCalled != null) {
            return verbalCalledRepository.findByPhaseVerbalCalledAndProgramNumberAndTypeVerbalCalledAndStateOrderByDateCalledDesc(phaseVerbalCalled, programNumber, typeVerbalCalled, state, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (phaseVerbalCalled != null && users != null && state != null && typeVerbalCalled != null) {
            return verbalCalledRepository.findByPhaseVerbalCalledAndAprenticeAndTypeVerbalCalledAndStateOrderByDateCalledDesc(phaseVerbalCalled, users, typeVerbalCalled,  state, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (phaseVerbalCalled != null && users != null && typeVerbalCalled != null) {
            return verbalCalledRepository.findByPhaseVerbalCalledAndAprenticeAndTypeVerbalCalledOrderByDateCalledDesc(phaseVerbalCalled, users, typeVerbalCalled, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (phaseVerbalCalled != null && programNumber != null && typeVerbalCalled != null) {
            return verbalCalledRepository.findByPhaseVerbalCalledAndProgramNumberAndTypeVerbalCalledOrderByDateCalledDesc(phaseVerbalCalled, programNumber, typeVerbalCalled, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (phaseVerbalCalled != null && state != null && typeVerbalCalled != null) {
            return verbalCalledRepository.findByPhaseVerbalCalledAndTypeVerbalCalledAndStateOrderByDateCalledDesc(phaseVerbalCalled,  typeVerbalCalled, state, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (phaseVerbalCalled != null && typeVerbalCalled != null) {
            return verbalCalledRepository.findByPhaseVerbalCalledAndTypeVerbalCalledOrderByDateCalledDesc(phaseVerbalCalled, typeVerbalCalled, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (phaseVerbalCalled != null && users != null && programNumber != null && state != null) {
            return verbalCalledRepository.findByPhaseVerbalCalledAndAprenticeAndProgramNumberAndStateOrderByDateCalledDesc(phaseVerbalCalled, users, programNumber, state, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (phaseVerbalCalled != null && programNumber != null && state != null) {
            return verbalCalledRepository.findByPhaseVerbalCalledAndProgramNumberAndStateOrderByDateCalledDesc(phaseVerbalCalled, programNumber, state, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (phaseVerbalCalled != null && users != null && state != null) {
            return verbalCalledRepository.findByPhaseVerbalCalledAndAprenticeAndStateOrderByDateCalledDesc(phaseVerbalCalled, users, state, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (phaseVerbalCalled != null && state != null) {
            return verbalCalledRepository.findByPhaseVerbalCalledAndStateOrderByDateCalledDesc(phaseVerbalCalled, state, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (phaseVerbalCalled != null && users != null && programNumber != null) {
            return verbalCalledRepository.findByPhaseVerbalCalledAndAprenticeAndProgramNumberOrderByDateCalledDesc(phaseVerbalCalled, users, programNumber, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (phaseVerbalCalled != null && programNumber != null) {
            return verbalCalledRepository.findByPhaseVerbalCalledAndProgramNumberOrderByDateCalledDesc(phaseVerbalCalled, programNumber, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (phaseVerbalCalled != null && users != null) {
            return verbalCalledRepository.findByPhaseVerbalCalledAndAprenticeOrderByDateCalledDesc(phaseVerbalCalled, users, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users != null && programNumber != null && state != null && typeVerbalCalled != null) {
            return verbalCalledRepository.findByAprenticeAndProgramNumberAndTypeVerbalCalledAndStateOrderByDateCalledDesc(users, programNumber, typeVerbalCalled, state,  pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users != null && programNumber != null && state != null) {
            return verbalCalledRepository.findByAprenticeAndProgramNumberAndStateOrderByDateCalledDesc(users, programNumber, state, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users != null && programNumber != null && typeVerbalCalled != null) {
            return verbalCalledRepository.findByAprenticeAndProgramNumberAndTypeVerbalCalledOrderByDateCalledDesc(users, programNumber, typeVerbalCalled, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users != null && state != null && typeVerbalCalled != null) {
            return verbalCalledRepository.findByAprenticeAndTypeVerbalCalledAndStateOrderByDateCalledDesc(users, typeVerbalCalled, state, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users != null && programNumber != null) {
            return verbalCalledRepository.findByAprenticeAndProgramNumberOrderByDateCalledDesc(users, programNumber, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users != null && state != null) {
            return verbalCalledRepository.findByAprenticeAndStateOrderByDateCalledDesc(users, state, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users != null && typeVerbalCalled != null) {
            return verbalCalledRepository.findByAprenticeAndTypeVerbalCalledOrderByDateCalledDesc(users, typeVerbalCalled, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (programNumber != null && state != null && typeVerbalCalled != null) {
            return verbalCalledRepository.findByProgramNumberAndTypeVerbalCalledAndStateOrderByDateCalledDesc(programNumber, typeVerbalCalled, state,  pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (programNumber != null && typeVerbalCalled != null) {
            return verbalCalledRepository.findByProgramNumberAndTypeVerbalCalledOrderByDateCalledDesc(programNumber, typeVerbalCalled, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (programNumber != null && state != null) {
            return verbalCalledRepository.findByProgramNumberAndStateOrderByDateCalledDesc(programNumber, state, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (state != null && typeVerbalCalled != null) {
            return verbalCalledRepository.findByTypeVerbalCalledAndStateOrderByDateCalledDesc(typeVerbalCalled, state,  pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users != null) {
            return verbalCalledRepository.findByAprenticeOrderByDateCalledDesc(users, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (programNumber != null) {
            return verbalCalledRepository.findByProgramNumberOrderByDateCalledDesc(programNumber, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (state != null) {
            return verbalCalledRepository.findByStateOrderByDateCalledDesc(state, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (typeVerbalCalled != null) {
            return verbalCalledRepository.findByTypeVerbalCalledOrderByDateCalledDesc(typeVerbalCalled, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (phaseVerbalCalled != null) {
            return verbalCalledRepository.findByPhaseVerbalCalledOrderByDateCalledDesc(phaseVerbalCalled, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        } else if (users1 != null) {
            return verbalCalledRepository.findByInstructorOrderByDateCalledDesc(users1, pageable)
                    .map(VerbalCalledFilterTransformer::getVerbalCalledByFiltersDTO);
        }
        else {
            return null;
        }
    }



}


/*
  Optional<Users> userinstructor = usersRepository.findById(verbalCalled.getInstructor().getIdUsers());
            Optional<Users> useraprentice = usersRepository.findById(verbalCalled.getAprentice().getIdUsers());

            sendEmail(
                    userinstructor.get().getEmail(),

                    "se diligencio un llamado de atencion",
                    "Cordial saludo instructor " + userinstructor.get().getName() + " " + userinstructor.get().getLastName(),
                    " Se ha registrado la actualizacion de un llamado de atencion por parte del aprendiz " +
                            useraprentice.get().getName() + " " + useraprentice.get().getLastName());

            Optional<Users> useraprentice1 = usersRepository.findById(verbalCalled.getAprentice().getIdUsers());
            Optional<Users> userinstructor2 = usersRepository.findById(verbalCalled.getInstructor().getIdUsers());


            sendEmail(
                    useraprentice1.get().getEmail(),

                    "se diligencio completamente llamado de atencion",
                    "Cordial saludo aprendiz " + useraprentice1.get().getName() + " " + useraprentice1.get().getLastName(),
                    " Se le ha registrado la actualizacion de un llamado de atencion con exito, hecho el dia " + verbalCalledDTO.getDateCalled() + " " +
                            "por parte del instructor " + userinstructor2.get().getName() + " " + userinstructor2.get().getLastName());
 */
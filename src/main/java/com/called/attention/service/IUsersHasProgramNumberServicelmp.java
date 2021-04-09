package com.called.attention.service;

import com.called.attention.domain.ProgramNumber;
import com.called.attention.domain.Users;
import com.called.attention.domain.UsersHasProgramNumber;
import com.called.attention.domain.enumeration.UsersCourseRol;
import com.called.attention.repository.ProgramNumberRepository;
import com.called.attention.repository.UsersHasProgramNumberRepository;
import com.called.attention.repository.UsersRepository;
import com.called.attention.service.dto.ProgramNumber.ProgramNumberDTO;
import com.called.attention.service.dto.UsersHasProgramNumber.*;
import com.called.attention.service.transformer.UsersHasProgramNumber.UsersHasProgramNumberCreateVerbalTransformer;
import com.called.attention.service.transformer.UsersHasProgramNumber.UsersHasProgramNumberFilterTransformer;
import com.called.attention.service.transformer.UsersHasProgramNumber.UsersHasProgramNumberGetTransformer;
import com.called.attention.service.transformer.UsersHasProgramNumber.UsersHasProgramNumberTransformer;
import com.called.attention.service.transformer.VerbalCalled.VerbalCalledFilterTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IUsersHasProgramNumberServicelmp implements IUsersHasProgramNumberService {

    @Autowired
    ProgramNumberRepository programNumberRepository;

    @Autowired
    UsersRepository usersRepository;


    @Autowired
    UsersHasProgramNumberRepository usersHasProgramNumberRepository;

    @Override
    public Page<UsersHasProgramDTO> read (Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return usersHasProgramNumberRepository.findAll(pageable)
                .map(UsersHasProgramNumberTransformer::getUserHasProgramDTOFromUsersHasProgram);
    }

    @Override
    public UsersHasProgramDTO create ( UsersHasProgramDTO usersHasProgramDTO ) {
        UsersHasProgramNumber usersHasProgramNumber = UsersHasProgramNumberTransformer.getUserHasProgramNumberFromUserHasProgramDTO(usersHasProgramDTO);
        return UsersHasProgramNumberTransformer.getUserHasProgramDTOFromUsersHasProgram(usersHasProgramNumberRepository.save(usersHasProgramNumber));
    }

    @Override
    public Page<UsersHasProgramNumberFilterProgramDTO> getUsersByFilterProgramNumber(String number, Integer pageSize, Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        ProgramNumber programNumber = programNumberRepository.findByNumber(number).get();
        if (programNumber != null) {
            return usersHasProgramNumberRepository.findByProgramNumberAndUsersCourseRol(programNumber, UsersCourseRol.apprentice, pageable)
                    .map(UsersHasProgramNumberGetTransformer::getUsersByFilterProgramNumberDTO);
        } return null;

    }

    @Override
    public Page<UsersHasProgramNumberFilterByDocument> getUsersByFilterDocumentNumber(String documentNumber, Integer pageSize, Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Users users = usersRepository.findUsersByDocumentNumber(documentNumber).get();
        if (users != null){
            return usersHasProgramNumberRepository.findByUsersAndUsersCourseRol(users, UsersCourseRol.apprentice, pageable)
                    .map(UsersHasProgramNumberGetTransformer::getUsersByFilterDocumentNumberDTO);
        }return null;

    }

    @Override
    public Page<UsersHasProgramFilterDTO> getByProgramNumberAndUsersAndUsersCourseRol (String number, String documentNumber, Integer pageSize, Integer pageNumber){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        ProgramNumber programNumber = programNumberRepository.findByNumber(number).orElse(null);
        Users users = usersRepository.findUsersByDocumentNumber(documentNumber).orElse(null);

        if (programNumber != null && users !=null){
            return usersHasProgramNumberRepository.findByProgramNumberAndUsersAndUsersCourseRol(programNumber, users, UsersCourseRol.apprentice, pageable)
                    .map(UsersHasProgramNumberFilterTransformer::getByProgramNumberAndUsersAndUsersCourseRol);

        }
        else if (programNumber!= null){
            return usersHasProgramNumberRepository.findByProgramNumberAndUsersCourseRol(programNumber, UsersCourseRol.apprentice, pageable)
                    .map(UsersHasProgramNumberFilterTransformer::getByProgramNumberAndUsersAndUsersCourseRol);

        }
        else if (users != null){
            return usersHasProgramNumberRepository.findByUsersAndUsersCourseRol(users, UsersCourseRol.apprentice, pageable)
                    .map(UsersHasProgramNumberFilterTransformer::getByProgramNumberAndUsersAndUsersCourseRol);
        }else {
            return usersHasProgramNumberRepository.findByProgramNumberAndUsersAndUsersCourseRol(programNumber, users, UsersCourseRol.apprentice, pageable)
                        .map(UsersHasProgramNumberFilterTransformer::getByProgramNumberAndUsersAndUsersCourseRol);
            }
        }

    @Override
    public Optional<UsersHasProgramNumberCreateVerbalDTO> findByUsers ( Users idUsers ) {
        return usersHasProgramNumberRepository.findByUsers(idUsers)
                .map(UsersHasProgramNumberCreateVerbalTransformer :: getUsersHasProgramNumberCreateVerbalDTOFromUsersHasProgramNumberCreateVerbal);
    }

    @Override
    public Optional <UsersHasProgramNumberFilterByDocument> getUsersByMyDocument(String documentNumber) {
        Users users = usersRepository.findUsersByDocumentNumber(documentNumber).get();
        if (users != null) {
            return usersHasProgramNumberRepository.findByUsersAndUsersCourseRol(users, UsersCourseRol.apprentice)
                    .map(UsersHasProgramNumberGetTransformer::getUsersByFilterDocumentNumberDTO);
        } else{
            return usersHasProgramNumberRepository.findByUsersAndUsersCourseRol(users, UsersCourseRol.apprentice)
                    .map(UsersHasProgramNumberGetTransformer::getUsersByFilterDocumentNumberDTO);
        }
    }




}

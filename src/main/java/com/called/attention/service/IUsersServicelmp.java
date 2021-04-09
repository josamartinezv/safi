package com.called.attention.service;

import com.called.attention.domain.DetailUser;
import com.called.attention.domain.Rols;
import com.called.attention.domain.Users;
import com.called.attention.repository.DetailUserRepository;
import com.called.attention.repository.RolRepository;
import com.called.attention.repository.UsersRepository;
import com.called.attention.service.dto.Users.UsersDTO;
import com.called.attention.service.dto.Users.UsersFilterDocumentDTO;
import com.called.attention.service.dto.Users.UsersLoginDTO;
import com.called.attention.service.transformer.Users.UsersFilterDocumentTransformer;
import com.called.attention.service.transformer.Users.UsersLoginTransformer;
import com.called.attention.service.transformer.Users.UsersTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IUsersServicelmp implements IUsersService {

    private Logger logger = LoggerFactory.getLogger(IUsersServicelmp.class);



    @Autowired
    UsersRepository usersRepository;

    @Autowired
    RolRepository rolRepository;

    @Autowired
    DetailUserRepository detailUserRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public Page<UsersDTO> read (Integer pageSize, Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Rols rols = rolRepository.findByDescription("ROLE_GUEST").get();
            return usersRepository.findByRolsOrderByIdUsersDesc(rols, pageable)
                    .map(UsersTransformer::getUsersDTOfromUsers);



    }


    @Override
    public UsersDTO create (UsersDTO usersDTO) {
        DetailUser detailUser = new DetailUser();
        detailUser.setName(usersDTO.getName());
        detailUser.setLastName(usersDTO.getLastName());
        detailUser.setAddress(usersDTO.getAddress());
        detailUser.setPhoneNumber(usersDTO.getPhoneNumber());
        detailUserRepository.save(detailUser);

        Users users = UsersTransformer.getUsersFromUsersDTO(usersDTO);
        users.setDocumentNumber(usersDTO.getDocumentNumber());
        users.setEmail(usersDTO.getEmail());
        users.setEmailSena(usersDTO.getEmailSena());
        users.setEnabled(false);
        Set<Rols> rols1 = new HashSet<>();
        rols1.add(rolRepository.findByDescription("ROLE_GUEST").get());
        users.setRols(rols1);
        users.setPassword(passwordEncoder.encode(usersDTO.getPassword()));
        users.setDetailUser(detailUser);

        usersRepository.save(users);
        emailService.sendEmailRegister(users);

        Rols rols = rolRepository.findByDescription("ROLE_ADMIN").get();
        ArrayList<Users> useradmin = usersRepository.findAllByRols(rols);
        useradmin.forEach( item -> emailService.sendEmailAdminNotify(item));

        return usersDTO;
    }

    @Override
    public UsersFilterDocumentDTO searchBydocument ( String documentNumber ) {
        Users users = usersRepository.findByDocumentNumber(documentNumber);
        UsersFilterDocumentDTO usersFilterDocumentDTO = UsersFilterDocumentTransformer.getUsersFilterDocumentDTO(users);
        return usersFilterDocumentDTO;
    }

    @Override
    public UsersDTO update (UsersDTO usersDTO){
        Users usersup = usersRepository.findById(usersDTO.getIdUsers()).get();

        Users users = UsersTransformer.getUsersFromUsersDTO(usersDTO);
        users.setPassword(usersup.getPassword());
        users.setEnabled(true);

        emailService.sendEmailConfirmation(users);
        return UsersTransformer.getUsersDTOfromUsers(usersRepository.save(users));
    }

    @Override
    public Optional<UsersDTO> getById(Long idUsers) {

        Optional<Users> users = usersRepository.findById(idUsers);
        return users.map(UsersTransformer::getUsersDTOfromUsers);
    }
    @Override
    public UsersLoginDTO getBydocument (String documentNumber ) throws UsernameNotFoundException {
        Users users = usersRepository.findByDocumentNumber(documentNumber);
        if (users == null) {
            logger.error("Error, usuario " + documentNumber + " no encontrado");
            throw new UsernameNotFoundException("Error, usuario " + documentNumber + " no encontrado");
        }

        UsersLoginDTO usersLoginDTO = UsersLoginTransformer.getUsersLoginDTOfromUser(users);
        ArrayList authorities = new ArrayList<String>();
        usersLoginDTO.getRols().forEach(item -> {
            authorities.add(item.getDescription());
        });
        usersLoginDTO.setAuthorities(authorities);
        return usersLoginDTO;
    }




}

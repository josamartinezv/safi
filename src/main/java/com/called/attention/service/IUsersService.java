package com.called.attention.service;

import com.called.attention.domain.Users;
import com.called.attention.service.dto.Users.UsersDTO;
import com.called.attention.service.dto.Users.UsersFilterDocumentDTO;
import com.called.attention.service.dto.Users.UsersLoginDTO;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;


public interface IUsersService {

    //Get - obtener - read
    public Page<UsersDTO> read (Integer pageSize, Integer pageNumber);

    //Post - guardar - create
    public UsersDTO create (UsersDTO usersDTO);

    //Get - obtener informacion por documento
    public UsersFilterDocumentDTO searchBydocument (String documentNumber);

    UsersLoginDTO getBydocument (String documentNumber) throws UsernameNotFoundException;

    public UsersDTO update(UsersDTO usersDTO);

    Optional<UsersDTO> getById(Long idUsers);









}

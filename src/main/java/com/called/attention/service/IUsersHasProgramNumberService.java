package com.called.attention.service;

import com.called.attention.domain.ProgramNumber;
import com.called.attention.domain.Users;
import com.called.attention.domain.UsersHasProgramNumber;
import com.called.attention.domain.enumeration.UsersCourseRol;
import com.called.attention.service.dto.UsersHasProgramNumber.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface IUsersHasProgramNumberService {

    //Get - obtener - read
    Page<UsersHasProgramDTO> read(Integer pageNumber, Integer pageSize);

    //Post - guardar - create
    public UsersHasProgramDTO create ( UsersHasProgramDTO usersHasProgramDTO );

    public Page<UsersHasProgramNumberFilterProgramDTO> getUsersByFilterProgramNumber(String number, Integer pageSize, Integer pageNumber);

    public Page<UsersHasProgramNumberFilterByDocument> getUsersByFilterDocumentNumber(String documentNumber, Integer pageSize, Integer pageNumber);

    public Page<UsersHasProgramFilterDTO> getByProgramNumberAndUsersAndUsersCourseRol (String number, String documentNumber, Integer pageSize, Integer pageNumber);

    public Optional<UsersHasProgramNumberCreateVerbalDTO> findByUsers ( Users idUsers );

    public Optional<UsersHasProgramNumberFilterByDocument> getUsersByMyDocument (String documentNumber);

}

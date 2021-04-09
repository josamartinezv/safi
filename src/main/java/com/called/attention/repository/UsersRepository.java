package com.called.attention.repository;

import com.called.attention.domain.Rols;
import com.called.attention.domain.Users;
import com.called.attention.domain.VerbalCalled;
import com.called.attention.service.dto.Users.UsersDTO;
import com.called.attention.service.dto.Users.UsersFilterNameDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findUsersByDocumentNumber(String documentNumber);

    //Consulta por documento para crear llamado de atencion
    Users findByDocumentNumber (String documentNumber);

    Page<Users> findByRols (Rols rols, Pageable pageable);

    ArrayList<Users> findAllByRols(Rols rols);









}

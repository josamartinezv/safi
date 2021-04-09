package com.called.attention.repository;

import com.called.attention.domain.ProgramNumber;
import com.called.attention.domain.Users;
import com.called.attention.domain.UsersHasProgramNumber;
import com.called.attention.domain.enumeration.UsersCourseRol;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface        UsersHasProgramNumberRepository extends JpaRepository<UsersHasProgramNumber, Long> {

    Page<UsersHasProgramNumber> findByProgramNumberAndUsersCourseRol ( ProgramNumber programNumber, UsersCourseRol usersCourseRol, Pageable pageable );

    Page<UsersHasProgramNumber> findByUsersAndUsersCourseRol ( Users users, UsersCourseRol usersCourseRol, Pageable pageable );

    Optional<UsersHasProgramNumber> findByUsersAndUsersCourseRol (Users users, UsersCourseRol usersCourseRol );


    Optional<UsersHasProgramNumber> findByUsers (Users idUsers);

    Page<UsersHasProgramNumber> findByProgramNumberAndUsersAndUsersCourseRol ( ProgramNumber programNumber, Users users, UsersCourseRol usersCourseRol, Pageable pageable);

}

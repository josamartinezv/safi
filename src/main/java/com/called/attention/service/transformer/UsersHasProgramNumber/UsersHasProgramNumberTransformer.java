package com.called.attention.service.transformer.UsersHasProgramNumber;

import com.called.attention.domain.UsersHasProgramNumber;
import com.called.attention.service.dto.UsersHasProgramNumber.UsersHasProgramDTO;

public class UsersHasProgramNumberTransformer {

    public static UsersHasProgramDTO getUserHasProgramDTOFromUsersHasProgram(UsersHasProgramNumber usersHasProgramNumber){
        if (usersHasProgramNumber == null){
            return null;
        }

        UsersHasProgramDTO dto = new UsersHasProgramDTO();

        dto.setIdCUsersHasProgramNumber(usersHasProgramNumber.getIdCUsersHasProgramNumber());
        dto.setProgramNumber(usersHasProgramNumber.getProgramNumber());
        dto.setUsers(usersHasProgramNumber.getUsers());
        dto.setUsersCourseRol(usersHasProgramNumber.getUsersCourseRol());

        return dto;
    }

    public static UsersHasProgramNumber getUserHasProgramNumberFromUserHasProgramDTO(UsersHasProgramDTO usersHasProgramDTO){
        if (usersHasProgramDTO == null){
            return null;
        }

        UsersHasProgramNumber usersHasProgramNumber = new UsersHasProgramNumber();

        usersHasProgramNumber.setIdCUsersHasProgramNumber(usersHasProgramDTO.getIdCUsersHasProgramNumber());
        usersHasProgramNumber.setProgramNumber(usersHasProgramDTO.getProgramNumber());
        usersHasProgramNumber.setUsers(usersHasProgramDTO.getUsers());
        usersHasProgramNumber.setUsersCourseRol(usersHasProgramDTO.getUsersCourseRol());

        return usersHasProgramNumber;

    }
}

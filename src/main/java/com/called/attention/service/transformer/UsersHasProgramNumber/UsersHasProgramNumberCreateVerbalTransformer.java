package com.called.attention.service.transformer.UsersHasProgramNumber;

import com.called.attention.domain.UsersHasProgramNumber;
import com.called.attention.service.dto.UsersHasProgramNumber.UsersHasProgramNumberCreateVerbalDTO;

public class UsersHasProgramNumberCreateVerbalTransformer {

    public static UsersHasProgramNumberCreateVerbalDTO getUsersHasProgramNumberCreateVerbalDTOFromUsersHasProgramNumberCreateVerbal ( UsersHasProgramNumber usersHasProgramNumber ) {
        if (usersHasProgramNumber == null) {
            return null;
        }

        UsersHasProgramNumberCreateVerbalDTO dto = new UsersHasProgramNumberCreateVerbalDTO();

        //set variables
        dto.setUsers(usersHasProgramNumber.getUsers());
        dto.setFullName(usersHasProgramNumber.getUsers().getDetailUser().getName() + ' ' + usersHasProgramNumber.getUsers().getDetailUser().getLastName());
        dto.setProgramNumber(usersHasProgramNumber.getProgramNumber());

        return dto;
    }
}

package com.called.attention.service.transformer.UsersHasProgramNumber;

import com.called.attention.domain.UsersHasProgramNumber;
import com.called.attention.service.dto.UsersHasProgramNumber.UsersHasProgramFilterDTO;

public class UsersHasProgramNumberFilterTransformer {

    public static UsersHasProgramFilterDTO getByProgramNumberAndUsersAndUsersCourseRol (UsersHasProgramNumber usersHasProgramNumber){
        if (usersHasProgramNumber == null){
            return null;
        }

        UsersHasProgramFilterDTO dto = new UsersHasProgramFilterDTO();
        dto.setIdUser(usersHasProgramNumber.getUsers().getIdUsers());
        dto.setDocumentNumber(usersHasProgramNumber.getUsers().getDocumentNumber());
        dto.setFullName(usersHasProgramNumber.getUsers().getDetailUser().getName()+' '+usersHasProgramNumber.getUsers().getDetailUser().getLastName());
        dto.setNumber(usersHasProgramNumber.getProgramNumber().getNumber());
        dto.setEmailSena(usersHasProgramNumber.getUsers().getEmailSena());
        dto.setPhoneNumber(usersHasProgramNumber.getUsers().getDetailUser().getPhoneNumber());

        return dto;
    }
}

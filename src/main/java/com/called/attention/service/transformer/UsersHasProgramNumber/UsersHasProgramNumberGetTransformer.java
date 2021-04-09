package com.called.attention.service.transformer.UsersHasProgramNumber;

import com.called.attention.domain.UsersHasProgramNumber;
import com.called.attention.service.dto.UsersHasProgramNumber.UsersHasProgramNumberFilterByDocument;
import com.called.attention.service.dto.UsersHasProgramNumber.UsersHasProgramNumberFilterProgramDTO;

public class UsersHasProgramNumberGetTransformer {

    public static UsersHasProgramNumberFilterProgramDTO getUsersByFilterProgramNumberDTO(UsersHasProgramNumber usersHasProgramNumber) {
        if (usersHasProgramNumber == null) {
            return null;
        }

        UsersHasProgramNumberFilterProgramDTO dto = new UsersHasProgramNumberFilterProgramDTO();

        dto.setIdUser(usersHasProgramNumber.getUsers().getIdUsers());
        dto.setDocumentNumber(usersHasProgramNumber.getUsers().getDocumentNumber());
        dto.setEmailSena(usersHasProgramNumber.getUsers().getEmailSena());
        dto.setNumber(usersHasProgramNumber.getProgramNumber().getNumber());
        dto.setPhoneNumber(usersHasProgramNumber.getUsers().getDetailUser().getPhoneNumber());
        dto.setFullName(usersHasProgramNumber.getUsers().getDetailUser().getName() + ' ' + usersHasProgramNumber.getUsers().getDetailUser().getLastName());

        return dto;

    }

    public static UsersHasProgramNumberFilterByDocument getUsersByFilterDocumentNumberDTO(UsersHasProgramNumber usersHasProgramNumber) {
        if (usersHasProgramNumber == null) {
            return null;
        }

        UsersHasProgramNumberFilterByDocument dto = new UsersHasProgramNumberFilterByDocument();

        dto.setIdUser(usersHasProgramNumber.getUsers().getIdUsers());
        dto.setDocumentNumber(usersHasProgramNumber.getUsers().getDocumentNumber());
        dto.setEmailSena(usersHasProgramNumber.getUsers().getEmailSena());
        dto.setNumber(usersHasProgramNumber.getProgramNumber().getNumber());
        dto.setPhoneNumber(usersHasProgramNumber.getUsers().getDetailUser().getPhoneNumber());
        dto.setFullName(usersHasProgramNumber.getUsers().getDetailUser().getName() + ' ' + usersHasProgramNumber.getUsers().getDetailUser().getLastName());
        dto.setNombreFicha(usersHasProgramNumber.getProgramNumber().getProgram().getName());

        return dto;
    }
}


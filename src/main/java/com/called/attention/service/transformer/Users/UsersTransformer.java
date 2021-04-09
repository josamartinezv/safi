package com.called.attention.service.transformer.Users;

import com.called.attention.domain.Users;
import com.called.attention.service.dto.Users.UsersDTO;

public class UsersTransformer {

    public static UsersDTO getUsersDTOfromUsers ( Users users ) {
        if (users == null) {
            return null;
        }
        UsersDTO dto = new UsersDTO();

        //set variables
        dto.setIdUsers(users.getIdUsers());
        dto.setDocumentNumber(users.getDocumentNumber());
        dto.setEmailSena(users.getEmailSena());
        dto.setEmail(users.getEmail());
        dto.setPassword(users.getPassword());
        dto.setEnabled(users.getEnabled());
        dto.setDetailUser(users.getDetailUser());
        dto.setRols(users.getRols());
        dto.setFullName(users.getDetailUser().getName() + " " + users.getDetailUser().getLastName());
        return dto;
    }

    public static Users getUsersFromUsersDTO ( UsersDTO dto ) {
        if (dto == null) {
            return null;
        }
        //create Object of Users

        Users users = new Users();

        users.setIdUsers(dto.getIdUsers());
        users.setDocumentNumber(dto.getDocumentNumber());
        users.setEmailSena(dto.getEmailSena());
        users.setEmail(dto.getEmail());
        users.setPassword(dto.getPassword());
        users.setEnabled(dto.getEnabled());
        users.setDetailUser(dto.getDetailUser());
        users.setRols(dto.getRols());
        return users;
    }
}

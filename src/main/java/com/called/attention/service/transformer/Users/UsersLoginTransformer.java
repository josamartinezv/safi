package com.called.attention.service.transformer.Users;

import com.called.attention.domain.Users;
import com.called.attention.service.dto.Users.UsersDTO;
import com.called.attention.service.dto.Users.UsersLoginDTO;

public class UsersLoginTransformer {

    public static UsersLoginDTO getUsersLoginDTOfromUser(Users users){
        if (users == null){
            return null;
        }
        UsersLoginDTO dto = new UsersLoginDTO();
        dto.setIdUsers(users.getIdUsers());
        dto.setEmailSena(users.getEmailSena());
        dto.setEmail(users.getEmail());



        dto.setEnabled(users.getEnabled());
        dto.setRols(users.getRols());
        dto.setDocumentNumber(users.getDocumentNumber());
        dto.setName(users.getDetailUser().getName());
        dto.setLastName(users.getDetailUser().getLastName());
        return dto;

    }
}

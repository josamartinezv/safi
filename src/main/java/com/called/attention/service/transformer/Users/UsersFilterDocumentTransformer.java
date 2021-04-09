package com.called.attention.service.transformer.Users;

import com.called.attention.domain.Users;
import com.called.attention.service.dto.Users.UsersFilterDocumentDTO;

public class UsersFilterDocumentTransformer {

    public static UsersFilterDocumentDTO getUsersFilterDocumentDTO ( Users users ) {
        if (users == null) {
            return null;
        }

        UsersFilterDocumentDTO dto = new UsersFilterDocumentDTO();

        dto.setIdUsers(users.getIdUsers());
        dto.setDocumentNumber(users.getDocumentNumber());
        dto.setName(users.getDetailUser().getName());
        dto.setLastName(users.getDetailUser().getLastName());

        return dto;
    }
}

package com.called.attention.service.transformer.Users;

import com.called.attention.domain.Users;
import com.called.attention.service.dto.Users.UsersFilterNameDTO;

public class UsersFilterNameTransformer {

    public static UsersFilterNameDTO getUsersFilterNameDTO (Users users ){
        if (users == null ){
            return null;
        }

        UsersFilterNameDTO dto = new UsersFilterNameDTO();

        dto.setIdUsers(users.getIdUsers());
        dto.setFullName(users.getDetailUser().getName() +' '+ users.getDetailUser().getLastName());

        return dto;
    }
}

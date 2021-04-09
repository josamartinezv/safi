package com.called.attention.service.transformer.DetailUserTransformer;

import com.called.attention.domain.DetailUser;
import com.called.attention.domain.Users;
import com.called.attention.service.dto.DetailUser.DetailUserDTO;
import com.called.attention.service.dto.Users.UsersDTO;

public class DetailUserTransformer {

    public static DetailUserDTO getDetailUserDTOfromDetailUser(DetailUser detailUser){
        if (detailUser == null) {
            return null;
        }
        DetailUserDTO dto = new DetailUserDTO();
        dto.setIdDetailUser(detailUser.getIdDetailUser());
        dto.setName(detailUser.getName());
        dto.setLastName(detailUser.getLastName());
        dto.setAddress(detailUser.getAddress());
        dto.setPhoneNumber(detailUser.getPhoneNumber());

        return dto;
        }

    public static DetailUser getDetailUserFromDetailUserDTO (DetailUserDTO dto ) {
        if (dto == null) {
            return null;
        }
        //create Object of Users

        DetailUser detailUser = new DetailUser();

        detailUser.setIdDetailUser(dto.getIdDetailUser());
        detailUser.setName(dto.getName());
        detailUser.setLastName(dto.getLastName());
        detailUser.setAddress(dto.getAddress());
        detailUser.setPhoneNumber(dto.getPhoneNumber());

        return detailUser;
    }
}

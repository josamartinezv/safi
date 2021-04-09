package com.called.attention.service;

import com.called.attention.service.dto.DetailUser.DetailUserDTO;
import com.called.attention.service.dto.Users.UsersDTO;
import com.called.attention.service.dto.Users.UsersFilterDocumentDTO;
import org.springframework.data.domain.Page;


public interface IDetailUserService {
    public Page<DetailUserDTO> read (Integer pageSize, Integer pageNumber);

    //Post - guardar - create
    public DetailUserDTO create (DetailUserDTO detailUserDTO );


    public DetailUserDTO update(DetailUserDTO detailUserDTO);
}

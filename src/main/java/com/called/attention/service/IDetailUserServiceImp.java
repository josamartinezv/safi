package com.called.attention.service;

import com.called.attention.domain.DetailUser;
import com.called.attention.domain.Users;
import com.called.attention.repository.DetailUserRepository;
import com.called.attention.service.dto.DetailUser.DetailUserDTO;
import com.called.attention.service.transformer.DetailUserTransformer.DetailUserTransformer;
import com.called.attention.service.transformer.Users.UsersTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IDetailUserServiceImp implements IDetailUserService{

    @Autowired
    DetailUserRepository detailUserRepository;
    @Override
    public Page<DetailUserDTO> read(Integer pageSize, Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return detailUserRepository.findAll(pageable)
                .map(DetailUserTransformer::getDetailUserDTOfromDetailUser);
    }

    @Override
    public DetailUserDTO create(DetailUserDTO detailUserDTO) {
        DetailUser detailUser = DetailUserTransformer.getDetailUserFromDetailUserDTO(detailUserDTO);
        return DetailUserTransformer.getDetailUserDTOfromDetailUser(detailUserRepository.save(detailUser));
    }


    @Override
    public DetailUserDTO update(DetailUserDTO detailUserDTO) {
        DetailUser detailUser = DetailUserTransformer.getDetailUserFromDetailUserDTO(detailUserDTO);
        return DetailUserTransformer.getDetailUserDTOfromDetailUser(detailUserRepository.save(detailUser));
    }
}

package com.called.attention.service;

import com.called.attention.domain.Rols;
import com.called.attention.repository.RolRepository;
import com.called.attention.service.dto.Rols.RolsDTO;
import com.called.attention.service.transformer.Rols.RolsTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IRolServicelmp implements IRolService {

    @Autowired
    RolRepository rolRepository;

    @Override
    public Iterable<Rols> read () {
        return rolRepository.findAll();
    }

    @Override
    public RolsDTO create ( RolsDTO rolsDTO ) {
        Rols rol = RolsTransformer.getRolFromRolDTO(rolsDTO);
        return RolsTransformer.getRolDTOfromRol(rolRepository.save(rol));
    }


}

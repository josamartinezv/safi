package com.called.attention.service.transformer.Rols;

import com.called.attention.domain.Rols;
import com.called.attention.service.dto.Rols.RolsDTO;

public class RolsTransformer {

    public static RolsDTO getRolDTOfromRol    (Rols rols){
        if (rols ==null){
            return null;
        }

        RolsDTO dto = new RolsDTO();

        //set variables

        dto.setIdRol(rols.getIdRol());
        dto.setDescription(rols.getDescription());

        return dto;
    }

    public static Rols getRolFromRolDTO (RolsDTO dto){
        if (dto == null){
            return null;
        }

        //create Object of Rols

        Rols rols = new Rols();

        rols.setIdRol(dto.getIdRol());
        rols.setDescription(dto.getDescription());

        return rols;
    }
}

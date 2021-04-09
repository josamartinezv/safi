package com.called.attention.service;

import com.called.attention.domain.Rols;
import com.called.attention.service.dto.Rols.RolsDTO;

import java.util.List;


public interface IRolService {

    //Get - obtener - read
    public Iterable<Rols> read ();


    //Post - guardar - create
    public RolsDTO create ( RolsDTO rolDTO );


}

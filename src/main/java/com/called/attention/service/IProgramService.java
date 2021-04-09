package com.called.attention.service;

import com.called.attention.domain.Program;
import com.called.attention.service.dto.Program.ProgramDTO;
import org.springframework.data.domain.Page;

public interface IProgramService {


    //Get - obtener - read
    Page<ProgramDTO> read(Integer pageNumber, Integer pageSize);

    //Post - guardar - create
    public ProgramDTO create (ProgramDTO programDTO);

}

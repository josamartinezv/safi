package com.called.attention.service;

import com.called.attention.service.dto.ProgramNumber.ProgramNumberDTO;
import com.called.attention.service.dto.ProgramNumber.ProgramNumberFilterCourseDTO;
import org.springframework.data.domain.Page;


public interface IProgramNumberService {

    //Get - obtener - read
    public Page<ProgramNumberDTO> read (Integer pageSize, Integer pageNumber);

    //Post - guardar - create
    public ProgramNumberDTO create (ProgramNumberDTO programNumberDTO);

    //Get - obtener informacion por course
    public ProgramNumberFilterCourseDTO searchByCourse (String number);

}

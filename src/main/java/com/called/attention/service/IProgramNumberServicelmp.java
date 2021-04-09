package com.called.attention.service;

import com.called.attention.domain.ProgramNumber;
import com.called.attention.repository.ProgramNumberRepository;
import com.called.attention.service.dto.ProgramNumber.ProgramNumberDTO;
import com.called.attention.service.dto.ProgramNumber.ProgramNumberFilterCourseDTO;
import com.called.attention.service.transformer.ProgramNumber.ProgramNumberFilterCourseTransformer;
import com.called.attention.service.transformer.ProgramNumber.ProgramNumberTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IProgramNumberServicelmp implements IProgramNumberService {

    @Autowired
    ProgramNumberRepository programNumberRepository;

    @Override
    public Page<ProgramNumberDTO> read (Integer pageSize , Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return programNumberRepository.findAll(pageable)
                .map(ProgramNumberTransformer::getProgramNumberDTOFromProgramNumber);
    }

    @Override
    public ProgramNumberDTO create ( ProgramNumberDTO programNumberDTO ) {
        ProgramNumber programNumber = ProgramNumberTransformer.getProgramNumberFromProgramNumberDTO(programNumberDTO);
        return ProgramNumberTransformer.getProgramNumberDTOFromProgramNumber(programNumberRepository.save(programNumber));
    }

    @Override
    public ProgramNumberFilterCourseDTO searchByCourse ( String number ) {
        ProgramNumber programNumber = programNumberRepository.findByNumber(number).get();
        ProgramNumberFilterCourseDTO programNumberFilterCourseDTO = ProgramNumberFilterCourseTransformer.getProgramNumberFilterCourseDTO(programNumber);
        return programNumberFilterCourseDTO;
    }
}

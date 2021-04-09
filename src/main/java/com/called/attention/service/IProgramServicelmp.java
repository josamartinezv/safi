package com.called.attention.service;

import com.called.attention.domain.Program;
import com.called.attention.repository.ProgramRepository;
import com.called.attention.service.dto.Program.ProgramDTO;
import com.called.attention.service.transformer.Program.ProgramTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IProgramServicelmp implements IProgramService {

    @Autowired
    ProgramRepository programRepository;

    @Override
    public Page<ProgramDTO> read(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return programRepository.findAll(pageable)
            .map(ProgramTransformer::getProgramDTOfromProgram);
    }

    @Override
    public ProgramDTO create(ProgramDTO programDTO) {
        Program program = ProgramTransformer.getProgramFromProgramDTO(programDTO);
        return ProgramTransformer.getProgramDTOfromProgram(programRepository.save(program));
    }

}

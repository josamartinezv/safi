package com.called.attention.service.transformer.Program;

import com.called.attention.domain.Program;
import com.called.attention.service.dto.Program.ProgramDTO;

public class ProgramTransformer {

    public static ProgramDTO getProgramDTOfromProgram (Program program){
        if (program == null){
            return null;
        }

        ProgramDTO programDTO = new ProgramDTO();

        programDTO.setIdProgram(program.getIdProgram());
        programDTO.setName(program.getName());

        return programDTO;

    }

    public static Program getProgramFromProgramDTO (ProgramDTO programDTO){
        if (programDTO == null ){
            return null;
        }

        Program program = new Program();

        program.setIdProgram(programDTO.getIdProgram());
        program.setName(programDTO.getName());

        return program;
    }

}

package com.called.attention.service.transformer.ProgramNumber;

import com.called.attention.domain.ProgramNumber;
import com.called.attention.service.dto.ProgramNumber.ProgramNumberDTO;

public class ProgramNumberTransformer {

    public static ProgramNumberDTO getProgramNumberDTOFromProgramNumber ( ProgramNumber programNumber ) {
        if (programNumber == null) {
            return null;
        }
        ProgramNumberDTO dto = new ProgramNumberDTO();

        //set variables
        dto.setIdProgramNumber(programNumber.getIdProgramNumber());
        dto.setNumber(programNumber.getNumber());
        dto.setProgram(programNumber.getProgram());

        return dto;
    }

    public static ProgramNumber getProgramNumberFromProgramNumberDTO ( ProgramNumberDTO dto ) {
        if (dto == null) {
            return null;
        }
        //create Object of programNumber

        ProgramNumber programNumber = new ProgramNumber();

        programNumber.setIdProgramNumber(dto.getIdProgramNumber());
        programNumber.setNumber(dto.getNumber());
        programNumber.setProgram(dto.getProgram());

        return programNumber;
    }
}

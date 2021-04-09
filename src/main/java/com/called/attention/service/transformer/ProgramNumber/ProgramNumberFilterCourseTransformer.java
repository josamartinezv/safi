package com.called.attention.service.transformer.ProgramNumber;

import com.called.attention.domain.ProgramNumber;
import com.called.attention.service.dto.ProgramNumber.ProgramNumberFilterCourseDTO;

public class ProgramNumberFilterCourseTransformer {

    public static ProgramNumberFilterCourseDTO getProgramNumberFilterCourseDTO ( ProgramNumber programNumber ){
        if (programNumber == null){
            return null;
        }

        ProgramNumberFilterCourseDTO dto = new ProgramNumberFilterCourseDTO();

        dto.setIdProgramNumber(programNumber.getIdProgramNumber());
        dto.setNumber(programNumber.getNumber());

        return dto;
    }
}

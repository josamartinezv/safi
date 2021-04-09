package com.called.attention.service.dto.ProgramNumber;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProgramNumberFilterCourseDTO {

    @NotNull
    private long idProgramNumber;
    private String number;

}

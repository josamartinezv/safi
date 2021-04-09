package com.called.attention.service.dto.ProgramNumber;

import com.called.attention.domain.Program;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ProgramNumberDTO {

    @NotNull
    private long idProgramNumber;

    @Size(min = 5, message = "the size have to be min 5 characteres")
    @Pattern(regexp = "[0-9]+", message = "must not contain special characters or numbers")
    private String number;


    private Program program;
}

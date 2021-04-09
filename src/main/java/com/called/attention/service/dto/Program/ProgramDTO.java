package com.called.attention.service.dto.Program;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProgramDTO {

    @Id
    private int idProgram;

    @Pattern(regexp = "[a-zA-Zñ ]+", message = "must not contain special characters or numbers")
    private String name;
}

package com.called.attention.service.dto.Rols;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class RolsDTO {

    @NotNull
    private int idRol;

    @NotEmpty(message = "This field can not be empty")
    @Pattern(regexp = "[a-zA-Z ]+", message = "must not contain special characters or numbers")
    private String description;
}

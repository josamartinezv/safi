package com.called.attention.service.dto.DetailUser;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
public class DetailUserDTO {

    private Long idDetailUser;
    @NotEmpty(message = "This field can not be empty")
    @Size(min = 2, max = 50, message = "the size have to be between 2 and 50 characteres")
    @Pattern(regexp = "[a-zA-Z ]+", message = "must not contain special characters or numbers")
    private String name;

    @NotEmpty(message = "This field can not be empty")
    @Size(min = 2, max = 50, message = "the size have to be between 2 and 50 characteres")
    @Pattern(regexp = "[a-zA-Z ]+", message = "must not contain special characters or numbers")
    private String lastName;

    @NotEmpty(message = "This field can not be empty")
    @Size(min = 2, max = 50, message = "the size have to be between 2 and 50 characteres")
    @Pattern(regexp = "[a-zA-Z ]+", message = "must not contain special characters or numbers")
    private String address;

    private String phoneNumber;

}

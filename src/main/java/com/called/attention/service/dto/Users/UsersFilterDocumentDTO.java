package com.called.attention.service.dto.Users;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UsersFilterDocumentDTO {

    @NotNull
    private Long idUsers;
    private String documentNumber;
    private String name;
    private String lastName;
}

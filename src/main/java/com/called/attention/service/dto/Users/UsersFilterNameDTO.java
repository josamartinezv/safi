package com.called.attention.service.dto.Users;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class UsersFilterNameDTO {

    @NotNull
    private Long idUsers;
    private String fullName;
}

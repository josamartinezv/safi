package com.called.attention.service.dto.UsersHasProgramNumber;

import com.called.attention.domain.ProgramNumber;
import com.called.attention.domain.Users;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsersHasProgramFilterDTO {

    private Long idUser;
    private String fullName;
    private String documentNumber;
    private String emailSena;
    private String number;
    private String phoneNumber;
}

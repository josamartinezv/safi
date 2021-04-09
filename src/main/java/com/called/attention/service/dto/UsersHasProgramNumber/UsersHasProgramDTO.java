package com.called.attention.service.dto.UsersHasProgramNumber;


import com.called.attention.domain.ProgramNumber;
import com.called.attention.domain.Users;
import com.called.attention.domain.enumeration.UsersCourseRol;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Setter
@Getter
public class UsersHasProgramDTO {
    @Id
    @NotNull
    private Long idCUsersHasProgramNumber;
    private ProgramNumber programNumber;
    private Users users;
    private UsersCourseRol usersCourseRol;


}

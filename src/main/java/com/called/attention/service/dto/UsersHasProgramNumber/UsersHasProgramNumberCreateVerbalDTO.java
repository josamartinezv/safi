package com.called.attention.service.dto.UsersHasProgramNumber;

import com.called.attention.domain.ProgramNumber;
import com.called.attention.domain.Users;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UsersHasProgramNumberCreateVerbalDTO {

        private Users users;
        private String fullName;
        private ProgramNumber programNumber;
    }
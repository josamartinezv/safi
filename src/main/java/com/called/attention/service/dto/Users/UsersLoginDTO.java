package com.called.attention.service.dto.Users;

import com.called.attention.domain.DetailUser;
import com.called.attention.domain.Rols;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Setter
@Getter

public class UsersLoginDTO implements Serializable {
    private Long idUsers;

    @Size(min = 5, max = 20, message = "the size have to be between 5 and 20 characteres")//java
    private String documentNumber;

    @Size(min = 5, max = 50, message = "the size have to be between 5 and 50 characteres")
    @Email(message = "email does not have a good structure")
    private String emailSena;

    @Size(min = 5, max = 50, message = "the size have to be between 5 and 50 characteres")
    @Email(message = "email does not have a good structure")
    private String email;

    private Boolean enabled;

    private String name;

    private String lastName;

    private Set<Rols> rols;

    private List<String> authorities;
}

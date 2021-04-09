package com.called.attention.service.dto.Users;

import com.called.attention.domain.DetailUser;
import com.called.attention.domain.Rols;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class UsersDTO {
    private Long idUsers;

    @Size(min = 5, max = 20, message = "the size have to be between 5 and 20 characteres")//java
    @Pattern(regexp = "[0-9]*", message = "Must contain numbers")
    private String documentNumber;

    @Size(min = 5, max = 50, message = "the size have to be between 5 and 50 characteres")
    @Email(message = "email does not have a good structure")
    private String emailSena;

    @Size(min = 5, max = 50, message = "the size have to be between 5 and 50 characteres")
    @Email(message = "email does not have a good structure")
    private String email;

    private String password;

    private Boolean enabled;

    private DetailUser detailUser;

    private String name;

    private String lastName;

    private String address;

    private String phoneNumber;

    private Set<Rols> rols = new HashSet<>();

    private List<String> authorities;

    private String fullName;


}

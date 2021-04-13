package com.called.attention.domain;

import com.called.attention.service.dto.Users.UsersDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Set;

@Entity
@Table (name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (columnDefinition = "serial")
    private Long idUsers;

    @NotEmpty(message = "This field can not be empty")
    @Size(min = 5, message = "the size have to be min 5 characteres")
    @Pattern(regexp = "[0-9]*", message = "Must contain numbers")
    private String documentNumber;

    @NotNull
    @Size(max = 60)
    @Column(name = "password", length = 60)
    private String password;

    @NotEmpty(message = "This field can not be empty")
    @Size(min = 5, max = 50, message = "the size have to be between 5 and 50 characteres")
    @Email(message = "email does not have a good structure")
    private String emailSena;

    @Size(min = 5, max = 50, message = "the size have to be between 5 and 50 characteres")
    @Email(message = "email does not have a good structure")
    private String email;

    private Boolean enabled;

    @JoinColumn(name = "id_detail_user", unique = true)
    @OneToOne(cascade = CascadeType.ALL)
    private DetailUser detailUser;

    //relations with rol
    @ManyToMany
    @JoinTable (name = "users_has_rol",
            joinColumns = @JoinColumn (name = "users_id_users"),
            inverseJoinColumns = @JoinColumn (name = "rol_id_rol"))
    private Set<Rols> rols;

}

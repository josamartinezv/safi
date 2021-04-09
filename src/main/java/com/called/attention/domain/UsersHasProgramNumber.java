package com.called.attention.domain;

import com.called.attention.domain.enumeration.UsersCourseRol;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table (name = "usersHasProgramNumber")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersHasProgramNumber {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (columnDefinition = "serial")
    private Long idCUsersHasProgramNumber;

    //Relation with ProgramNumber

    @ManyToOne
    private ProgramNumber programNumber;

    //Relation with Users
    @ManyToOne
    private Users users;

    @Enumerated (EnumType.STRING)
    private UsersCourseRol usersCourseRol;

}

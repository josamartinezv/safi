package com.called.attention.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table (name = "rols")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rols {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (columnDefinition = "serial")
    private int idRol;


    @NotEmpty(message = "This field can not be empty")
    @Pattern(regexp = "[a-zA-Z ]+", message = "must not contain special characters or numbers")
    private String description;


}

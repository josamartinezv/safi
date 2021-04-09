package com.called.attention.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table (name = "Program")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Program {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (columnDefinition = "Serial")
    private int idProgram;


    @Pattern(regexp = "[a-zA-Zñ ]+", message = "must not contain special characters or numbers")
    private String name;
}

package com.called.attention.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table (name = "programNumber")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProgramNumber {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (columnDefinition = "serial")
    private Long idProgramNumber;

    @Size(min = 5, message = "the size have to be min 5 characteres")
    @Pattern(regexp = "[0-9]+", message = "must not contain special characters or numbers")
    private String number;

    @ManyToOne
    private Program program;

}

package com.called.attention.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DetailUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private Long idDetailUser;

    @NotEmpty(message = "This field can not be empty")
    @Size(min = 2, max = 50, message = "the size have to be between 2 and 50 characteres")
    @Pattern(regexp = "[a-zA-Z ]+", message = "must not contain special characters or numbers")
    private String name;

    @NotEmpty(message = "This field can not be empty")
    @Size(min = 2, max = 50, message = "the size have to be between 2 and 50 characteres")
    @Pattern(regexp = "[a-zA-Z ]+", message = "must not contain special characters or numbers")
    private String lastName;

    @NotEmpty(message = "This field can not be empty")
    @Size(min = 5, max = 50, message = "the size have to be between 5 and 50 characteres")
    private String address;

    @Pattern(regexp = "[0-9]*", message = "Must contain numbers")
    private String phoneNumber;
}

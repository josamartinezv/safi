package com.called.attention.service.dto.VerbalCalled;

import com.called.attention.domain.ProgramNumber;
import com.called.attention.domain.Users;
import com.called.attention.domain.enumeration.PhaseVerbalCalled;
import com.called.attention.domain.enumeration.TypeVerbalCalled;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
@Setter
@Getter
public class VerbalCalledDTO {

    @Id
    private int idVerbalCalled;
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCalled;
    private TypeVerbalCalled typeVerbalCalled;
    private PhaseVerbalCalled phaseVerbalCalled;
    private Boolean state;
    private Boolean procedure;

    @NotEmpty(message = "This field can not be empty")
    @Size(min = 5, max = 300, message = "the size have to be between 2 and 50 characteres")
    @Column(length = 300)
    @Pattern(regexp = "[a-zA-Z.,() ]+", message = "must not contain special characters or numbers")
    private String situation;

    @NotEmpty(message = "This field can not be empty")
    @Size(min = 5, max = 300, message = "the size have to be between 2 and 50 characteres")
    @Column(length = 300)
    @Pattern(regexp = "[a-zA-Z ]+", message = "must not contain special characters or numbers")
    private String summary;

    @NotEmpty(message = "This field can not be empty")
    @Size(min = 5, max = 300, message = "the size have to be between 2 and 50 characteres")
    @Column(length = 300)
    @Pattern(regexp = "[a-zA-Z ]+", message = "must not contain special characters or numbers")
    private String impact;

    @NotEmpty(message = "This field can not be empty")
    @Size(min = 5, max = 300, message = "the size have to be between 2 and 50 characteres")
    @Column(length = 300)
    @Pattern(regexp = "[a-zA-Z ]+", message = "must not contain special characters or numbers")
    private String effect;

    @NotEmpty(message = "This field can not be empty")
    @Size(min = 5, max = 300, message = "the size have to be between 2 and 50 characteres")
    @Column(length = 300)
    @Pattern(regexp = "[a-zA-Z ]+", message = "must not contain special characters or numbers")
    private String commitment;

    @NotEmpty(message = "This field can not be empty")
    @Size(min = 5, max = 300, message = "the size have to be between 2 and 50 characteres")
    @Column(length = 300)
    @Pattern(regexp = "[a-zA-Z ]+", message = "must not contain special characters or numbers")
    private String recovery;

    private ProgramNumber programNumber;
    private Users instructor;
    private Users aprentice;

}

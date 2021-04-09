package com.called.attention.domain;

import com.called.attention.domain.enumeration.PhaseVerbalCalled;
import com.called.attention.domain.enumeration.TypeVerbalCalled;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table (name = "verbalCalled")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VerbalCalled {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (columnDefinition = "serial")
    private int idVerbalCalled;

    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCalled;

    @Enumerated (EnumType.STRING)
    private TypeVerbalCalled typeVerbalCalled;

    @Enumerated (EnumType.STRING)
    private PhaseVerbalCalled phaseVerbalCalled;

    @Column (name = "state")
    private Boolean state;

    @Column (name = "procedure")
    private Boolean procedure;

    @NotEmpty(message = "This field can not be empty")
    @Size(min = 2, max = 50, message = "the size have to be between 2 and 50 characteres")
    @Column(length = 300)
    @Pattern(regexp = "[a-zñA-ZÑáéíóúÁÉÍÓÚ,;\"\".  ]+", message = "must not contain special characters or numbers")
    private String situation;

    @NotEmpty(message = "This field can not be empty")
    @Size(min = 2, max = 50, message = "the size have to be between 2 and 50 characteres")
    @Column(length = 300)
    @Pattern(regexp = "[a-zñA-ZÑáéíóúÁÉÍÓÚ,;\"\".  ]+", message = "must not contain special characters or numbers")
    private String summary;

    @NotEmpty(message = "This field can not be empty")
    @Size(min = 2, max = 50, message = "the size have to be between 2 and 50 characteres")
    @Column(length = 300)
    @Pattern(regexp = "[a-zñA-ZÑáéíóúÁÉÍÓÚ,;\"\".  ]+", message = "must not contain special characters or numbers")
    private String impact;

    @NotEmpty(message = "This field can not be empty")
    @Size(min = 2, max = 50, message = "the size have to be between 2 and 50 characteres")
    @Column(length = 300)
    @Pattern(regexp = "[a-zñA-ZÑáéíóúÁÉÍÓÚ,;\"\".  ]+", message = "must not contain special characters or numbers")
    private String effect;

    @NotEmpty(message = "This field can not be empty")
    @Size(min = 2, max = 50, message = "the size have to be between 2 and 50 characteres")
    @Column(length = 300)
    @Pattern(regexp = "[a-zñA-ZÑáéíóúÁÉÍÓÚ,;\"\". ]+", message = "must not contain special characters or numbers")
    private String commitment;

    @NotEmpty(message = "This field can not be empty")
    @Size(min = 2, max = 50, message = "the size have to be between 2 and 50 characteres")
    @Column(length = 300)
    @Pattern(regexp = "[a-zñA-ZÑáéíóúÁÉÍÓÚ,;\"\". ]+", message = "must not contain special characters or numbers")
    private String recovery;

    //Relations with program_number
    @ManyToOne
    private ProgramNumber programNumber;

    //Relation With users instructor
    @ManyToOne
    private Users instructor;

    //Relation with users apprentice
    @ManyToOne
    private Users aprentice;

}

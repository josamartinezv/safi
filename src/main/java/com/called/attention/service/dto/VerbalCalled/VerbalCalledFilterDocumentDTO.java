package com.called.attention.service.dto.VerbalCalled;

import com.called.attention.domain.ProgramNumber;
import com.called.attention.domain.Users;
import com.called.attention.domain.enumeration.PhaseVerbalCalled;
import com.called.attention.domain.enumeration.TypeVerbalCalled;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Getter
@Setter
public class VerbalCalledFilterDocumentDTO {

    @NotNull
    private int idVerbalCalled;
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCalled;
    private TypeVerbalCalled typeVerbalCalled;
    private PhaseVerbalCalled phaseVerbalCalled;
    private Boolean state;
    private Boolean procedure;
    private String situation;
    private String summary;
    private String impact;
    private String effect;
    private String commitment;
    private String recovery;
    private ProgramNumber programNumber;
    private Users instructor;
    private Users aprentice;
}

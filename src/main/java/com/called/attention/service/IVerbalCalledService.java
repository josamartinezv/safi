package com.called.attention.service;

import com.called.attention.domain.VerbalCalled;
import com.called.attention.domain.enumeration.PhaseVerbalCalled;
import com.called.attention.domain.enumeration.TypeVerbalCalled;
import com.called.attention.service.dto.VerbalCalled.VerbalCalledDTO;
import com.called.attention.service.dto.VerbalCalled.VerbalCalledFilterDocumentDTO;
import com.called.attention.service.dto.VerbalCalled.VerbalCalledFiltersDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Optional;

public interface IVerbalCalledService {
    //Get - obtener - read
    Page <VerbalCalledDTO> read(Integer pageNumber, Integer pageSize);

    //Put - actualizar - update
    public VerbalCalledDTO update(VerbalCalledDTO verbalCalledDTO);

    //Post - guardar - create
    public ResponseEntity <VerbalCalledDTO> create (VerbalCalledDTO verbalCalledDTO);

    Optional<VerbalCalledDTO> getById(Integer idVerbalCalled);

    public void delete (Integer idVerbalCalled);

    public Page<VerbalCalledFilterDocumentDTO> getByfilterDocument(String documentNumber, Integer pageSize, Integer pageNumber);


    public Page<VerbalCalledFiltersDTO> getByFilters(String documentNumber, String documentNumberInstructor, String number,  Boolean state, TypeVerbalCalled typeVerbalCalled, PhaseVerbalCalled phaseVerbalCalled, Integer pageSize, Integer pageNumber);


}

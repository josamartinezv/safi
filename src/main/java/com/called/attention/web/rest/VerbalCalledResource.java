package com.called.attention.web.rest;

import com.called.attention.domain.enumeration.PhaseVerbalCalled;
import com.called.attention.domain.enumeration.TypeVerbalCalled;
import com.called.attention.service.IVerbalCalledService;
import com.called.attention.service.dto.Rols.RolsDTO;
import com.called.attention.service.dto.VerbalCalled.VerbalCalledDTO;
import com.called.attention.service.dto.VerbalCalled.VerbalCalledFilterDocumentDTO;
import com.called.attention.service.dto.VerbalCalled.VerbalCalledFiltersDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class VerbalCalledResource {
    @Autowired
    IVerbalCalledService verbalCalledService;

    //My api-rest create
    @PostMapping ("/verbalCalled")
    public ResponseEntity create (@Valid @RequestBody VerbalCalledDTO verbalCalledDTO, BindingResult bindingResult) {

        Map<String, Object> response = new HashMap<>();

        if (bindingResult.hasErrors()){
            List<String> error = bindingResult.getFieldErrors()
                    .stream()
                    .map(err -> "The field " + err.getField() + " " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("Error", error);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
        return verbalCalledService.create(verbalCalledDTO);
    }

    //My api-rest read all
    @GetMapping ("/verbalCalled")
    public Page<VerbalCalledDTO> read (@RequestParam(value = "pageNumber") Integer pageNumber,
                                       @RequestParam(value = "pageSize" ) Integer pageSize) {

        return verbalCalledService.read(pageNumber, pageSize);

    }


    //My api-rest update
    @PutMapping ("/verbalCalled")
    public ResponseEntity<?> update (@Valid @RequestBody VerbalCalledDTO verbalCalledDTO, BindingResult bindingResult ) {
        Map<String, Object> response = new HashMap<>();

        if (bindingResult.hasErrors()){
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(err -> "the field " + err.getField() + " " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("Errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(verbalCalledService.update(verbalCalledDTO), HttpStatus.OK);
    }

    @GetMapping ("/verbalCalled/search-by-documentNumber")
    public Page<VerbalCalledFilterDocumentDTO> getByfilterDocument(@RequestParam (name = "documentNumber", required = false) String documentNumberAprentice,
                                                                   @RequestParam (name = "pageSize") Integer pageSize,
                                                                   @RequestParam (name = "pageNumber") Integer pageNumber){
        return verbalCalledService.getByfilterDocument(documentNumberAprentice, pageSize, pageNumber);
    }

    @GetMapping ("/verbalCalled/search-by-filters")
    public Page<VerbalCalledFiltersDTO> getByFilters(@RequestParam (name = "documentNumber", required = false ) String documentNumber,
                                                     @RequestParam (name = "documentNumberInstructor", required = false ) String documentNumberInstructor,
                                                     @RequestParam (name = "number", required = false ) String number,
                                                     @RequestParam (name = "state", required = false ) Boolean state,
                                                     @RequestParam (name = "name", required = false ) String instructorName,
                                                     @RequestParam (name = "lastName", required = false ) String instructorlastName,
                                                     @RequestParam (name = "typeVerbalCalled", required = false ) TypeVerbalCalled typeVerbalCalled,
                                                     @RequestParam (name = "phaseVerbalCalled", required = false ) PhaseVerbalCalled phaseVerbalCalled,
                                                     @RequestParam (name = "pageSize") Integer pageSize,
                                                     @RequestParam (name = "pageNumber") Integer pageNumber){
        return verbalCalledService.getByFilters(documentNumber, documentNumberInstructor, number, state, typeVerbalCalled, phaseVerbalCalled, pageSize , pageNumber);
    }

    @GetMapping("/verbalCalled/{idVerbalCalled}")
    public ResponseEntity<VerbalCalledDTO> getById(@PathVariable Integer idVerbalCalled){
        return new ResponseEntity<>(verbalCalledService.getById(idVerbalCalled).get(), HttpStatus.OK);
    }



    @DeleteMapping("/verbalCalled/{idVerbalCalled}")
    public void delete(@PathVariable Integer idVerbalCalled) {
        verbalCalledService.delete(idVerbalCalled);
    }

}

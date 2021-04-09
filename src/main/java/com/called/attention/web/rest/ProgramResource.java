package com.called.attention.web.rest;

import com.called.attention.domain.Program;
import com.called.attention.service.IProgramService;
import com.called.attention.service.dto.Program.ProgramDTO;
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
@RequestMapping ("api")
public class ProgramResource {

    @Autowired
    IProgramService programService;

    //My api-rest create
    @PostMapping ("/program")
    public ResponseEntity<?> create (@Valid @RequestBody ProgramDTO programDTO, BindingResult bindingResult) {
        ProgramDTO dto = null;
        Map<String, Object> response = new HashMap<>();

        if (bindingResult.hasErrors()){
            List<String> errors = bindingResult.getFieldErrors().stream().map(err -> "the field " + err.getField() + " " + err.getDefaultMessage()).collect(Collectors.toList());
            response.put("Error ", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(programService.create(programDTO), HttpStatus.OK);
    }

    //My api-rest read all
    @GetMapping ("/program")
    public Page<ProgramDTO> read (@RequestParam(value = "pageNumber") Integer pageNumber,
                                  @RequestParam(value = "pageSize" ) Integer pageSize) {

        return programService.read(pageNumber, pageSize);

    }
}

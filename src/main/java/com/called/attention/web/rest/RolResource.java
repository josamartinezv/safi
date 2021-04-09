package com.called.attention.web.rest;

import com.called.attention.domain.Rols;
import com.called.attention.service.IRolService;
import com.called.attention.service.dto.Rols.RolsDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping ("/api")
public class RolResource {



    @Autowired
    IRolService rolService;

    //My api-rest create
    @PostMapping ("/rols")
    public ResponseEntity<?> create (@Valid @RequestBody RolsDTO rolsDTO, BindingResult bindingResult) {
        RolsDTO dto = null;
        Map<String, Object> response = new HashMap<>();

        if (bindingResult.hasErrors()){
            List<String> errors = bindingResult.getFieldErrors().stream().map(err -> "the field " + err.getField() + " " + err.getDefaultMessage()).collect(Collectors.toList());

            response.put("Errors ", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(rolService.create(rolsDTO), HttpStatus.OK);
    }

    //My api-rest read all
    @GetMapping ("/rol")
    public Iterable<Rols> read () {
        return rolService.read();
    }



}

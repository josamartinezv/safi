package com.called.attention.web.rest;

import com.called.attention.service.IProgramNumberService;
import com.called.attention.service.dto.Program.ProgramDTO;
import com.called.attention.service.dto.ProgramNumber.ProgramNumberDTO;
import com.called.attention.service.dto.ProgramNumber.ProgramNumberFilterCourseDTO;
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
@RequestMapping ("/api")
public class ProgramNumberResource {

    @Autowired
    IProgramNumberService programNumberService;

    //My api-rest create
    @PostMapping ("/programNumber")
    public ResponseEntity<?> create (@Valid @RequestBody ProgramNumberDTO programNumberDTO, BindingResult bindingResult) {
        ProgramNumberDTO dto = null;
        Map<String, Object> response = new HashMap<>();

        if (bindingResult.hasErrors()){
            List<String> errors = bindingResult.getFieldErrors().stream().map(err -> "the field " + err.getField() + " " + err.getDefaultMessage()).collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(programNumberService.create(programNumberDTO), HttpStatus.OK);
    }

    //My api-rest read all
    @GetMapping ("/programNumber")
    public Page<ProgramNumberDTO> read (@RequestParam(value = "pageSize") Integer pageSize,
                                        @RequestParam(value = "pageNumber") Integer pageNumber) {
        return programNumberService.read(pageSize, pageNumber);
    }

    @GetMapping("programNumber/searchByCourse")
    public ProgramNumberFilterCourseDTO searchByCourse (@RequestParam(value = "course", required = false) String course){
        return programNumberService.searchByCourse(course);
    }

}

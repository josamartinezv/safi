package com.called.attention.web.rest;


import com.called.attention.service.IDetailUserService;
import com.called.attention.service.dto.DetailUser.DetailUserDTO;
import com.called.attention.service.dto.Users.UsersDTO;
import com.called.attention.service.dto.Users.UsersFilterDocumentDTO;
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
public class DetailUserResource {

    @Autowired
    IDetailUserService detailUserService;

    @PostMapping("/detailUser")
    public ResponseEntity<?> create(@Valid @RequestBody DetailUserDTO detailUserDTO, BindingResult bindingResult) {
        Map<String, Object> response = new HashMap<>();

        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(err -> "field " + err.getField() + " " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("Error", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(detailUserService.create(detailUserDTO), HttpStatus.OK);

    }

    @GetMapping("/detailUser")
    public Page<DetailUserDTO> read(@RequestParam(value = "pageSize") Integer pageSize,
                               @RequestParam(value = "pageNumber") Integer pageNumber) {
        return detailUserService.read(pageSize, pageNumber);
    }


    @PutMapping("/detailUser")
    public DetailUserDTO update(@RequestBody DetailUserDTO detailUserDTO) {
        return detailUserService.update(detailUserDTO);

    }


}

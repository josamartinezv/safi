package com.called.attention.web.rest;

import com.called.attention.domain.Users;
import com.called.attention.domain.VerbalCalled;
import com.called.attention.service.IUsersService;
import com.called.attention.service.dto.Rols.RolsDTO;
import com.called.attention.service.dto.Users.UsersDTO;
import com.called.attention.service.dto.Users.UsersFilterDocumentDTO;
import com.called.attention.service.dto.Users.UsersFilterNameDTO;
import com.called.attention.service.dto.Users.UsersLoginDTO;
import com.called.attention.service.dto.VerbalCalled.VerbalCalledDTO;
import lombok.Getter;
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
public class UsersResource {

    @Autowired
    IUsersService usersService;

    //My api-rest create
    @PostMapping("/users")
    public ResponseEntity<?> create(@Valid @RequestBody UsersDTO usersDTO, BindingResult bindingResult) {
        UsersDTO dto = null;
        Map<String, Object> response = new HashMap<>();

        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(err -> "field " + err.getField() + " " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("Error", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(usersService.create(usersDTO), HttpStatus.OK);


    }



    //My api-rest read all
    @GetMapping("/users")
    public Page<UsersDTO> read(@RequestParam(value = "pageSize", required = false) Integer pageSize,
                               @RequestParam(value = "pageNumber", required = false) Integer pageNumber) {
        return usersService.read(pageSize, pageNumber);
    }

    @GetMapping("users/searchByDocumentnumber")
    public UsersFilterDocumentDTO searchBydocument(@RequestParam(value = "documentNumber", required = false) String documentNumber) {
        return usersService.searchBydocument(documentNumber);
    }


    @PutMapping("/users")
    public UsersDTO update(@RequestBody UsersDTO usersDTO) {
        return usersService.update(usersDTO);

    }

    @GetMapping("/users/{idUsers}")
    public ResponseEntity<UsersDTO> getById (@PathVariable Long idUsers){
        return new ResponseEntity<>(usersService.getById(idUsers).get(), HttpStatus.OK);
    }


    @GetMapping("/user/account")
    public ResponseEntity<UsersLoginDTO> getAccount(@RequestParam(value = "username") String documentNumber){
        return new ResponseEntity<>(usersService.getBydocument(documentNumber), HttpStatus.OK);
    }




}
package com.called.attention.web.rest;

import com.called.attention.domain.Users;
import com.called.attention.domain.UsersHasProgramNumber;
import com.called.attention.service.IUsersHasProgramNumberService;
import com.called.attention.service.dto.Program.ProgramDTO;
import com.called.attention.service.dto.UsersHasProgramNumber.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UsersHasProgramNumberResource {

    @Autowired
    IUsersHasProgramNumberService usersHasProgramNumberService;

    //My api-rest create
    @PostMapping("/userHasProgramNumber")
    public UsersHasProgramDTO create(@RequestBody UsersHasProgramDTO usersHasProgramDTO ){
        return usersHasProgramNumberService.create(usersHasProgramDTO);
    }

    //My api-rest read all
    @GetMapping("/userHasProgramNumber")
    public Page<UsersHasProgramDTO> read (@RequestParam(value = "pageNumber") Integer pageNumber,
                                          @RequestParam(value = "pageSize" ) Integer pageSize) {

        return usersHasProgramNumberService.read(pageNumber, pageSize);

    }

    @GetMapping("/usersHasProgramNumber/search-by-programNumber")
    public Page<UsersHasProgramNumberFilterProgramDTO> getUsersByFilterProgramNumber(@RequestParam(name = "number" ) String number,
                                                                                     @RequestParam (name = "pageSize") Integer pageSize,
                                                                                     @RequestParam (name = "pageNumber") Integer pageNumber){
        return usersHasProgramNumberService.getUsersByFilterProgramNumber(number, pageSize, pageNumber);
    }

    @GetMapping("/usersHasProgramNumber/search-by-documentNumber")
    public Page<UsersHasProgramNumberFilterByDocument> getUsersByFilterDocumentNumber(@RequestParam (name = "documentNumber" ) String documentNumber,
                                                                                      @RequestParam (name = "pageSize" ) Integer pageSize,
                                                                                      @RequestParam (name = "pageNumber") Integer pageNumber){
        return usersHasProgramNumberService.getUsersByFilterDocumentNumber(documentNumber, pageSize, pageNumber);
    }

    @GetMapping("/usersHasProgramNumber/search-by-filters")
    public Page<UsersHasProgramFilterDTO> getByProgramNumberAndUsersAndUsersCourseRol(
                                                                         @RequestParam(name = "number", required = false) String number,
                                                                         @RequestParam(name = "documentNumber", required = false ) String documentNumber,
                                                                         @RequestParam (name = "pageSize") Integer pageSize,
                                                                         @RequestParam (name = "pageNumber") Integer pageNumber){
        return usersHasProgramNumberService.getByProgramNumberAndUsersAndUsersCourseRol(number, documentNumber, pageSize, pageNumber);
    }


    @GetMapping("/usersHasProgramNumber/{idusers}")
    public Optional<UsersHasProgramNumberCreateVerbalDTO> findByUsers ( @PathVariable Users idusers ){
        return usersHasProgramNumberService.findByUsers(idusers);
    }

    @GetMapping("/usersHasProgramNumber/document-number")
    public Optional<UsersHasProgramNumberFilterByDocument> getUsersByMyDocument ( @RequestParam (name = "documentNumber" ) String documentNumber ){
        return usersHasProgramNumberService.getUsersByMyDocument(documentNumber);
    }


}

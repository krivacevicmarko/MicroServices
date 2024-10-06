package org.example.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.example.dto.*;
import org.example.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@RequestMapping("/manager")
public class ManagerController {




        private UserService userService;
        public ManagerController(UserService userService) {this.userService = userService;}

    @ApiOperation(value = "Get all managers")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "What page number you want", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "Number of items to return", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @GetMapping
    public ResponseEntity<Page<ManagerDto>> getAllManagers(@RequestHeader("Authorization") String authorization,
                                                       Pageable pageable) {

        return new ResponseEntity<>(userService.findAllManagers(pageable), HttpStatus.OK);
    }


    @ApiOperation(value = "Register manager")
    @PostMapping("/register")
    public ResponseEntity<ManagerDto> saveManager(@RequestBody @Valid ManagerCreateDto managerCreateDto) {
        System.out.println(managerCreateDto);
        return new ResponseEntity<>(userService.add(managerCreateDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Login")
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginManager(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(userService.loginManager(tokenRequestDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManagerDto> findById(@RequestHeader("Authorization") String authorization, @PathVariable("id") Integer id) {
        return new ResponseEntity<>(userService.findManagerById(id), HttpStatus.OK);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<ManagerDto> findByEmail(@RequestHeader("Authorization") String authorization, @PathVariable("email") String email) {
        return new ResponseEntity<>(userService.findManagerByEmail(email), HttpStatus.OK);
    }

}

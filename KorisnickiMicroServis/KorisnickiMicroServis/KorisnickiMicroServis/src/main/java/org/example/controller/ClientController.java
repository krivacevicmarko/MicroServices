package org.example.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.example.domain.Client;
import org.example.dto.*;
import org.example.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("/client")
public class ClientController {

    private UserService userService;

    public ClientController(UserService userService) {this.userService = userService;}

    @ApiOperation(value = "edit")
    @PutMapping("/profile")
    public ResponseEntity<String> editProfile(@RequestBody ClientDto clientProfileEditDto) {
        // Get the authenticated user's ID (you may use Spring Security for this)
        Integer clientId = clientProfileEditDto.getId();
        String username = clientProfileEditDto.getUsername();
        String password = clientProfileEditDto.getPassword();
        String name = clientProfileEditDto.getName();
        String surname = clientProfileEditDto.getSurname();
        int numberOfTrainings = clientProfileEditDto.getNumberOfReservations();
        String email = clientProfileEditDto.getEmail();
        LocalDate dateOfBirth  = clientProfileEditDto.getDateOfBirth();
        boolean isBlocked = clientProfileEditDto.isBlocked();
        System.out.println(clientProfileEditDto.toString());
        // Update user profile using the provided data
        userService.editClientProfile(clientId,numberOfTrainings,username,password,email,dateOfBirth,name,surname,isBlocked);

        return ResponseEntity.ok("Profile updated successfully");
    }


    @ApiOperation(value = "Get all clients")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "What page number you want", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "Number of items to return", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @GetMapping
    public ResponseEntity<Page<ClientDto>> getAllClients(@RequestHeader("Authorization") String authorization,
                                                       Pageable pageable) {

        return new ResponseEntity<>(userService.findAllClients(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}/discount")
    public ResponseEntity<DiscountDto> getDiscount(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(userService.findDiscount(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Register user")
    @PostMapping("/register")
    public ResponseEntity<ClientDto> saveClient(@RequestBody @Valid ClientCreateDto clientCreateDto) {
        return new ResponseEntity<>(userService.add(clientCreateDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Login")
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginUser(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(userService.login(tokenRequestDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> findById(@RequestHeader("Authorization") String authorization, @PathVariable("id") Integer id) {
        return new ResponseEntity<>(userService.findClientById(id), HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ClientDto> findByEmail(@RequestHeader("Authorization") String authorization, @PathVariable("email") String email) {
        return new ResponseEntity<>(userService.findClientByEmail(email), HttpStatus.OK);
    }

}

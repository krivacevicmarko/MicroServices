package org.example.controller;

import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @PostMapping("/{clientId}/block")
    public ResponseEntity<Void> blockUser(@PathVariable Integer clientId) {
        userService.blockClient(clientId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{clientId}/unblock")
    public ResponseEntity<Void> unblockUser(@PathVariable Integer clientId) {
        userService.unblockClient(clientId);
        return ResponseEntity.ok().build();
    }

}

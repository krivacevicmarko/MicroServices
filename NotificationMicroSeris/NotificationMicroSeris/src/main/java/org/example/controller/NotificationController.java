package org.example.controller;

import org.example.dto.NotificationCreateDto;
import org.example.dto.NotificationDto;
import org.example.service.NotificationServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
    @RequestMapping("/notification")
    public class NotificationController {
        private  NotificationServiceImplementation notificationService;

        @Autowired
        public NotificationController(NotificationServiceImplementation notificationService) {
            this.notificationService = notificationService;
        }


        @PostMapping("/notification")
        public ResponseEntity<NotificationDto> createNotification(@RequestBody NotificationCreateDto notification) {
            return new ResponseEntity<>(notificationService.createNotification(notification), HttpStatus.CREATED);
        }

        @GetMapping
        public ResponseEntity<Page<NotificationDto>> findAll(@RequestHeader("Authorization") String authorization, @ApiIgnore Pageable pageable) {
            return new ResponseEntity<>(notificationService.findAll(pageable), HttpStatus.OK);
        }

    @GetMapping("/tip/{tip}")
    public ResponseEntity<List<NotificationDto>> findByTrener(@RequestHeader("Authorization") String authorization, @PathVariable("tip") String tip) {
        return new ResponseEntity<>(notificationService.findByType(tip), HttpStatus.OK);
    }


}

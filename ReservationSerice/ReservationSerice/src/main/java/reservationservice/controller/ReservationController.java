package reservationservice.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reservationservice.domain.Reservation;
import reservationservice.dto.ReservationCreateDto;
import reservationservice.dto.ReservationDto;
import reservationservice.service.ReservationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> findById(@RequestHeader("Authorization") String authorization, @PathVariable("id") Integer id) {
        return new ResponseEntity<>(reservationService.findById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Void> addReservation(@RequestBody @Valid ReservationCreateDto reservationCreateDto) {
        System.out.println(reservationCreateDto.toString());
        System.out.println("IDEMOO");
        reservationService.addReservation(reservationCreateDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRes(@PathVariable("id") Integer id) {
        System.out.println("USAAAAIIIIIIII");
        reservationService.deleteReservation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
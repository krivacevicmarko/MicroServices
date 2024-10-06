package reservationservice.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reservationservice.dto.GymCreateDto;
import reservationservice.dto.GymDto;
import reservationservice.service.GymService;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/gym")
public class GymController {
    private GymService gymService;

    public GymController(GymService gymService){
        this.gymService = gymService;
    }

    @ApiOperation(value = "Get all movies")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "What page number you want", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "Number of items to return", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @GetMapping
    public ResponseEntity<Page<GymDto>> findAll(@RequestHeader("Authorization") String authorization, @ApiIgnore Pageable pageable) {
        return new ResponseEntity<>(gymService.findAll(pageable), HttpStatus.OK);
    }
    @ApiOperation(value = "edit")
    @PutMapping("/profile")
    public ResponseEntity<String> editProfile(@RequestBody GymDto clientProfileEditDto) {
        // Get the authenticated user's ID (you may use Spring Security for this)
        Integer gymId = clientProfileEditDto.getId();
        String name = clientProfileEditDto.getName();
        String description = clientProfileEditDto.getShortDescription();
        int numberOfPersonalTrainers = clientProfileEditDto.getNumberOfPersonalTrainers();
        

        System.out.println(clientProfileEditDto.toString());
        // Update user profile using the provided data
        gymService.editGymProfile(gymId,name,description,numberOfPersonalTrainers);

        return ResponseEntity.ok("Profile updated successfully");
    }

    @GetMapping("/sort")
    public ResponseEntity<List<GymDto>> findBySort(@RequestHeader("Authorization") String authorization) {
        return new ResponseEntity<>(gymService.getAllTrainingsSortedByPocetak(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GymDto> findById(@RequestHeader("Authorization") String authorization, @PathVariable("id") Integer id) {
        return new ResponseEntity<>(gymService.findbyId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GymDto> add(@RequestHeader("Authorization") String authorization, @RequestBody @Valid GymCreateDto gymCreateDto) {
        return new ResponseEntity<>(gymService.add(gymCreateDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String authorization, @PathVariable("id") Integer id) {
        gymService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

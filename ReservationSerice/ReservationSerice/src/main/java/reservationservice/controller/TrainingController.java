package reservationservice.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reservationservice.domain.Tip;
import reservationservice.domain.TrainingType;
import reservationservice.dto.TrainingCreateDto;
import reservationservice.dto.TrainingDto;
import reservationservice.service.TrainingService;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/training")
public class TrainingController {
    private TrainingService trainingService;

    public TrainingController(TrainingService trainingService){
        this.trainingService = trainingService;
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
    public ResponseEntity<Page<TrainingDto>> findAll(@RequestHeader("Authorization") String authorization, @ApiIgnore Pageable pageable) {
        return new ResponseEntity<>(trainingService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingDto> findById(@RequestHeader("Authorization") String authorization, @PathVariable("id") Integer id) {
        return new ResponseEntity<>(trainingService.findbyId(id), HttpStatus.OK);
    }

    @GetMapping("/trener/{trener}")
    public ResponseEntity<List<TrainingDto>> findByTrener(@RequestHeader("Authorization") String authorization, @PathVariable("trener") String trener) {
        return new ResponseEntity<>(trainingService.findByTrener(trener), HttpStatus.OK);
    }

    @GetMapping("/sort")
    public ResponseEntity<List<TrainingDto>> findBySort(@RequestHeader("Authorization") String authorization) {
        return new ResponseEntity<>(trainingService.getAllTrainingsSortedByPocetak(), HttpStatus.OK);
    }


    @GetMapping("/filter/{localDate}")
    public ResponseEntity<List<TrainingDto>> findByDatum(@RequestHeader("Authorization") String authorization, @PathVariable("localDate") LocalDate localDate) {
        return new ResponseEntity<>(trainingService.findByDate(localDate), HttpStatus.OK);
    }

    @GetMapping("/type/{tip}")
    public ResponseEntity<List<TrainingDto>> findByType(@RequestHeader("Authorization") String authorization, @PathVariable("tip") Tip tip) {
        return new ResponseEntity<>(trainingService.findByTrainingType(tip), HttpStatus.OK);
    }
    @GetMapping("/day/{dan}")
    public ResponseEntity<List<TrainingDto>> findByDay(@RequestHeader("Authorization") String authorization, @PathVariable("dan") String dan) {
        return new ResponseEntity<>(trainingService.findByDayOfTheWeek(dan), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<TrainingDto> add(@RequestHeader("Authorization") String authorization, @RequestBody @Valid TrainingCreateDto trainingCreateDto) {
        return new ResponseEntity<>(trainingService.add(trainingCreateDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String authorization, @PathVariable("id") Integer id) {
        trainingService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

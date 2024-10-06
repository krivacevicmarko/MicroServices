package reservationservice.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reservationservice.dto.TrainingTypeCreateDto;
import reservationservice.dto.TrainingTypeDto;
import reservationservice.service.TrainingTypeService;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping("/trainingtype")
public class TrainingTypeController {
    private TrainingTypeService trainingTypeService;

    public TrainingTypeController(TrainingTypeService trainingTypeService){
        this.trainingTypeService = trainingTypeService;
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
    public ResponseEntity<Page<TrainingTypeDto>> findAll(@RequestHeader("Authorization") String authorization, @ApiIgnore Pageable pageable) {
        return new ResponseEntity<>(trainingTypeService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingTypeDto> findById(@RequestHeader("Authorization") String authorization, @PathVariable("id") Integer id) {
        return new ResponseEntity<>(trainingTypeService.findbyId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TrainingTypeDto> add(@RequestHeader("Authorization") String authorization, @RequestBody @Valid TrainingTypeCreateDto trainingTypeCreateDto) {
        return new ResponseEntity<>(trainingTypeService.add(trainingTypeCreateDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String authorization, @PathVariable("id") Integer id) {
        trainingTypeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

package com.test.parking.controller;

import com.test.parking.model.ParkingDataSourceConfig;
import com.test.parking.service.ParkingDataSourceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/parkingDataSource")
public class parkingDataSourceController {

    private final ParkingDataSourceService parkingDataSourceService;

    public parkingDataSourceController(ParkingDataSourceService parkingDataSourceService) {
        this.parkingDataSourceService = parkingDataSourceService;
    }

    @PostMapping()
    @ApiOperation(value = "Create a new parking data sources config for a new city")
    public ResponseEntity<ParkingDataSourceConfig> createParkingDataSource(@RequestBody @Valid ParkingDataSourceConfig config) {
            ParkingDataSourceConfig newParkingDataSource = parkingDataSourceService.createParkingDataSources(config);
            return ResponseEntity.status(HttpStatus.CREATED).body(newParkingDataSource);
    }

    @GetMapping("/all")
    @ApiOperation(value = "Retrieve all parking data sources for all cities")
    public ResponseEntity<List<ParkingDataSourceConfig>> getAllParkingDataSources() {
        return ResponseEntity.status(HttpStatus.OK).body(parkingDataSourceService.getAllParkingDataSources());
    }

}

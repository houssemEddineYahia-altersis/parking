package com.test.parking.controller;

import com.test.parking.exception.AlreadyExistsException;
import com.test.parking.model.ParkingDataSourceConfig;
import com.test.parking.service.ParkingDataSourceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parkingDataSource")
public class parkingDataSourceController {

    private ParkingDataSourceService parkingDataSourceService;

    @Autowired
    public parkingDataSourceController(ParkingDataSourceService parkingDataSourceService) {
        this.parkingDataSourceService = parkingDataSourceService;
    }

    @PostMapping()
    @ApiOperation(value = "Create a new parking data source based on the city.")
    public ResponseEntity<?> createParkingDataSource(@RequestBody ParkingDataSourceConfig config) {
        try {
            ParkingDataSourceConfig newParkingDataSource = parkingDataSourceService.createParkingDataSources(config);
            return ResponseEntity.status(HttpStatus.CREATED).body(newParkingDataSource);
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("City already exists.");
        }
    }

    @GetMapping("/all")
    @ApiOperation(value = "Retrieve all parking data sources.")
    public List<ParkingDataSourceConfig> getAllParkingDataSources() {
        return parkingDataSourceService.getAllParkingDataSources();
    }

}

package com.test.parking.controller;

import com.test.parking.dto.RecordData;
import com.test.parking.service.ParkingDataInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parkingInfo")
public class parkingDataInfoController {

    private final ParkingDataInfoService parkingDataService;
    public parkingDataInfoController(ParkingDataInfoService parkingDataService) {
        this.parkingDataService = parkingDataService;
    }
    @GetMapping("/{city}")
    @ApiOperation(value = "Retrieve the parking list of a specific city")
    public ResponseEntity<RecordData> getParkingList(@PathVariable String city) {
        return ResponseEntity.status(HttpStatus.OK).body(parkingDataService.getParkingList(city));
    }

    @GetMapping("/availability/{city}")
    @ApiOperation(value = "Retrieve the parking list of a specific city with their availabilities information")
    public ResponseEntity<RecordData> getAvailableParking(@PathVariable String city) {
        return ResponseEntity.status(HttpStatus.OK).body(parkingDataService.getAvailableParkingList(city));
    }
}

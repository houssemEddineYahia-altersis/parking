package com.test.parking.controller;

import com.test.parking.dto.NearbyParkingRequest;
import com.test.parking.dto.NearbyParkingResponse;
import com.test.parking.service.ParkingService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }
    @PostMapping("/nearby")
    @ApiOperation(value = "retrieve the list of nearby parking based on the geometric coordinates")
    public ResponseEntity<NearbyParkingResponse> nearbyParking(@RequestBody @Valid NearbyParkingRequest nearbyParkingRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(parkingService.nearbyParking(nearbyParkingRequest));
    }
}

package com.test.parking.controller;

import com.test.parking.dto.NearbyParkingRequest;
import com.test.parking.dto.NearbyParkingResponse;
import com.test.parking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    private ParkingService parkingService;
    @Autowired
    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }
    @PostMapping("/nearby/{city}/{latitude}/{longitude}")
    public NearbyParkingResponse nearbyParking(@PathVariable String city, @PathVariable Double latitude, @PathVariable Double longitude) {

        NearbyParkingRequest nearbyParkingRequest = NearbyParkingRequest.builder()
                .city(city).latitude(latitude).longitude(longitude).build();
        return parkingService.nearbyParking(nearbyParkingRequest);

    }
}

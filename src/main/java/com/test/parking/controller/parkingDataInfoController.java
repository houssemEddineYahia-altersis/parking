package com.test.parking.controller;

import com.test.parking.dto.RecordData;
import com.test.parking.service.ParkingDataInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parkingInfo")
public class parkingDataInfoController {

    @Autowired
    ParkingDataInfoService parkingDataService;

    @GetMapping("/{city}")
    public RecordData getParkingList(@PathVariable String city) {
        return parkingDataService.getParkingList(city);
    }

    @GetMapping("/available/{city}")
    public RecordData getAvailableParking(@PathVariable String city) {
        return parkingDataService.getAvailableParkingList(city);
    }
}

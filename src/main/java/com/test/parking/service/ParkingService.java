package com.test.parking.service;

import com.test.parking.dto.NearbyParkingRequest;
import com.test.parking.dto.NearbyParkingResponse;

public interface ParkingService {
    NearbyParkingResponse nearbyParking(NearbyParkingRequest nearbyParkingRequest);
}

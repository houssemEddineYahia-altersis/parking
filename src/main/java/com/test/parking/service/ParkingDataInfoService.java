package com.test.parking.service;

import com.test.parking.dto.RecordData;

public interface ParkingDataInfoService {

    RecordData getParkingList(String city);
    RecordData getAvailableParkingList(String city);
}

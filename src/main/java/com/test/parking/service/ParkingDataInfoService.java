package com.test.parking.service;

import com.test.parking.dto.RecordData;
import com.test.parking.model.ParkingDataSourceConfig;

public interface ParkingDataInfoService {

    RecordData getParkingList(String city);
    RecordData getAvailableParkingList(String city);
}

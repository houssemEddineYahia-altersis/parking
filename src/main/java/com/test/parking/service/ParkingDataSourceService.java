package com.test.parking.service;

import com.test.parking.model.ParkingDataSourceConfig;

import java.util.List;

public interface ParkingDataSourceService {
     ParkingDataSourceConfig createParkingDataSource(ParkingDataSourceConfig config);

     List<ParkingDataSourceConfig> getAllParkingDataSource();
}

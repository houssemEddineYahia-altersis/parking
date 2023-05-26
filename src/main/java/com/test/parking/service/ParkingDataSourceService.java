package com.test.parking.service;

import com.test.parking.model.ParkingDataSourceConfig;

import java.util.List;

public interface ParkingDataSourceService {
     ParkingDataSourceConfig createParkingDataSources(ParkingDataSourceConfig config);

     List<ParkingDataSourceConfig> getAllParkingDataSources();

     ParkingDataSourceConfig findByCity(String city);

}

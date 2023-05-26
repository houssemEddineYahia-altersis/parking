package com.test.parking.service.impl;

import com.test.parking.client.ParkingDataClientAPI;
import com.test.parking.dto.RecordData;
import com.test.parking.model.ParkingDataSourceConfig;
import com.test.parking.repository.ParkingDataSourceRepository;
import com.test.parking.service.ParkingDataInfoService;
import com.test.parking.service.ParkingDataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingDataInfoServiceImpl implements ParkingDataInfoService {

    private final ParkingDataClientAPI parkingDataClientAPI;

    private final ParkingDataSourceService parkingDataSourceService;


    @Autowired
    public ParkingDataInfoServiceImpl(ParkingDataSourceService parkingDataSourceService,ParkingDataClientAPI parkingDataClientAPI) {
        this.parkingDataClientAPI = parkingDataClientAPI;
        this.parkingDataSourceService = parkingDataSourceService;
    }
    @Override
    public RecordData getParkingList(String city) {
        ParkingDataSourceConfig dataConfig = parkingDataSourceService.findByCity(city);
        return parkingDataClientAPI.getParkingList(dataConfig.getParkingDataUrl());
    }

    @Override
    public RecordData getAvailableParkingList(String city) {
        ParkingDataSourceConfig dataConfig = parkingDataSourceService.findByCity(city);
        return parkingDataClientAPI.getAvailableParking(dataConfig.getParkingAvailableUrl());
    }
}

package com.test.parking.service.impl;

import com.test.parking.client.ParkingDataClientAPI;
import com.test.parking.dto.RecordData;
import com.test.parking.exception.EmptyDataSetException;
import com.test.parking.model.ParkingDataSourceConfig;
import com.test.parking.service.ParkingDataInfoService;
import com.test.parking.service.ParkingDataSourceService;
import org.springframework.stereotype.Service;

@Service
public class ParkingDataInfoServiceImpl implements ParkingDataInfoService {

    private final ParkingDataClientAPI parkingDataClientAPI;

    private final ParkingDataSourceService parkingDataSourceService;

    public ParkingDataInfoServiceImpl(ParkingDataSourceService parkingDataSourceService,ParkingDataClientAPI parkingDataClientAPI) {
        this.parkingDataClientAPI = parkingDataClientAPI;
        this.parkingDataSourceService = parkingDataSourceService;
    }
    @Override
    public RecordData getParkingList(String city) {
        ParkingDataSourceConfig dataConfig = parkingDataSourceService.findByCity(city);
        if(dataConfig == null) throw new EmptyDataSetException();
        return parkingDataClientAPI.getParkingList(dataConfig.getParkingDataUrl());
    }

    @Override
    public RecordData getAvailableParkingList(String city) {
        ParkingDataSourceConfig dataConfig = parkingDataSourceService.findByCity(city);
        if(dataConfig == null) throw new EmptyDataSetException();
        return parkingDataClientAPI.getAvailableParking(dataConfig.getParkingAvailableUrl());
    }
}

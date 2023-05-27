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

    /**
     * Retrieves the parking list of a specific city.
     *
     * @param city The city for which to retrieve the parking list.
     * @return The parking list of the specified city.
     * @throws EmptyDataSetException if no data source is found for the city.
     */
    @Override
    public RecordData getParkingList(String city) {
        ParkingDataSourceConfig dataConfig = parkingDataSourceService.findByCity(city);
        if(dataConfig == null) throw new EmptyDataSetException();
        return parkingDataClientAPI.getParkingList(dataConfig.getParkingDataUrl());
    }

    /**
     * Retrieve the parking list of a specific city with their availabilities.
     *
     * @param city The city for which to retrieve the available parking list.
     * @return The available parking list of the specified city.
     * @throws EmptyDataSetException if no data source is found for the city.
     */
    @Override
    public RecordData getAvailableParkingList(String city) {
        ParkingDataSourceConfig dataConfig = parkingDataSourceService.findByCity(city);
        if(dataConfig == null) throw new EmptyDataSetException();
        return parkingDataClientAPI.getAvailableParking(dataConfig.getParkingAvailabilityUrl());
    }
}

package com.test.parking.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.test.parking.client.ParkingDataClientAPI;
import com.test.parking.dto.RecordData;
import com.test.parking.exception.EmptyDataSetException;
import com.test.parking.model.ParkingDataSourceConfig;
import com.test.parking.service.ParkingDataSourceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ParkingDataInfoServiceImplTest {
    @MockBean
    private ParkingDataClientAPI parkingDataClientAPI;

    @Autowired
    private ParkingDataInfoServiceImpl parkingDataInfoServiceImpl;

    @MockBean
    private ParkingDataSourceService parkingDataSourceService;

    /**
     * Method under test: {@link ParkingDataInfoServiceImpl#getParkingList(String)}
     */
    @Test
    void testGetParkingList_ValidCity_ReturnsRecordData() {
        String city = "Paris";
        ParkingDataSourceConfig parkingDataSourceConfig = new ParkingDataSourceConfig();
        parkingDataSourceConfig.setCity(city);
        parkingDataSourceConfig.setId("42");
        parkingDataSourceConfig.setParkingAvailableUrl("https://example.org/example");
        parkingDataSourceConfig.setParkingDataUrl("https://example.org/example");

        when(parkingDataSourceService.findByCity(city)).thenReturn(parkingDataSourceConfig);
        RecordData recordData = new RecordData();
        when(parkingDataClientAPI.getParkingList(parkingDataSourceConfig.getParkingDataUrl())).thenReturn(recordData);

        assertSame(recordData, parkingDataInfoServiceImpl.getParkingList(city));
        verify(parkingDataSourceService).findByCity(city);
        verify(parkingDataClientAPI).getParkingList(parkingDataSourceConfig.getParkingDataUrl());
    }

    /**
     * Method under test: {@link ParkingDataInfoServiceImpl#getParkingList(String)}
     */
    @Test
    void testGetParkingList_InvalidCity_ThrowsEmptyDataSetException() {
        String city = "Paris";
        ParkingDataSourceConfig parkingDataSourceConfig = new ParkingDataSourceConfig();
        parkingDataSourceConfig.setCity(city);
        parkingDataSourceConfig.setId("42");
        parkingDataSourceConfig.setParkingAvailableUrl("https://example.org/example");
        parkingDataSourceConfig.setParkingDataUrl("https://example.org/example");

        when(parkingDataSourceService.findByCity(city)).thenReturn(parkingDataSourceConfig);
        when(parkingDataClientAPI.getParkingList(parkingDataSourceConfig.getParkingDataUrl())).thenThrow(new EmptyDataSetException());

        assertThrows(EmptyDataSetException.class, () -> parkingDataInfoServiceImpl.getParkingList(city));
        verify(parkingDataSourceService).findByCity(city);
        verify(parkingDataClientAPI).getParkingList(parkingDataSourceConfig.getParkingDataUrl());
    }

    /**
     * Method under test: {@link ParkingDataInfoServiceImpl#getAvailableParkingList(String)}
     */
    @Test
    void testGetAvailableParkingList_ValidCity_ReturnsRecordData() {
        String city = "Paris";
        ParkingDataSourceConfig parkingDataSourceConfig = new ParkingDataSourceConfig();
        parkingDataSourceConfig.setCity(city);
        parkingDataSourceConfig.setId("42");
        parkingDataSourceConfig.setParkingAvailableUrl("https://example.org/example");
        parkingDataSourceConfig.setParkingDataUrl("https://example.org/example");

        when(parkingDataSourceService.findByCity(city)).thenReturn(parkingDataSourceConfig);
        RecordData recordData = new RecordData();
        when(parkingDataClientAPI.getAvailableParking(parkingDataSourceConfig.getParkingAvailableUrl())).thenReturn(recordData);

        assertSame(recordData, parkingDataInfoServiceImpl.getAvailableParkingList(city));
        verify(parkingDataSourceService).findByCity(city);
        verify(parkingDataClientAPI).getAvailableParking(parkingDataSourceConfig.getParkingAvailableUrl());
    }

    /**
     * Method under test: {@link ParkingDataInfoServiceImpl#getAvailableParkingList(String)}
     */
    @Test
    void testGetAvailableParkingList_InvalidCity_ThrowsEmptyDataSetException() {
        String city = "Paris";
        ParkingDataSourceConfig parkingDataSourceConfig = new ParkingDataSourceConfig();
        parkingDataSourceConfig.setCity(city);
        parkingDataSourceConfig.setId("42");
        parkingDataSourceConfig.setParkingAvailableUrl("https://example.org/example");
        parkingDataSourceConfig.setParkingDataUrl("https://example.org/example");

        when(parkingDataSourceService.findByCity(city)).thenReturn(parkingDataSourceConfig);
        when(parkingDataClientAPI.getAvailableParking(parkingDataSourceConfig.getParkingAvailableUrl())).thenThrow(new EmptyDataSetException());

        assertThrows(EmptyDataSetException.class, () -> parkingDataInfoServiceImpl.getAvailableParkingList(city));
        verify(parkingDataSourceService).findByCity(city);
        verify(parkingDataClientAPI).getAvailableParking(parkingDataSourceConfig.getParkingAvailableUrl());
    }
}


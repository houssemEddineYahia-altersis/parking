package com.test.parking.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.test.parking.exception.CityAlreadyExistsException;
import com.test.parking.exception.CityNotFoundException;
import com.test.parking.exception.EmptyDataSetException;
import com.test.parking.model.ParkingDataSourceConfig;
import com.test.parking.repository.ParkingDataSourceRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ParkingDataSourceServiceImplTest {
    @MockBean
    private ParkingDataSourceRepository parkingDataSourceRepository;

    @Autowired
    private ParkingDataSourceServiceImpl parkingDataSourceServiceImpl;

    /**
     * Method under test: {@link ParkingDataSourceServiceImpl#createParkingDataSources(ParkingDataSourceConfig)}
     */
    @Test
    void testCreateParkingDataSources_NewCity_SuccessfullyCreatesConfig() {
        ParkingDataSourceConfig parkingDataSourceConfig = new ParkingDataSourceConfig();
        parkingDataSourceConfig.setCity("Paris");
        parkingDataSourceConfig.setId("42");
        parkingDataSourceConfig.setParkingAvailableUrl("https://example.org/example");
        parkingDataSourceConfig.setParkingDataUrl("https://example.org/example");
        when(parkingDataSourceRepository.existsByCity("Paris")).thenReturn(false);
        when(parkingDataSourceRepository.save(parkingDataSourceConfig)).thenReturn(parkingDataSourceConfig);

        ParkingDataSourceConfig parkingDataSourceConfig1 = new ParkingDataSourceConfig();
        parkingDataSourceConfig1.setCity("Paris");
        parkingDataSourceConfig1.setId("42");
        parkingDataSourceConfig1.setParkingAvailableUrl("https://example.org/example");
        parkingDataSourceConfig1.setParkingDataUrl("https://example.org/example");

        assertSame(parkingDataSourceConfig,
                parkingDataSourceServiceImpl.createParkingDataSources(parkingDataSourceConfig1));
        verify(parkingDataSourceRepository).existsByCity("Paris");
        verify(parkingDataSourceRepository).save(parkingDataSourceConfig);
    }

    /**
     * Method under test: {@link ParkingDataSourceServiceImpl#createParkingDataSources(ParkingDataSourceConfig)}
     */
    @Test
    void testCreateParkingDataSources_ExistingCity_ThrowsCityAlreadyExistsException() {
        ParkingDataSourceConfig parkingDataSourceConfig = new ParkingDataSourceConfig();
        parkingDataSourceConfig.setCity("Paris");
        parkingDataSourceConfig.setId("42");
        parkingDataSourceConfig.setParkingAvailableUrl("https://example.org/example");
        parkingDataSourceConfig.setParkingDataUrl("https://example.org/example");

        when(parkingDataSourceRepository.existsByCity("Paris")).thenReturn(true);
        when(parkingDataSourceRepository.save(parkingDataSourceConfig)).thenReturn(parkingDataSourceConfig);

        ParkingDataSourceConfig parkingDataSourceConfig1 = new ParkingDataSourceConfig();
        parkingDataSourceConfig1.setCity("Paris");
        parkingDataSourceConfig1.setId("42");
        parkingDataSourceConfig1.setParkingAvailableUrl("https://example.org/example");
        parkingDataSourceConfig1.setParkingDataUrl("https://example.org/example");

        assertThrows(CityAlreadyExistsException.class,
                () -> parkingDataSourceServiceImpl.createParkingDataSources(parkingDataSourceConfig1));
        verify(parkingDataSourceRepository).existsByCity("Paris");
    }

    /**
     * Method under test: {@link ParkingDataSourceServiceImpl#getAllParkingDataSources()}
     */
    @Test
    void testGetAllParkingDataSources_NoData_ThrowsEmptyDataSetException() {
        when(parkingDataSourceRepository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(EmptyDataSetException.class, () -> parkingDataSourceServiceImpl.getAllParkingDataSources());
        verify(parkingDataSourceRepository).findAll();
    }

    /**
     * Method under test: {@link ParkingDataSourceServiceImpl#getAllParkingDataSources()}
     */
    @Test
    void testGetAllParkingDataSources_DataExists_ReturnsParkingDataSourceConfigList() {
        ParkingDataSourceConfig parkingDataSourceConfig = new ParkingDataSourceConfig();
        parkingDataSourceConfig.setCity("Paris");
        parkingDataSourceConfig.setId("42");
        parkingDataSourceConfig.setParkingAvailableUrl("https://example.org/example");
        parkingDataSourceConfig.setParkingDataUrl("https://example.org/example");

        ArrayList<ParkingDataSourceConfig> parkingDataSourceConfigList = new ArrayList<>();
        parkingDataSourceConfigList.add(parkingDataSourceConfig);

        when(parkingDataSourceRepository.findAll()).thenReturn(parkingDataSourceConfigList);

        List<ParkingDataSourceConfig> actualAllParkingDataSources = parkingDataSourceServiceImpl
                .getAllParkingDataSources();

        assertSame(parkingDataSourceConfigList, actualAllParkingDataSources);
        assertEquals(1, actualAllParkingDataSources.size());
        verify(parkingDataSourceRepository).findAll();
    }

    /**
     * Method under test: {@link ParkingDataSourceServiceImpl#findByCity(String)}
     */
    @Test
    void testFindByCity_ValidCity_ReturnsParkingDataSourceConfig() {
        ParkingDataSourceConfig parkingDataSourceConfig = new ParkingDataSourceConfig();
        parkingDataSourceConfig.setCity("Paris");
        parkingDataSourceConfig.setId("42");
        parkingDataSourceConfig.setParkingAvailableUrl("https://example.org/example");
        parkingDataSourceConfig.setParkingDataUrl("https://example.org/example");

        when(parkingDataSourceRepository.findByCity("Paris")).thenReturn(parkingDataSourceConfig);

        assertSame(parkingDataSourceConfig, parkingDataSourceServiceImpl.findByCity("Paris"));
        verify(parkingDataSourceRepository).findByCity("Paris");
    }

    /**
     * Method under test: {@link ParkingDataSourceServiceImpl#findByCity(String)}
     */
    @Test
    void testFindByCity_InvalidCity_ReturnsParkingDataSourceConfig() {
        when(parkingDataSourceRepository.findByCity("Paris")).thenReturn(null);

        assertThrows(CityNotFoundException.class, () -> parkingDataSourceServiceImpl.findByCity("Paris"));
        verify(parkingDataSourceRepository).findByCity("Paris");
    }
}


package com.test.parking.service.impl;

import com.test.parking.exception.CityAlreadyExistsException;
import com.test.parking.exception.CityNotFoundException;
import com.test.parking.exception.EmptyDataSetException;
import com.test.parking.model.ParkingDataSourceConfig;
import com.test.parking.repository.ParkingDataSourceRepository;
import com.test.parking.service.ParkingDataSourceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingDataSourceServiceImpl implements ParkingDataSourceService {

    private final ParkingDataSourceRepository parkingDataSourceRepository;

    public ParkingDataSourceServiceImpl(ParkingDataSourceRepository parkingDataSourceRepository) {
        this.parkingDataSourceRepository = parkingDataSourceRepository;
    }

    /**
     * Creates a new Parking Data Sources configuration.
     *
     * @param config The parking data source configuration to create.
     * @return The created parking data source configuration.
     * @throws CityAlreadyExistsException if the city specified in the new configuration already exists.
     */
    @Override
    public ParkingDataSourceConfig createParkingDataSources(ParkingDataSourceConfig config) {
        String city = config.getCity();
        if (parkingDataSourceRepository.existsByCity(city)) {
            throw new CityAlreadyExistsException();
        }
        return parkingDataSourceRepository.save(config);
    }

    /**
     * Retrieves all parking data sources.
     *
     * @return A list of all parking data sources.
     * @throws EmptyDataSetException if no parking data sources configurations are found.
     */
    @Override
    public List<ParkingDataSourceConfig> getAllParkingDataSources() {
        List<ParkingDataSourceConfig> parkingDataSourceConfig = parkingDataSourceRepository.findAll();
        if(parkingDataSourceConfig.isEmpty()) throw new EmptyDataSetException();
        return parkingDataSourceConfig;
    }

    /**
     * Retrieves parking data sources configuration for a specific city.
     *
     * @param city The city name
     * @return The parking data source configuration of the specified city.
     * @throws CityNotFoundException if the parking data sources configuration of the specified city is not found.
     */
    @Override
    public ParkingDataSourceConfig findByCity(String city) {
        ParkingDataSourceConfig parkingDataSourceConfig = parkingDataSourceRepository.findByCity(city);
        if(parkingDataSourceConfig == null) throw new CityNotFoundException();
        return parkingDataSourceConfig;
    }

}

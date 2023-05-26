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
     * Crée une nouvelle configuration de source de données des Parkings.
     *
     * @param config La configuration de source de données de parking à créer.
     * @return La configuration de source de données de parking créée.
     * @throws CityAlreadyExistsException Si la ville spécifiée dans la configuration existe déjà.
     */
    @Override
    public ParkingDataSourceConfig createParkingDataSources(ParkingDataSourceConfig config) {
        String city = config.getCity();
        if (parkingDataSourceRepository.existsByCity(city)) {
            throw new CityAlreadyExistsException();
        }
        return parkingDataSourceRepository.save(config);
    }

    @Override
    public List<ParkingDataSourceConfig> getAllParkingDataSources() {
        List<ParkingDataSourceConfig> parkingDataSourceConfig = parkingDataSourceRepository.findAll();
        if(parkingDataSourceConfig.isEmpty()) throw new EmptyDataSetException();
        return parkingDataSourceConfig;
    }
    @Override
    public ParkingDataSourceConfig findByCity(String city) {
        ParkingDataSourceConfig parkingDataSourceConfig = parkingDataSourceRepository.findByCity(city);
        if(parkingDataSourceConfig == null) throw new CityNotFoundException();
        return parkingDataSourceConfig;
    }

}

package com.test.parking.service.impl;

import com.test.parking.exception.AlreadyExistsException;
import com.test.parking.model.ParkingDataSourceConfig;
import com.test.parking.repository.ParkingDataSourceRepository;
import com.test.parking.service.ParkingDataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingDataSourceServiceImpl implements ParkingDataSourceService {

    private final ParkingDataSourceRepository parkingDataSourceRepository;

    @Autowired
    public ParkingDataSourceServiceImpl(ParkingDataSourceRepository parkingDataSourceRepository) {
        this.parkingDataSourceRepository = parkingDataSourceRepository;
    }
    /**
     * Crée une nouvelle configuration de source de données des Parkings.
     *
     * @param config La configuration de source de données de parking à créer.
     * @return La configuration de source de données de parking créée.
     * @throws AlreadyExistsException Si la ville spécifiée dans la configuration existe déjà.
     */
    @Override
    public ParkingDataSourceConfig createParkingDataSources(ParkingDataSourceConfig config) {
        String city = config.getCity();
        if (parkingDataSourceRepository.existsByCity(city)) {
            throw new AlreadyExistsException("City already exists.");
        }
        return parkingDataSourceRepository.save(config);
    }

    @Override
    public List<ParkingDataSourceConfig> getAllParkingDataSources() {
        return parkingDataSourceRepository.findAll();
    }
    @Override
    public ParkingDataSourceConfig findByCity(String city) {
        return parkingDataSourceRepository.findByCity(city);
    }

}

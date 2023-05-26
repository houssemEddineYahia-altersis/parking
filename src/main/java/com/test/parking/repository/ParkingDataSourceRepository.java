package com.test.parking.repository;

import com.test.parking.model.ParkingDataSourceConfig;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingDataSourceRepository extends MongoRepository<ParkingDataSourceConfig, String> {
    boolean existsByCity(String city);
}

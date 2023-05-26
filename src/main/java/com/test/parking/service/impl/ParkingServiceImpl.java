package com.test.parking.service.impl;

import com.test.parking.dto.*;
import com.test.parking.dto.Record;
import com.test.parking.exception.IllegalDataInput;
import com.test.parking.service.ParkingDataInfoService;
import com.test.parking.service.ParkingDataSourceService;
import com.test.parking.service.ParkingService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ParkingServiceImpl implements ParkingService {

    private final ParkingDataSourceService parkingDataSourceService;

    private final ParkingDataInfoService parkingDatainfoService;


    public ParkingServiceImpl(ParkingDataSourceService parkingDataSourceService, ParkingDataInfoService parkingDatainfoService) {
        this.parkingDataSourceService = parkingDataSourceService;
        this.parkingDatainfoService = parkingDatainfoService;
    }
    @Override
    public NearbyParkingResponse nearbyParking(NearbyParkingRequest nearbyParkingRequest) {

        // 1. GET PARKING LIST
        RecordData parkingList = getParkingList(nearbyParkingRequest.getCity());

        // 2. GET AVAILABLE PARKING
        RecordData availableParking = getAvailableParking(nearbyParkingRequest.getCity());

        if(availableParking.getRecords().size()>parkingList.getRecords().size()){
            throw new IllegalDataInput();
        }
        // 3. RETURN NEARBY PARKING (ALL PARKING + PLACES)

        return getNearbyParking(parkingList, availableParking, nearbyParkingRequest);
    }

    private RecordData getParkingList(String city){
        return parkingDatainfoService.getParkingList(city);
    }

    private RecordData getAvailableParking(String city){
        return parkingDatainfoService.getAvailableParkingList(city);
    }

    private NearbyParkingResponse getNearbyParking(RecordData parkingList, RecordData availableParkingList, NearbyParkingRequest nearestParkingRequest){

        NearbyParkingResponse nearbyParking = NearbyParkingResponse.builder().build();

        ArrayList<Parking> parkingResult = new ArrayList<>();

        parkingList.getRecords().forEach(record -> {
            Field field = record.getFields();
            if (field != null){
                Parking parking = Parking.builder().nom(field.getNom())
                        .capacity(field.getCapacity())
                        .places(0)
                        .geoPoint2d(field.getGeoPoint2d())
                        .build();

            if(field.getGeoPoint2d() != null && field.getGeoPoint2d().length == 2) {
                parking.setDistance(calculateDistance(field.getGeoPoint2d()[0], field.getGeoPoint2d()[1], nearestParkingRequest.getLatitude(), nearestParkingRequest.getLongitude()));
            }

           Record recordItem = availableParkingList.getRecords().stream().filter(availableParking ->
                   record.getFields().getNom().equalsIgnoreCase(availableParking.getFields().getNom())).findFirst().orElse(null);
          if(recordItem != null) {
              parking.setPlaces(recordItem.getFields().getPlaces());
          }

            parkingResult.add(parking);
        }
        });

        List<Parking> sortedParking = parkingResult.stream()
                .sorted(Comparator.comparingDouble(Parking::getDistance)).toList();

        nearbyParking.setParking(sortedParking);

        return nearbyParking;
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double EARTH_RADIUS = 6371000;

        // Convert latitude and longitude to radians
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);
        // Calculate the differences between coordinates
        double deltaLat = lat2Rad - lat1Rad;

        double deltaLon = lon2Rad - lon1Rad;
        // Haversine formula
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) + Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Calculate the distance in meters
        double distance = EARTH_RADIUS * c;

        return distance;

    }
}

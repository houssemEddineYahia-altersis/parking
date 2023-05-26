package com.test.parking.service.impl;

import com.test.parking.dto.NearbyParkingRequest;
import com.test.parking.dto.NearbyParkingResponse;
import com.test.parking.dto.Parking;
import com.test.parking.dto.RecordData;
import com.test.parking.service.ParkingDataInfoService;
import com.test.parking.service.ParkingDataSourceService;
import com.test.parking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ParkingServiceImpl implements ParkingService {

    private ParkingDataSourceService parkingDataSourceService;

    private ParkingDataInfoService parkingDatainfoService;

    @Autowired
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

        // 3. RETURN NEARBY PARKING (ALL PARKING + PLACES)

        return getNearbyParking(parkingList, availableParking, nearbyParkingRequest);
    }

    public RecordData getParkingList(String city){
        return parkingDatainfoService.getParkingList(city);
    }

    public RecordData getAvailableParking(String city){
        return parkingDatainfoService.getAvailableParkingList(city);
    }

    public NearbyParkingResponse getNearbyParking(RecordData parkingList, RecordData availableParkingList, NearbyParkingRequest nearestParkingRequest){

        NearbyParkingResponse nearbyParking = NearbyParkingResponse.builder().build();

        ArrayList<Parking> parkingResult = new ArrayList<>();

        parkingList.getRecords().forEach(record -> {

            Parking parking = Parking.builder().build();

            parking.setNom(record.getFields().getNom());
            parking.setCapacity(record.getFields().getCapacity());
            parking.setPlaces(0);
            parking.setGeoPoint2d(record.getFields().getGeoPoint2d());

            parking.setDistance(calculateDistance(record.getFields().getGeoPoint2d()[0],record.getFields().getGeoPoint2d()[1],nearestParkingRequest.getLatitude(), nearestParkingRequest.getLongitude()));


            availableParkingList.getRecords().forEach(availableParking -> {

                if(record.getFields().getNom().equalsIgnoreCase(availableParking.getFields().getNom())){
                    parking.setPlaces(availableParking.getFields().getPlaces());
                }
            });
            parkingResult.add(parking);
        });

        List<Parking> sortedParking = parkingResult.stream()
                .sorted(Comparator.comparingDouble(Parking::getDistance)).toList();
        nearbyParking.setParking(sortedParking);

        return nearbyParking;
    }

    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
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

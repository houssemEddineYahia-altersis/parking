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

    /**
     * Retrieves nearby parking list from a specific geometric coordinates.
     *
     * @param nearbyParkingRequest The request object containing the city, latitude, and longitude of a location received
     * from a mobile device or a website
     * @return The response object containing the nearby parking list.
     * @throws IllegalDataInput if the available parking list is inconsistent with the total parking list.
     */
    @Override
    public NearbyParkingResponse getNearbyParkingList(NearbyParkingRequest nearbyParkingRequest) {

        RecordData parkingList = getParkingList(nearbyParkingRequest.getCity());

        RecordData availableParking = getAvailableParking(nearbyParkingRequest.getCity());

        if(availableParking.getRecords().size()>parkingList.getRecords().size()){
            throw new IllegalDataInput();
        }

        return getSortedNearbyParking(parkingList, availableParking, nearbyParkingRequest);
    }

    private RecordData getParkingList(String city){
        return parkingDatainfoService.getParkingList(city);
    }

    private RecordData getAvailableParking(String city){
        return parkingDatainfoService.getAvailableParkingList(city);
    }

    /**
     * Retrieve the list of parking sorted by proximity to a specific geometric coordinates.
     *
     * @param parkingList             The parking list.
     * @param availableParkingList    The available parking list.
     * @param nearbyParkingRequest    The request object containing geometric coordinates received from a mobile device or a website.
     * @return The response object containing the parking list sorted by proximity to the specific geometric coordinates.
     */
    private NearbyParkingResponse getSortedNearbyParking(RecordData parkingList, RecordData availableParkingList, NearbyParkingRequest nearbyParkingRequest){

        NearbyParkingResponse nearbyParkingList = NearbyParkingResponse.builder().build();

        ArrayList<Parking> parkingResultList = new ArrayList<>();

        parkingList.getRecords().forEach(record -> {
            Field currentParkingInfo = record.getFields();

            if (currentParkingInfo != null){
                Parking parking = Parking.builder().nom(currentParkingInfo.getNom())
                        .capacity(currentParkingInfo.getCapacity())
                        .places(0)
                        .geoPoint2d(currentParkingInfo.getGeoPoint2d())
                        .build();

                /* Verify the presence of latitude and longitude coordinates before starting to calculate the distance
                between the current parking and the received geometric coordinates
                */
                if(currentParkingInfo.getGeoPoint2d() != null && currentParkingInfo.getGeoPoint2d().length == 2) {
                parking.setDistance(calculateDistance(currentParkingInfo.getGeoPoint2d()[0],
                        currentParkingInfo.getGeoPoint2d()[1], nearbyParkingRequest.getLatitude(), nearbyParkingRequest.getLongitude()));
                }

                // Get the number of available places in the current parking
                Record recordItem = availableParkingList.getRecords().stream().filter(availableParking ->
                        currentParkingInfo.getNom().equalsIgnoreCase(availableParking.getFields().getNom())).findFirst().orElse(null);
                if(recordItem != null) {
                parking.setPlaces(recordItem.getFields().getPlaces());
                }

                parkingResultList.add(parking);
            }
        });

        // Sort parking by distance
        List<Parking> sortedParking = parkingResultList.stream()
                .sorted(Comparator.comparingDouble(Parking::getDistance)).toList();

        nearbyParkingList.setParking(sortedParking);

        return nearbyParkingList;
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

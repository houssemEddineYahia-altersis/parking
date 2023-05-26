package com.test.parking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NearbyParkingRequest {

    private String city;

    private Double latitude;

    private Double longitude;


}

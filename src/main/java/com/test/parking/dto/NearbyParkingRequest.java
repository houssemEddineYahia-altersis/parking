package com.test.parking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NearbyParkingRequest {
    @NotNull
    private String city;
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;
}

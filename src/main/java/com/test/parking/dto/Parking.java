package com.test.parking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Parking {
    private String nom;
    private Integer capacity;
    private Integer places;
    private double[] geoPoint2d;
    private Double distance;
}

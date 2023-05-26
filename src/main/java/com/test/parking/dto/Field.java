package com.test.parking.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Field {

    @JsonProperty("nom")
    private String nom;
    @JsonProperty("geo_point_2d")
    private double[] geoPoint2d;
    @JsonProperty("nb_places")
    private int capacity;
    @JsonProperty("places")
    private int places;

}

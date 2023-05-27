package com.test.parking.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotNull;

/**
 * Represents parking data sources configuration by city
 */
@Document(collection = "ParkingDataSource")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ParkingDataSourceConfig {
    /**
     * Unique identifier of the configuration.
     */
    @Id
    private String id;
    /**
     * Name of the city.
     */
    @NotNull
    private String city;
    /**
     * URL of the data source that provides the list of parking of a specific city.
     */
    @NotNull
    private String parkingDataUrl;
    /**
     * URL of the data source that provides parking availabilities information of a specific city.
     */
    @NotNull
    private String parkingAvailabilityUrl;
}

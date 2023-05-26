package com.test.parking.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

/**
 * Représente une configuration des sources de données de la liste des parkings par ville.
 */
@Document(collection = "ParkingDataSource")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ParkingDataSourceConfig {
    /**
     * Identifiant unique de la configuration.
     */
    @Id
    private String id;
    /**
     * Nom de la ville associée à la configuration.
     */
    @NotNull
    private String city;
    /**
     * URL de la source de données pour récupérer la liste des parkings spécifique à une ville.
     */
    @NotNull
    private String parkingDataUrl;
    /**
     * URL de la source de données pour récupérer les informations sur la disponibilité des parkings spécifique à une ville
     */
    @NotNull
    private String parkingAvailableUrl;
}

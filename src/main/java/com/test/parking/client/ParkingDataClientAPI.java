package com.test.parking.client;

import com.test.parking.dto.RecordData;
import com.test.parking.exception.EmptyThirdPartyResultException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ParkingDataClientAPI {

    private WebClient webClient;

    public ParkingDataClientAPI(WebClient webClient) {
        this.webClient = webClient;
    }

    public RecordData getParkingList(String parkingDataUrl) {

        try {
            return webClient
                    .get()
                    .uri(parkingDataUrl)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<RecordData>() {
                    })
                    .block();
        } catch (Exception e) {
            throw new EmptyThirdPartyResultException();
        }
    }

    public RecordData getAvailableParking(String parkingAvailableUrl) {

        try {
            return webClient
                    .get()
                    .uri(parkingAvailableUrl)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<RecordData>() {
                    })
                    .block();
        } catch (Exception e) {
            throw new EmptyThirdPartyResultException();
        }
    }
}

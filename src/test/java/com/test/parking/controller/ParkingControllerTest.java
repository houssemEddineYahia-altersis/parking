package com.test.parking.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.parking.dto.NearbyParkingRequest;
import com.test.parking.dto.NearbyParkingResponse;
import com.test.parking.service.ParkingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ParkingControllerTest {
    @Autowired
    private ParkingController parkingController;

    @MockBean
    private ParkingService parkingService;

    /**
     * Method under test: {@link ParkingController#nearbyParking(NearbyParkingRequest)}
     */
    @Test
    void testNearbyParking_ValidRequest_ReturnsOKResponseWithNearbyParkingResponse() throws Exception {
        when(parkingService.nearbyParking((NearbyParkingRequest) any())).thenReturn(new NearbyParkingResponse());

        NearbyParkingRequest nearbyParkingRequest = new NearbyParkingRequest();
        nearbyParkingRequest.setCity("Paris");
        nearbyParkingRequest.setLatitude(10.0d);
        nearbyParkingRequest.setLongitude(10.0d);

        String content = (new ObjectMapper()).writeValueAsString(nearbyParkingRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/parking/nearby")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        MockMvcBuilders.standaloneSetup(parkingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"parking\":null}"));
    }

    /**
     * Method under test: {@link ParkingController#nearbyParking(NearbyParkingRequest)}
     */
    @Test
    void testNearbyParking_InvalidRequest_ReturnsBadRequestResponse() throws Exception {
        when(parkingService.nearbyParking((NearbyParkingRequest) any())).thenReturn(new NearbyParkingResponse());

        NearbyParkingRequest nearbyParkingRequest = new NearbyParkingRequest();
        nearbyParkingRequest.setLatitude(10.0d);
        nearbyParkingRequest.setLongitude(10.0d);

        String content = (new ObjectMapper()).writeValueAsString(nearbyParkingRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/parking/nearby")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        MockMvcBuilders.standaloneSetup(parkingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}


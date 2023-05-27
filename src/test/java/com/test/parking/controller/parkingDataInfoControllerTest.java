package com.test.parking.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.test.parking.dto.RecordData;
import com.test.parking.service.ParkingDataInfoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class parkingDataInfoControllerTest {
    @Autowired
    private parkingDataInfoController parkingDataInfoController;

    @MockBean
    private ParkingDataInfoService parkingDataInfoService;


    /**
     * Method under test: {@link parkingDataInfoController#getParkingList(String)}
     */
    @Test
    void testGetParkingList() throws Exception {
        when(parkingDataInfoService.getParkingList((String) any())).thenReturn(new RecordData());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/parkingInfo/{city}", "Paris");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(parkingDataInfoController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"records\":null}"));
    }

    /**
     * Method under test: {@link parkingDataInfoController#getAvailableParking(String)}
     */
    @Test
    void testGetAvailableParking() throws Exception {
        when(parkingDataInfoService.getAvailableParkingList((String) any())).thenReturn(new RecordData());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/parkingInfo/available/{city}",
                "Paris");
        MockMvcBuilders.standaloneSetup(parkingDataInfoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"records\":null}"));
    }
}


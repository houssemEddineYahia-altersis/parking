package com.test.parking.controller;

import static org.mockito.Mockito.when;
import com.test.parking.service.ParkingDataSourceService;
import java.util.ArrayList;

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
class parkingDataSourceControllerTest {
    @Autowired
    private parkingDataSourceController parkingDataSourceController;

    @MockBean
    private ParkingDataSourceService parkingDataSourceService;

    /**
     * Method under test: {@link parkingDataSourceController#getAllParkingDataSources()}
     */
    @Test
    void testGetAllParkingDataSources() throws Exception {
        when(parkingDataSourceService.getAllParkingDataSources()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/parkingDataSource/all");
        MockMvcBuilders.standaloneSetup(parkingDataSourceController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}


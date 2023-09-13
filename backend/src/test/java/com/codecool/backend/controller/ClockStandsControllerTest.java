package com.codecool.backend.controller;

import com.codecool.backend.dao.model.ClockStands;
import com.codecool.backend.dao.model.DefaultPrices;
import com.codecool.backend.service.ClockStandsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ClockStandsController.class)
@AutoConfigureMockMvc(addFilters = false)
class ClockStandsControllerTest {
    @MockBean
    ClockStandsService clockStandsService;
    @Autowired
    MockMvc mockMvc;

    ClockStands testClockStands;

    @BeforeEach
    void setUp() {
        testClockStands = new ClockStands(
                LocalDateTime.of(2000, Month.JANUARY,
                        1,
                        0,
                        0),
                0,
                0,
                0,
                0,
                0
        );
    }


    @Test
    void getAllClockStandsObjects() throws Exception {
        List<ClockStands> expected = List.of(testClockStands);
        Mockito.when(clockStandsService.getAllClockStands()).thenReturn(expected);

        mockMvc.perform(MockMvcRequestBuilders.get("/clockstands/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[{\"dateTime\":\"2000-01-01T00:00:00\",\"warmWaterStand\":0.0,\"coldWaterStand\":0.0,\"electricityStand\":0,\"warmingBill\":0,\"gasBill\":" + DefaultPrices.GAS.getPrice() + ",\"baseRent\":" + DefaultPrices.BASE_RENT.getPrice() + ",\"kkt\":" + DefaultPrices.KKT.getPrice() + ",\"internetCost\":" + DefaultPrices.INTERNET.getPrice() + "}]"));
    }

    @Test
    void getClockStandsByYearAndMonth() throws Exception {
        List<ClockStands> expected = List.of(testClockStands);
        Mockito.when(clockStandsService.getClockStandsByMonthAndYear(2000, Month.JANUARY)).thenReturn(expected);
        mockMvc.perform(MockMvcRequestBuilders.get("/clockstands/date").param("year", "2000").param("month", "JANUARY"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[{\"dateTime\":\"2000-01-01T00:00:00\",\"warmWaterStand\":0.0,\"coldWaterStand\":0.0,\"electricityStand\":0,\"warmingBill\":0,\"gasBill\":" + DefaultPrices.GAS.getPrice() + ",\"baseRent\":" + DefaultPrices.BASE_RENT.getPrice() + ",\"kkt\":" + DefaultPrices.KKT.getPrice() + ",\"internetCost\":" + DefaultPrices.INTERNET.getPrice() + "}]"));
    }
}
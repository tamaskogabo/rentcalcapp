package com.codecool.backend.controller;

import com.codecool.backend.dao.model.ClockStands;
import com.codecool.backend.service.ClockStandsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@WebMvcTest(ClockStandsController.class)
@AutoConfigureMockMvc(addFilters = false)
class ClockStandsControllerTest {
    @MockBean
    ClockStandsService clockStandsService;
    @Autowired
    MockMvc mockMvc;
    ClockStands validTestClockStands;
    static ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    static void initMapperDateTimeModule() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @BeforeEach
    void setUp() {
        validTestClockStands = new ClockStands(
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
    void getAllClockStandsObjects_validRequest() throws Exception {
        List<ClockStands> expected = List.of(validTestClockStands);
        Mockito.when(clockStandsService.getAllClockStands()).thenReturn(expected);
        String expectedJson = objectMapper.writeValueAsString(expected);

        mockMvc.perform(MockMvcRequestBuilders.get("/clockstands/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedJson));
    }

    @Test
    void getClockStandsByYearAndMonth_validRequest() throws Exception {
        List<ClockStands> expected = List.of(validTestClockStands);
        Mockito.when(clockStandsService.getClockStandsByMonthAndYear(2000, Month.JANUARY)).thenReturn(expected);
        String expectedJson = objectMapper.writeValueAsString(expected);
        mockMvc.perform(MockMvcRequestBuilders.get("/clockstands/date")
                        .param("year", "2000")
                        .param("month", "JANUARY"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedJson));
    }

    @Test
    void postClockStand_validRequest() throws Exception {
        String jsonToSend = objectMapper.writeValueAsString(validTestClockStands);
        ResponseEntity<String> expected = new ResponseEntity<>("ClockStands saved.", HttpStatus.CREATED);
        Mockito.when(clockStandsService.postClockStands(validTestClockStands)).thenReturn(expected);
        mockMvc.perform(MockMvcRequestBuilders.post("/clockstands/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToSend))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
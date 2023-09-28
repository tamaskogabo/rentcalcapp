package com.codecool.backend.controller;

import com.codecool.backend.controller.dtos.ClockStandsRequest;
import com.codecool.backend.dao.ClockStandsDao;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest()
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
class ClockStandsControllerTest {
    @MockBean
    ClockStandsService clockStandsService;
    @MockBean
    ClockStandsDao clockStandsDao;
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
        validTestClockStands = ClockStands.of(
                LocalDateTime.of(2000, Month.JANUARY, 1, 0, 0),
                51,
                51,
                51,
                51
        );
    }


    @Test
    void getAllClockStandsObjects_validRequest() throws Exception {
        List<ClockStands> expected = List.of(validTestClockStands);
        Mockito.when(clockStandsService.getAllClockStands()).thenReturn(expected);
        String expectedJson = objectMapper.writeValueAsString(expected);

        mockMvc.perform(MockMvcRequestBuilders.get("/clockstands/all"))
                .andDo(MockMvcResultHandlers.print())
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
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedJson));
    }

    @Test
    void getClockStandsByYearAndMonth_validRequest_noEntryInDB() throws Exception {
        List<ClockStands> expected = new ArrayList<>();
        Mockito.when(clockStandsService.getClockStandsByMonthAndYear(2000, Month.JANUARY)).thenReturn(expected);
        String expectedJson = objectMapper.writeValueAsString(expected);
        mockMvc.perform(MockMvcRequestBuilders.get("/clockstands/date")
                        .param("year", "2000")
                        .param("month", "JANUARY"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedJson));
    }

    @Test
    void getClockStandsByYearAndMonth_noMonthInRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/clockstands/date")
                        .param("year", "2000"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void getClockStandsByYearAndMonth_noYearInRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/clockstands/date")
                        .param("month", "JANUARY"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void getClockStandsByYearAndMonth_noYearAndMonthInRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/clockstands/date"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void postClockStand_validRequest() throws Exception {
        ClockStandsRequest request = new ClockStandsRequest(51, 51, 51, 51);
        String jsonToSend = objectMapper.writeValueAsString(request);
        System.out.println(jsonToSend);
        ResponseEntity<String> expected = new ResponseEntity<>("ClockStands saved.", HttpStatus.CREATED);
        Mockito.when(clockStandsService.postClockStands(Mockito.any())).thenReturn(expected);
        mockMvc.perform(MockMvcRequestBuilders.post("/clockstands/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonToSend))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("ClockStands saved."));
    }

    @Test
    void postClockStand_noRequestBody() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/clockstands/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    void postClockStand_invalidRequestBody() throws Exception {
        String requestBody = "{\"invalidKey\" : \"false\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/clockstands/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(requestBody))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
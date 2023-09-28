package com.codecool.backend.service;

import com.codecool.backend.controller.dtos.ClockStandsRequest;
import com.codecool.backend.dao.ClockStandsDao;
import com.codecool.backend.dao.model.ClockStands;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClockStandsServiceTest {

    ClockStandsService clockStandsService;
    ClockStands testClockStands;
    ClockStandsRequest testClockStandsRequest;

    @Mock
    ClockStandsDao clockStandsDao;

    @BeforeEach
    void setUp() {
        clockStandsService = new ClockStandsService(clockStandsDao);
        testClockStands = ClockStands.of(LocalDateTime.of(2000, Month.JANUARY, 1, 0, 0),
                51,
                51,
                51,
                51);

        testClockStandsRequest = new ClockStandsRequest(
                0,
                0,
                0,
                0);
    }

    @Test
    void getAllClockStands_withOneEntry() throws SQLException {
        List<ClockStands> expected = List.of(testClockStands);
        Mockito.when(clockStandsDao.getAllClockStands()).thenReturn(List.of(testClockStands));
        List<ClockStands> actual = clockStandsService.getAllClockStands();
        assertEquals(expected, actual);
        Mockito.verify(clockStandsDao, Mockito.times(1)).getAllClockStands();
    }

    @Test
    void getAllClockStands_noEntry() throws SQLException {
        List<ClockStands> expected = new ArrayList<>();
        Mockito.when(clockStandsDao.getAllClockStands()).thenReturn(expected);
        List<ClockStands> actual = clockStandsService.getAllClockStands();
        assertEquals(expected, actual);
        Mockito.verify(clockStandsDao, Mockito.times(1)).getAllClockStands();
    }

    @Test
    void getClockStandsByMonthAndYear_withOneEntry() throws SQLException {
        List<ClockStands> expected = List.of(testClockStands);
        Mockito.when(clockStandsDao.getClockStandByMonthAndYear(2000, Month.JANUARY)).thenReturn(expected);
        List<ClockStands> actual = clockStandsService.getClockStandsByMonthAndYear(2000, Month.JANUARY);
        assertEquals(expected, actual);
        Mockito.verify(clockStandsDao, Mockito.times(1)).getClockStandByMonthAndYear(2000, Month.JANUARY);
    }

    @Test
    void postClockStands_shouldWork_happyCase() throws SQLException {
        ResponseEntity<String> expected = new ResponseEntity<>("ClockStands saved.", HttpStatus.CREATED);
        Mockito.when(clockStandsDao.postClockStand(Mockito.any(ClockStands.class))).thenReturn(true);
        ResponseEntity<String> actual = clockStandsService.postClockStands(testClockStandsRequest);
        assertEquals(actual, expected);
        Mockito.verify(clockStandsDao, Mockito.times(1)).postClockStand(Mockito.any(com.codecool.backend.dao.model.ClockStands.class));
    }

    @Test
    void postClockStands_shouldWork_sadCase() throws SQLException {
        ResponseEntity<String> expected = new ResponseEntity<>("Save not successful.", HttpStatus.BAD_REQUEST);
        Mockito.when(clockStandsDao.postClockStand(Mockito.any(ClockStands.class))).thenReturn(false);
        ResponseEntity<String> actual = clockStandsService.postClockStands(testClockStandsRequest);
        assertEquals(actual, expected);
        Mockito.verify(clockStandsDao, Mockito.times(1)).postClockStand(Mockito.any(com.codecool.backend.dao.model.ClockStands.class));
    }
}
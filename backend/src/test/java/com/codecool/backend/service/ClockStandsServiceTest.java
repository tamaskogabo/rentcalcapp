package com.codecool.backend.service;

import com.codecool.backend.controller.dto.ClockStandsDto;
import com.codecool.backend.dao.ClockStandsDao;
import com.codecool.backend.dao.ClockStandsDaoJDBC;
import com.codecool.backend.dao.model.ClockStands;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClockStandsServiceTest {

    ClockStandsService clockStandsService;
    ClockStands testClockStands;
    ClockStandsDto testClockStandsDto;

    @Mock
    ClockStandsDao clockStandsDao;

    @BeforeEach
    void setUp() {
        clockStandsService = new ClockStandsService(clockStandsDao);
        testClockStands = new ClockStands(
                LocalDateTime.of(2000, Month.JANUARY,
                        1,
                        0,
                        0),
                0,
                0,
                0,
                0,
                0);
        testClockStandsDto = new ClockStandsDto(1,
                LocalDateTime.of(2000,
                        Month.JANUARY,
                        1,
                        0,
                        0),
                0,
                0,
                0,
                0,
                0);
    }

    @Test
    void getAllClockStands() throws SQLException {
        List<ClockStands> expected = List.of(testClockStands);
        Mockito.when(clockStandsDao.getAllClockStands()).thenReturn(List.of(testClockStandsDto));
        List<ClockStands> actual = clockStandsService.getAllClockStands();
        assertEquals(expected.get(0).getColdWaterStand(), actual.get(0).getColdWaterStand());
        assertEquals(expected.get(0).getElectricityStand(), actual.get(0).getElectricityStand());
        assertEquals(expected.get(0).getWarmWaterStand(), actual.get(0).getWarmWaterStand());
        assertEquals(expected.get(0).getGasBill(), actual.get(0).getGasBill());
        assertEquals(expected.get(0).getWarmingBill(), actual.get(0).getWarmingBill());
        assertEquals(expected.get(0).getBaseRent(), actual.get(0).getBaseRent());
        assertEquals(expected.get(0).getKkt(), actual.get(0).getKkt());
        assertEquals(expected.get(0).getInternetCost(), actual.get(0).getInternetCost());
        assertEquals(expected.get(0).getDateTime(), actual.get(0).getDateTime());
        assertEquals(expected.get(0).getColdWaterStand(), actual.get(0).getColdWaterStand());
    }

    @Test
    void getClockStandsByMonthAndYear() throws SQLException {
        List<ClockStands> expected = List.of(testClockStands);
        Mockito.when(clockStandsDao.getClockStandByMonthAndYear(2000, Month.JANUARY)).thenReturn(List.of(testClockStandsDto));
        List<ClockStands> actual = clockStandsService.getClockStandsByMonthAndYear(2000, Month.JANUARY);
        assertEquals(expected.get(0).getColdWaterStand(), actual.get(0).getColdWaterStand());
        assertEquals(expected.get(0).getElectricityStand(), actual.get(0).getElectricityStand());
        assertEquals(expected.get(0).getWarmWaterStand(), actual.get(0).getWarmWaterStand());
        assertEquals(expected.get(0).getGasBill(), actual.get(0).getGasBill());
        assertEquals(expected.get(0).getWarmingBill(), actual.get(0).getWarmingBill());
        assertEquals(expected.get(0).getBaseRent(), actual.get(0).getBaseRent());
        assertEquals(expected.get(0).getKkt(), actual.get(0).getKkt());
        assertEquals(expected.get(0).getInternetCost(), actual.get(0).getInternetCost());
        assertEquals(expected.get(0).getDateTime(), actual.get(0).getDateTime());
        assertEquals(expected.get(0).getColdWaterStand(), actual.get(0).getColdWaterStand());
    }

    @Test
    void postClockStands() throws SQLException {
        boolean expected = true;
        Mockito.when(clockStandsDao.postClockStand(testClockStands)).thenReturn(true);
        boolean actual = clockStandsService.postClockStands(testClockStands);
        assertEquals(actual, expected);
    }
}
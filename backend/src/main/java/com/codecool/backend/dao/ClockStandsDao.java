package com.codecool.backend.dao;

import com.codecool.backend.controller.dto.ClockStandsDto;

import java.sql.SQLException;
import java.time.Month;
import java.util.List;

public interface ClockStandsDao {
    public List<ClockStandsDto> getAllClockStands() throws SQLException;
    public List<ClockStandsDto> getClockStandByMonthAndYear(int year, Month month) throws SQLException;
    public ClockStandsDto getClockStandById(int id);
}

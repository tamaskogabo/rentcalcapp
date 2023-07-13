package com.codecool.backend.dao;

import com.codecool.backend.controller.dto.ClockStandsDto;
import com.codecool.backend.dao.model.ClockStands;

import java.sql.SQLException;
import java.time.Month;
import java.util.List;

public interface ClockStandsDao {
    public List<ClockStandsDto> getAllClockStands() throws SQLException;
    public ClockStandsDto getClockStandByDate(int year, Month month);
    public ClockStandsDto getClockStandById(int id);
}

package com.codecool.backend.dao;

import com.codecool.backend.controller.dto.ClockStandsDto;
import com.codecool.backend.dao.model.ClockStands;

import java.time.Month;
import java.util.List;

public class ClockStandsDaoJDBC implements ClockStandsDao {
    @Override
    public List<ClockStandsDto> getAllClockStands() {
        //TODO
        return null;
    }

    @Override
    public ClockStandsDto getClockStandByDate(int year, Month month) {
        //TODO
        return null;
    }

    @Override
    public ClockStandsDto getClockStandById(int id) {
        //TODO
        return null;
    }
}

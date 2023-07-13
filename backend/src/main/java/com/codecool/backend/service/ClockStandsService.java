package com.codecool.backend.service;

import com.codecool.backend.dao.ClockStandsDao;
import org.springframework.beans.factory.annotation.Autowired;

public class ClockStandsService {
    private final ClockStandsDao clockStandsDao;

    @Autowired
    public ClockStandsService(ClockStandsDao clockStandsDao) {
        this.clockStandsDao = clockStandsDao;
    }

    //TODO this class will return the model objects made from the Dto-s.
}

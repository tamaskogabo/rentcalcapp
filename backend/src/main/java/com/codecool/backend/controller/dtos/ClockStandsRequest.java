package com.codecool.backend.controller.dtos;

import com.codecool.backend.dao.model.ClockStands;

import java.time.LocalDateTime;

public class ClockStandsRequest {
    private final double warmWaterStand;
    private final double coldWaterStand;
    private final int electricityStand;
    private final int warmingBill;

    public ClockStandsRequest(double warmWaterStand, double coldWaterStand, int electricityStand, int warmingBill) {
        this.warmWaterStand = warmWaterStand;
        this.coldWaterStand = coldWaterStand;
        this.electricityStand = electricityStand;
        this.warmingBill = warmingBill;
    }

    public ClockStands createClockStands() {
        return ClockStands.of(LocalDateTime.now(), warmWaterStand, coldWaterStand, electricityStand, warmingBill);
    }
}

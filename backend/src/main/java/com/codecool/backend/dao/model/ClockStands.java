package com.codecool.backend.dao.model;

import java.time.LocalDateTime;

public class ClockStands {
    private final LocalDateTime dateTime;
    private final double warmWaterStand;
    private final double coldWaterStand;
    private final int electricityStand;
    private final int warmingBill;
    private final int gasBill;
    private final int baseRent = 170000;
    private final int kkt = 25350;
    private final int internetCost = 6500;

    public ClockStands(LocalDateTime dateTime, double warmWaterStand, double coldWaterStand, int electricityStand, int warmingBill, int gasBill) {
        this.dateTime = dateTime;
        this.warmWaterStand = warmWaterStand;
        this.coldWaterStand = coldWaterStand;
        this.electricityStand = electricityStand;
        this.warmingBill = warmingBill;
        this.gasBill = gasBill;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public double getWarmWaterStand() {
        return warmWaterStand;
    }

    public double getColdWaterStand() {
        return coldWaterStand;
    }

    public int getElectricityStand() {
        return electricityStand;
    }

    public int getWarmingBill() {
        return warmingBill;
    }

    public int getGasBill() {
        return gasBill;
    }

    public int getBaseRent() {
        return baseRent;
    }

    public int getKkt() {
        return kkt;
    }

    public int getInternetCost() {
        return internetCost;
    }
}

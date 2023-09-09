package com.codecool.backend.dao.model;

import java.time.LocalDateTime;

import static com.codecool.backend.dao.model.DefaultPrices.*;

public class ClockStands {
    private final LocalDateTime dateTime;
    private final double warmWaterStand;
    private final double coldWaterStand;
    private final int electricityStand;
    private final int warmingBill;
    private final int gasBill = GAS.price;
    private final int baseRent = BASE_RENT.price;
    private final int kkt = KKT.price;
    private final int internetCost = INTERNET.price;

    public ClockStands(LocalDateTime dateTime, double warmWaterStand, double coldWaterStand, int electricityStand, int warmingBill, int gasBill) {
        this.dateTime = dateTime;
        this.warmWaterStand = warmWaterStand;
        this.coldWaterStand = coldWaterStand;
        this.electricityStand = electricityStand;
        this.warmingBill = warmingBill;
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

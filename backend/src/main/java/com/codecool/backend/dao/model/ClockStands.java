package com.codecool.backend.dao.model;

public class ClockStands {
    private final double warmWaterStand;
    private final double coldWaterStand;
    private final int electricityStand;
    private final int warmingBill;
    private final int gasBill;
    private final int baseRent = 170000;
    private final int kkt = 25350;
    private final int internetCost = 6500;

    public ClockStands(double warmWaterStand, double coldWaterStand, int electricityStand, int warmingBill, int gasBill) {
        this.warmWaterStand = warmWaterStand;
        this.coldWaterStand = coldWaterStand;
        this.electricityStand = electricityStand;
        this.warmingBill = warmingBill;
        this.gasBill = gasBill;
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

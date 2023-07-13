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
}

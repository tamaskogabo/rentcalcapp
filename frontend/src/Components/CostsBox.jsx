import { Typography } from '@mui/material';
import React from 'react';
import AccountBalanceIcon from '@mui/icons-material/AccountBalance';
import CallSplitIcon from '@mui/icons-material/CallSplit';

export default function CostsBox({ thisMonthData, lastMonthData }) {
    const WARM_WATER =
        Math.floor(thisMonthData[0].warmWaterStand) -
        Math.floor(lastMonthData[0].warmWaterStand);

    const COLD_WATER =
        Math.floor(thisMonthData[0].coldWaterStand) -
        Math.floor(lastMonthData[0].coldWaterStand);

    function calculateMonthlyCosts() {
        const currentMonth = new Date().getMonth() + 1;
        if (currentMonth % 2 !== 0 && currentMonth % 3 === 0) {
            return calculateOddAndQuarterlyCosts(WARM_WATER, COLD_WATER);
        } else if (currentMonth % 3 === 0) {
            return calculateQuarterlyCost();
        } else if (currentMonth % 2 !== 0) {
            return calculateOddMonthCost(WARM_WATER, COLD_WATER);
        }
        return calculateSimpleMonth();
    }

    function calculateOddAndQuarterlyCosts(warmWater, coldWater) {
        return calculateOddMonthCost(warmWater, coldWater) + thisMonthData[0].gasBill;
    }

    function calculateQuarterlyCost() {
        return calculateSimpleMonth() + thisMonthData[0].gasBill;
    }

    function calculateOddMonthCost(warmWater, coldWater) {
        return calculateSimpleMonth() + (warmWater + coldWater) * 600 + 338;
    }

    function calculateSimpleMonth() {
        return (
            thisMonthData[0].baseRent +
            thisMonthData[0].kkt +
            thisMonthData[0].warmingBill +
            (thisMonthData[0].electricityStand -
                lastMonthData[0].electricityStand) *
                38.7
        );
    }

    let sumToWire = 0;
    let sharedCostsWithInternet = 6500;

    if (thisMonthData) {
        sumToWire += calculateMonthlyCosts();
        sharedCostsWithInternet += calculateMonthlyCosts();
    }

    return (
        <>
            <Typography variant='h5' p={2}>
                <AccountBalanceIcon sx={{ marginRight: '10px' }} />
                Sum to wire to landlord: {sumToWire}
            </Typography>
            <Typography variant='h5' p={2}>
                <CallSplitIcon sx={{ marginRight: '10px' }} />
                Sum to ask from flatmate: {sharedCostsWithInternet / 2}
            </Typography>
        </>
    );
}

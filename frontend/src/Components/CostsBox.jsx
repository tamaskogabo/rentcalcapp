import { Typography } from '@mui/material';
import React from 'react';
import AccountBalanceIcon from '@mui/icons-material/AccountBalance';
import CallSplitIcon from '@mui/icons-material/CallSplit';

export default function CostsBox({ thisMonthData, lastMonthData }) {
    function calculateMonthlyCosts() {
        const currentMonth = new Date().getMonth() + 1;
        if (currentMonth % 2 !== 0 && currentMonth % 3 === 0) {
            const warmWater =
                Math.floor(thisMonthData[0].warmWaterStand) -
                Math.floor(lastMonthData[0].warmWaterStand);
            const coldWater =
                Math.floor(thisMonthData[0].coldWaterStand) -
                Math.floor(lastMonthData[0].coldWaterStand);

            return (
                thisMonthData[0].baseRent +
                thisMonthData[0].kkt +
                thisMonthData[0].warmingBill +
                thisMonthData[0].gasBill +
                (thisMonthData[0].electricityStand -
                    lastMonthData[0].electricityStand) *
                    38.7 +
                (warmWater + coldWater) * 600 +
                338
            );
        } else if (currentMonth % 3 === 0) {
            return (
                thisMonthData[0].baseRent +
                thisMonthData[0].kkt +
                thisMonthData[0].warmingBill +
                thisMonthData[0].gasBill +
                (thisMonthData[0].electricityStand -
                    lastMonthData[0].electricityStand) *
                    38.7
            );
        } else if (currentMonth % 2 !== 0) {
            const warmWater =
                Math.floor(thisMonthData[0].warmWaterStand) -
                Math.floor(lastMonthData[0].warmWaterStand);
            const coldWater =
                Math.floor(thisMonthData[0].coldWaterStand) -
                Math.floor(lastMonthData[0].coldWaterStand);

            return (
                thisMonthData[0].baseRent +
                thisMonthData[0].kkt +
                thisMonthData[0].warmingBill +
                (thisMonthData[0].electricityStand -
                    lastMonthData[0].electricityStand) *
                    38.7 +
                (warmWater + coldWater) * 600 +
                338
            );
        }
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
    let sharedCosts = 6500;

    if (thisMonthData) {
        sumToWire += calculateMonthlyCosts();
        sharedCosts += calculateMonthlyCosts();
    }

    return (
        <>
            <Typography variant='h5' p={2}>
                <AccountBalanceIcon sx={{ marginRight: '10px' }} />
                Sum to wire to landlord: {sumToWire}
            </Typography>
            <Typography variant='h5' p={2}>
                <CallSplitIcon sx={{ marginRight: '10px' }} />
                Sum to ask from flatmate: {sharedCosts / 2}
            </Typography>
        </>
    );
}

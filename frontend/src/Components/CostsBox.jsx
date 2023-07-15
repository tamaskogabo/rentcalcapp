import { Typography } from '@mui/material';
import React from 'react';
import AccountBalanceIcon from '@mui/icons-material/AccountBalance';

export default function CostsBox({thisMonthData, lastMonthData}) {
    return (
        <>
            <Typography variant='h5' p={2}>
                Normal month
            </Typography>
            <Typography variant='h5' p={2}>
                <AccountBalanceIcon sx={{ marginRight: '10px' }} />
                Sum to wire to landlord:
            </Typography>
        </>
    );
}

import React from 'react';
import { Box, Paper, Stack, Typography } from '@mui/material';
import WaterDropIcon from '@mui/icons-material/WaterDrop';
import ElectricBoltIcon from '@mui/icons-material/ElectricBolt';
import WbSunnyIcon from '@mui/icons-material/WbSunny';

export default function CalculatePage({ lastMonthData }) {

    //TODO loading state for the fetch of data
    
    return (
        <>
            <Stack direction='column' alignItems='center'>
                <Box maxWidth={0.9} mt='20px'>
                    <Paper elevation={3}>
                        <Typography variant='h5' p={2} mt='20px'>
                            <WaterDropIcon sx={{ marginRight: '10px' }} />
                            Last month's warm water: {lastMonthData && lastMonthData[0].warmWaterStand}
                        </Typography>
                        <Typography variant='h5' p={2}>
                            <WaterDropIcon sx={{ marginRight: '10px' }} />
                            Last month's cold water: {lastMonthData && lastMonthData[0].coldWaterStand}
                        </Typography>
                        <Typography variant='h5' p={2}>
                            <ElectricBoltIcon sx={{ marginRight: '10px' }} />
                            Last month's electricity: {lastMonthData && lastMonthData[0].electricityStand}
                        </Typography>
                        <Typography variant='h5' p={2} mb='20px'>
                            <WbSunnyIcon sx={{ marginRight: '10px' }} />
                            Last month's warming: {lastMonthData && lastMonthData[0].warmingBill} Ft
                        </Typography>
                    </Paper>
                </Box>
            </Stack>
        </>
    );
}

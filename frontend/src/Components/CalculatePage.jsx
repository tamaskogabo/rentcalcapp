import React from 'react';
import { Box, Paper, Stack, Typography } from '@mui/material';
import WaterDropIcon from '@mui/icons-material/WaterDrop';
import ElectricBoltIcon from '@mui/icons-material/ElectricBolt';
import WbSunnyIcon from '@mui/icons-material/WbSunny';

export default function CalculatePage() {
    return (
        <>
            <Stack direction='column' alignItems='center'>
                <Box width={0.9} mt='20px'>
                    <Paper elevation={3}>
                        <Typography variant='h5' p={2} mt='20px'>
                            <WaterDropIcon sx={{marginRight : '10px'}}/>
                            Last month's warm water:
                        </Typography>
                        <Typography variant='h5' p={2}>
                            <WaterDropIcon sx={{marginRight : '10px'}}/>
                            Last month's cold water:
                        </Typography>
                        <Typography variant='h5' p={2}>
                            <ElectricBoltIcon sx={{marginRight : '10px'}}/>
                            Last month's electricity:
                        </Typography>
                        <Typography variant='h5' p={2} mb='20px'>
                            <WbSunnyIcon sx={{marginRight : '10px'}}/>
                            Last month's warming:
                        </Typography>
                    </Paper>
                </Box>
            </Stack>
        </>
    );
}

import React from 'react';
import { Box, Paper, Stack, Typography } from '@mui/material';
import WaterDropIcon from '@mui/icons-material/WaterDrop';
import ElectricBoltIcon from '@mui/icons-material/ElectricBolt';
import WbSunnyIcon from '@mui/icons-material/WbSunny';
import CostsBox from './CostsBox';

export default function CalculatePage({ lastMonthData, thisMonthData }) {

    return (
        <>
            <Stack direction='row' justifyContent='center' gap={10}>
                <Box maxWidth={0.4} mt='20px'>
                    <Paper elevation={3}>
                        <Typography variant='h4' p={2} mt='20px'>
                            Last month's standings
                        </Typography>

                        <Typography variant='h5' p={2}>
                            <WaterDropIcon sx={{ marginRight: '10px' }} />
                            Last month's warm water:{' '}
                            {lastMonthData && lastMonthData[0].warmWaterStand}
                        </Typography>
                        <Typography variant='h5' p={2}>
                            <WaterDropIcon sx={{ marginRight: '10px' }} />
                            Last month's cold water:{' '}
                            {lastMonthData && lastMonthData[0].coldWaterStand}
                        </Typography>
                        <Typography variant='h5' p={2}>
                            <ElectricBoltIcon sx={{ marginRight: '10px' }} />
                            Last month's electricity:{' '}
                            {lastMonthData && lastMonthData[0].electricityStand}
                        </Typography>
                        <Typography variant='h5' p={2} mb='20px'>
                            <WbSunnyIcon sx={{ marginRight: '10px' }} />
                            Last month's warming:{' '}
                            {lastMonthData && lastMonthData[0].warmingBill} Ft
                        </Typography>

                        <Typography variant='h4' p={2} mt='20px'>
                            This month's standings
                        </Typography>

                        {!thisMonthData ? (
                            <Typography variant='h5' p={2} mb='20px'>
                                No data found for this month
                            </Typography>
                        ) : (
                            <>
                                <Typography variant='h5' p={2}>
                                    <WaterDropIcon
                                        sx={{ marginRight: '10px' }}
                                    />
                                    This month's warm water:{' '}
                                    {thisMonthData &&
                                        thisMonthData[0].warmWaterStand}
                                </Typography>
                                <Typography variant='h5' p={2}>
                                    <WaterDropIcon
                                        sx={{ marginRight: '10px' }}
                                    />
                                    This month's cold water:{' '}
                                    {thisMonthData &&
                                        thisMonthData[0].coldWaterStand}
                                </Typography>
                                <Typography variant='h5' p={2}>
                                    <ElectricBoltIcon
                                        sx={{ marginRight: '10px' }}
                                    />
                                    This month's electricity:{' '}
                                    {thisMonthData &&
                                        thisMonthData[0].electricityStand}
                                </Typography>
                                <Typography variant='h5' p={2} mb='20px'>
                                    <WbSunnyIcon sx={{ marginRight: '10px' }} />
                                    This month's warming:{' '}
                                    {thisMonthData &&
                                        thisMonthData[0].warmingBill}{' '}
                                    Ft
                                </Typography>
                            </>
                        )}
                    </Paper>
                </Box>
                {thisMonthData && lastMonthData && (
                    <Box maxWidth={0.4} mt='20px'>
                        <Paper elevation={3}>
                            <Typography variant='h4' mt='20px' p={2}>
                                Costs
                            </Typography>
                            <CostsBox
                                thisMonthData={thisMonthData}
                                lastMonthData={lastMonthData}
                            />
                        </Paper>
                    </Box>
                )}
            </Stack>
        </>
    );
}

import React from 'react';
import { Box, Paper, Stack, Typography } from '@mui/material';
import CostsBox from './CostsBox';
import LastMonthBox from './LastMonthBox';

export default function CalculatePage({ lastMonthData, thisMonthData }) {
    return (
        <>
            <Stack direction='row' justifyContent='center' gap={10}>
                <LastMonthBox lastMonthData={lastMonthData} thisMonthData={thisMonthData}/>
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

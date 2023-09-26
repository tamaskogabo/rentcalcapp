import {
    Box,
    Paper,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
} from '@mui/material';
import React, { useState, useEffect } from 'react';
import Loading from './Loading';

export default function ClockStandsHistoryPage() {
    const [clockstands, setClockstands] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        async function fetchClockStands() {
            const response = await fetch(`/clockstands/all`);
            const result = await response.json();
            if (!ignore) {
                setClockstands(result);
                setLoading(false);
            }
        }
        let ignore = false;
        fetchClockStands();
        return () => {
            ignore = true;
        };
    }, []);

    if (loading) {
        return (
            <Box
                width={'100vw'}
                mt={'50px'}
                display={'flex'}
                justifyContent={'center'}
            >
                <Loading />
            </Box>
        );
    }

    return (
        <>
            <TableContainer component={Paper}>
                <Table sx={{ minWidth: 650 }} aria-label='simple table'>
                    <TableHead>
                        <TableRow>
                            <TableCell>Date</TableCell>
                            <TableCell align='right'>Warm water</TableCell>
                            <TableCell align='right'>Cold water</TableCell>
                            <TableCell align='right'>Electricity</TableCell>
                            <TableCell align='right'>Warming</TableCell>
                            <TableCell align='right'>Gas</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {clockstands.map((row) => (
                            <TableRow
                                key={row.dateTime}
                                sx={{
                                    '&:last-child td, &:last-child th': {
                                        border: 0,
                                    },
                                }}
                            >
                                <TableCell component='th' scope='row'>
                                    {row.dateTime}
                                </TableCell>
                                <TableCell align='right'>
                                    {row.warmWaterStand}
                                </TableCell>
                                <TableCell align='right'>
                                    {row.coldWaterStand}
                                </TableCell>
                                <TableCell align='right'>
                                    {row.electricityStand}
                                </TableCell>
                                <TableCell align='right'>
                                    {row.warmingBill}
                                </TableCell>
                                <TableCell align='right'>
                                    {row.gasBill}
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </>
    );
}

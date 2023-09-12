import { Box, Typography } from '@mui/material';
import './App.css';
import CalculatePage from './Components/CalculatePage';
import TopMenu from './Components/TopMenu';
import { useState, useEffect } from 'react';
import ClockStandsHistoryPage from './Components/ClockStandsHistoryPage';
import Loading from './Components/Loading';
import PostClockStandPage from './Components/PostClockStandPage';

const MONTHS = [
    'JANUARY',
    'FEBRUARY',
    'MARCH',
    'APRIL',
    'MAY',
    'JUNE',
    'JULY',
    'AUGUST',
    'SEPTEMBER',
    'OCTOBER',
    'NOVEMBER',
    'DECEMBER',
];

function App() {
    const [lastMonthData, setLastMonthData] = useState(null);
    const [thisMonthData, setThisMonthData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [page, setPage] = useState('CALC');

    useEffect(() => {
        async function fetchClockStands() {
            setLastMonthData(null);
            setThisMonthData(null);
            const currentYear = new Date().getFullYear();
            const lastMonth = MONTHS[new Date().getMonth() - 1];
            const thisMonth = MONTHS[new Date().getMonth()];
            const responseLastMonth = await fetch(
                `/clockstands/date?year=${currentYear}&month=${lastMonth}`,
            );
            const resultLastMonth = await responseLastMonth.json();
            const responseThisMonth = await fetch(
                `/clockstands/date?year=${currentYear}&month=${thisMonth}`,
            );
            const resultThisMonth = await responseThisMonth.json();
            if (!ignore) {
                setLastMonthData(resultLastMonth);
                setThisMonthData(resultThisMonth);
                console.log(thisMonthData);
                console.log(lastMonthData);
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

    if (
        page === 'CALC' &&
        (lastMonthData.length === null || thisMonthData.length === null)
    ) {
        return (
            <>
                <TopMenu setPage={setPage} />
                <Box>
                    <Typography variant='h3' textAlign='center'>
                        There has to be data from this and the previous month to
                        process calculations...
                    </Typography>
                </Box>
            </>
        );
    } else if (page === 'CALC') {
        return (
            <>
                <TopMenu setPage={setPage} />
                <CalculatePage
                    lastMonthData={lastMonthData}
                    thisMonthData={thisMonthData}
                />
            </>
        );
    } else if (page === 'POST') {
        return (
            <>
                <TopMenu setPage={setPage} />
                <PostClockStandPage />
            </>
        );
    } else if (page === 'PREV') {
        return (
            <>
                <TopMenu setPage={setPage} />
                <ClockStandsHistoryPage />
            </>
        );
    }
}

export default App;

import { Typography } from '@mui/material';
import './App.css';
import CalculatePage from './Components/CalculatePage';
import TopMenu from './Components/TopMenu';
import { useState, useEffect } from 'react';

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
    const [page, setPage] = useState('CALC');

    useEffect(() => {
        async function fetchLastMonth() {
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
            }
        }
        let ignore = false;
        fetchLastMonth();
        return () => {
            ignore = true;
        };
    }, []);

    if (page === 'CALC') {
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
                <TopMenu setPage={setPage}/>
                <Typography>Post Data</Typography>
            </>
        );
    } else if (page === 'PREV') {
        return (
            <>
                <TopMenu setPage={setPage}/>
                <Typography>Previous Data</Typography>
            </>
        );
    }
}

export default App;

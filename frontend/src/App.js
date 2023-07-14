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
    const [lastMonthData, setlastMonthData] = useState(null);

    useEffect(() => {
        async function fetchLastMonth() {
            setlastMonthData(null);
            const currentYear = new Date().getFullYear();
            
            //Has to be set one month earlier after everything is done!
            const currentMonth = MONTHS[new Date().getMonth()];
            console.log(currentYear);
            console.log(currentMonth);
            const response = await fetch(
                `http://localhost:8080/clockstands/date?year=${currentYear}&month=${currentMonth}`,
            );
            const result = await response.json();
            if (!ignore) {
                setlastMonthData(result);
            }
        }
        let ignore = false;
        fetchLastMonth();
        return () => {
            ignore = true;
        };
    }, []);

    return (
        <>
            <TopMenu />
            <CalculatePage lastMonthData={lastMonthData} />
        </>
    );
}

export default App;

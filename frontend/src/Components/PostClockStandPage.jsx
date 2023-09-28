import {
    Alert,
    Box,
    Button,
    Paper,
    Snackbar,
    TextField,
    Typography,
} from '@mui/material';
import React, { useState } from 'react';

export default function PostClockStandPage() {
    const [openAlert, setOpenAlert] = useState(false);
    const [openSuccess, setOpenSuccess] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');

    const handleClose = (_event, reason) => {
        if (reason === 'clickaway') {
            return;
        }

        setOpenAlert(false);
    };

    async function handlePostClockStands(e) {
        e.preventDefault();
        const form = e.target;
        const formData = new FormData(form);
        const formJson = Object.fromEntries(formData.entries());
        console.log(formJson);
        try {
            const request = await fetch('/clockstands/', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formJson),
            });
            const response = await request.json();
            if (response.status !== '201') {
                console.error('Something went wrong on server, maybe inpus values are invalid!');
                setErrorMessage('Something went wrong on server, maybe inpus values are invalid!');
                setOpenAlert(true);
            } else {
                setOpenSuccess(true);
                console.log(response);
            }
        } catch (error) {
            console.error('Already found an entry for this month on server!');
            setErrorMessage('Already found an entry for this month on server!');
            setOpenAlert(true);
        }
    }

    return (
        <>
            <Box width='100vw' height='100vh' mt='30px'>
                <Paper elevation={3}>
                    <Box
                        flexDirection='column'
                        alignItems='center'
                        gap={5}
                        component='form'
                        onSubmit={handlePostClockStands}
                        display='flex'
                        sx={{ alignItems: 'center' }}
                    >
                        <Typography variant='h3' style={{ marginTop: '20px' }}>
                            Please type in the clock stands
                        </Typography>
                        <TextField
                            id='warmWaterStand'
                            name='warmWaterStand'
                            label='Warm Water Stand'
                            variant='filled'
                            size='small'
                        />
                        <TextField
                            id='coldWaterStand'
                            name='coldWaterStand'
                            label='Cold Water Stand'
                            size='small'
                            variant='filled'
                        />
                        <TextField
                            id='electricityStand'
                            name='electricityStand'
                            label='Electricity Stand'
                            size='small'
                            variant='filled'
                        />
                        <TextField
                            id='warmingBill'
                            name='warmingBill'
                            label='Warming Bill'
                            size='small'
                            variant='filled'
                        />
                        <Button
                            type='submit'
                            variant='contained'
                            style={{ marginBottom: '20px' }}
                        >
                            Submit
                        </Button>
                    </Box>
                </Paper>
            </Box>
            <Snackbar
                open={openAlert}
                autoHideDuration={6000}
                onClose={handleClose}
            >
                <Alert severity='error'>
                    {errorMessage}
                </Alert>
            </Snackbar>
            <Snackbar
                open={openSuccess}
                autoHideDuration={6000}
                onClose={handleClose}
            >
                <Alert severity='success'>Save successful!</Alert>
            </Snackbar>
        </>
    );
}

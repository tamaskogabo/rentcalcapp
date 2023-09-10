import React from 'react';
import {
    AppBar,
    Box,
    Button,
    Container,
    Toolbar,
    Typography,
} from '@mui/material';
import CalculateIcon from '@mui/icons-material/Calculate';

export default function TopMenu({setPage}) {
    return (
        <AppBar position='sticky'>
            <Container maxWidth='xl'>
                <Toolbar disableGutters>
                    <Box
                        display='flex'
                        alignItems='center'
                        sx={{ width: { xs: 'auto', sm: 'auto', md: '0.4' } }}
                    >
                        <CalculateIcon fontSize='large' />
                        <Typography
                            variant='h4'
                            p={2}
                            sx={{
                                display: {
                                    xs: 'none',
                                    sm: 'none',
                                    md: 'block',
                                },
                            }}
                        >
                            Rent calculator app
                        </Typography>
                    </Box>
                    <Box
                        display='flex'
                        width={0.6}
                        sx={{
                            justifyContent: 'flex-end',
                            width: { xs: 'auto', sm: '0.7', md: '0.6' },
                        }}
                    >
                        <Button variant='contained' onClick={() => setPage('CALC')}>Calculate</Button>
                        <Button variant='contained' onClick={() => setPage('POST')}>Post</Button>
                        <Button variant='contained' onClick={() => setPage('PREV')}>See previous data</Button>
                        <Button variant='contained' href='/logout'>Logout</Button>
                    </Box>
                </Toolbar>
            </Container>
        </AppBar>
    );
}

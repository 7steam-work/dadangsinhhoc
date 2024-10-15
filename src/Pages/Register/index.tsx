import React from 'react';
import { Box, Typography, Container } from '@mui/material';
import { Link } from 'react-router-dom';
import RegisterForm from './components/RegisterForm';
import RegisterHeader from './components/RegisterHeader';

const Register: React.FC = () => {

  return (
    <Box
      sx={{
        minHeight: '100vh',
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#065f46',
        backgroundImage: 'linear-gradient(315deg, #065f46 0%, #6ee7b7 74%)',
      }}
    >
      <Container maxWidth="xs">
        <RegisterHeader />
        <RegisterForm />
      </Container>
    </Box>
  );
};

export default Register;

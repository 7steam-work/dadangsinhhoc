import React, { useState } from 'react';
import { Box, Button, TextField, Typography, Container, Paper, InputAdornment, IconButton } from '@mui/material';
import Visibility from '@mui/icons-material/Visibility';
import VisibilityOff from '@mui/icons-material/VisibilityOff';
import { useNavigate } from 'react-router-dom';
import LoginForm from './components/LoginForm';
import LoginHeader from './components/LoginHeader';
import { Link } from 'react-router-dom';
import {useEffect} from "react";
import userApi from "../../Api/userApi.tsx";

const Login: React.FC = () => {
  useEffect(() => {
    const fetchUser = async () => {
      const listUser = await userApi.getAll();
      console.log(listUser);
    };
    fetchUser();
  }, [])

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
        <LoginHeader />
        <LoginForm />
        <Box sx={{ textAlign: 'center', mt: 3 }}>
          <Typography variant="body2" sx={{ fontWeight: 'bold', color: '#ffffff', textDecoration: 'underline' }}>
            <Link to='/register'>
              Đăng ký tại đây
            </Link>
          </Typography>
        </Box>
      </Container>
    </Box>
  );
};

export default Login;

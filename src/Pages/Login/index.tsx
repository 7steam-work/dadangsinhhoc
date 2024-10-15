import React, { useEffect } from 'react';
import { Box, Typography, Container } from '@mui/material';
import { Link } from 'react-router-dom';
import LoginForm from './components/LoginForm';
import LoginHeader from './components/LoginHeader';
import UserApi from "../../Api/UserApi.tsx";

const Login: React.FC = () => {
  useEffect(() => {
    const fetchUser = async () => {
      const listUser = await UserApi.getAll();
      console.log(listUser);
    }
    fetchUser();
  }, []);

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

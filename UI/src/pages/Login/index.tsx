import React, { useState } from 'react';
import { Box, Button, TextField, Typography, Link, Container, Paper, InputAdornment, IconButton } from '@mui/material';
import Visibility from '@mui/icons-material/Visibility';
import VisibilityOff from '@mui/icons-material/VisibilityOff';
import { useNavigate } from 'react-router-dom';
import LoginForm from './components/LoginForm';
import LoginHeader from './components/LoginHeader';

const Login: React.FC = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [showPassword, setShowPassword] = React.useState(false);
  const [emailError, setEmailError] = useState('');
  const [passwordError, setPasswordError] = useState('');

  const navigate = useNavigate();

  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };
  const handleLogin = (e: React.FormEvent) => {
    e.preventDefault();
    if (validateForm()) {
      if (email === 'admin@example.com' && password === 'password') {
        navigate('/dashboard');
      } else {
        alert('Đăng nhập thất bại');
      }
    }
  };

  const validateForm = (): boolean => {
    let isValid = true;

    // Kiểm tra email
    if (!email) {
      setEmailError('Vui lòng nhập email');
      isValid = false;
    } else if (!/\S+@\S+\.\S+/.test(email)) {
      setEmailError('Email không hợp lệ');
      isValid = false;
    } else {
      setEmailError(''); // Không có lỗi
    }

    // Kiểm tra mật khẩu
    if (!password) {
      setPasswordError('Vui lòng nhập mật khẩu');
      isValid = false;
    } else if (password.length < 6) {
      setPasswordError('Mật khẩu phải có ít nhất 6 ký tự');
      isValid = false;
    } else {
      setPasswordError(''); // Không có lỗi
    }

    return isValid;
  };

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
          <Typography variant="body2" sx={{ color: 'white' }}>
            <Link href="#" sx={{ fontWeight: 'bold', color: '#ffffff', textDecoration: 'underline' }}>
              Đăng ký tại đây
            </Link>
          </Typography>
        </Box>
      </Container>
    </Box>
  );
};

export default Login;

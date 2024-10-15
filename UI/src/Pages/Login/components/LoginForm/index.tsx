import React, { useState } from 'react';
import { Alert, Box, Button, Link, Paper, Typography } from '@mui/material';
import InputTextField from '../../../../Components/FormControll/InputTextField'; // Import InputTextField
import InputPasswordField from '../../../../Components/FormControll/InputPasswordField'; // Import InputPasswordField
import { useNavigate } from 'react-router-dom';
import authApi from '../../../../Api/AuthApi.tsx';

interface use

const LoginForm: React.FC = () => {
  const [email, setEmail] = useState('admin@gmail.com');
  const [password, setPassword] = useState('admin');
  const [emailError, setEmailError] = useState('');
  // const [passwordError, setPasswordError] = useState('');

  const navigate = useNavigate();

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();
    if (validateForm()) {
      try {
        const response = await authApi.login(email, password);
        console.log(response);
        if (response.code == "200") {
          navigate('/dashboard');
        } else {
        console.error('Login error:');

          <Alert severity="error">This is an error Alert.</Alert>
        }
      } catch (error) {
        console.error('Login error in BE:', error);
        <Alert severity="error">This is an error Alert.</Alert>

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
    // if (!password) {
    //   setPasswordError('Vui lòng nhập mật khẩu');
    //   isValid = false;
    // } else if (password.length < 6) {
    //   setPasswordError('Mật khẩu phải có ít nhất 6 ký tự');
    //   isValid = false;
    // } else {
    //   setPasswordError(''); // Không có lỗi
    // }

    return isValid;
  };

  return (
    <Paper elevation={10} sx={{ p: 4, borderRadius: '8px' }}>
      <Box sx={{ textAlign: 'center', mb: 2 }}>
        <Typography
          variant="h5" component="h3">
          Chào mừng bạn trở lại
        </Typography>
        <Typography variant="body2" color="textSecondary">
          Đây là trang quản lý
        </Typography>
      </Box>
      <Box component="form" onSubmit={handleLogin} sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}>
        <InputTextField
          label="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          error={!!emailError}
          helperText={emailError}
        />

        <InputPasswordField
          label="Mật khẩu"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          error={!!passwordError}
          helperText={passwordError}
          
        />

        <Box sx={{ textAlign: 'left', mb: 1 }}>
          <Link href="#" variant="body2" sx={{ color: '#065f46', textDecoration: 'none' }}>
            Quên mật khẩu?
          </Link>
        </Box>

        <Button
          type="submit"
          variant="contained"
          color="success"
          fullWidth
          sx={{
            backgroundColor: '#065f46',
            ':hover': {
              backgroundColor: '#047857',
            },
          }}
        >
          Đăng nhập
        </Button>
      </Box>
    </Paper>
  );
};

export default LoginForm;

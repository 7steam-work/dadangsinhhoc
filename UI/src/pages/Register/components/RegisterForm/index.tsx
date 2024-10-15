import React, { useState } from 'react';
import { Box, Button, Paper, Typography } from '@mui/material';
import InputTextField from '../../../../Components/FormControll/InputTextField'; // Import InputTextField
import InputPasswordField from '../../../../Components/FormControll/InputPasswordField'; // Import InputPasswordField
import { Link, useNavigate } from 'react-router-dom';

const LoginForm: React.FC = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [rePassword, setRePassword] = useState('');
  const [emailError, setEmailError] = useState('');
  const [passwordError, setPasswordError] = useState('');
  const [rePasswordError, setRePasswordError] = useState('');


  const navigate = useNavigate();

  const handleLogin = (e: React.FormEvent) => {
    e.preventDefault();
    if (validateForm()) {
      if (email === 'admin@example.com' && password === 'password') {
        navigate('/dashboard');
      } else {
        alert('Đăng ký thất bại');
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

    // Kiểm tra xác nhận mật khẩu
    if (!rePassword) {
      setRePasswordError('Vui lòng xác nhận lại mật khẩu');
      isValid = false;
    } else if (rePassword === password) {
      setRePasswordError('Không trùng với mật khẩu đã nhập');
      isValid = false;
    } else {
      setRePasswordError(''); // Không có lỗi
    }

    return isValid;
  };

  return (
    <Paper elevation={10} sx={{ p: 4, borderRadius: '8px' }}>
      <Box sx={{ textAlign: 'center', mb: 2 }}>
        <Typography
          variant="h5" component="h3">
          Đăng ký
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

        <InputPasswordField
          label="Nhập lại Mật khẩu"
          value={rePassword}
          onChange={(e) => setRePassword(e.target.value)}
          error={!!rePasswordError}
          helperText={rePasswordError}
        />

        <Box sx={{ textAlign: 'left', mb: 1, color: '#065f46', textDecoration: 'none', display: 'flex', alignItems: 'center' }}>
          <Typography variant="body2" color="textSecondary">
            Bạn đã có tài khoản? <Link to="/">
              Đăng nhập
            </Link>
          </Typography>

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
          Đăng ký
        </Button>
      </Box>
    </Paper>
  );
};

export default LoginForm;

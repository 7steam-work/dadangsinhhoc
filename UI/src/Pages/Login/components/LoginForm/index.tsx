import React, { useState } from 'react';
import { Box, Button, Link, Paper, Typography } from '@mui/material';
import InputTextField from '../../../../Components/FormControll/InputTextField'; // Import InputTextField
import InputPasswordField from '../../../../Components/FormControll/InputPasswordField'; // Import InputPasswordField
import CustomSnackbar from '../../../../Components/CustomSnackBar';
import { useNavigate } from 'react-router-dom';
import authApi from '../../../../Api/AuthApi.tsx';

const LoginForm: React.FC = () => {
  //nhận value nhập vào
  const [email, setEmail] = useState('admin@gmail.com');
  const [password, setPassword] = useState('admin');
  //check validate các trường
  const [emailError, setEmailError] = useState('');
  const [passwordError, setPasswordError] = useState('');
  //snackbar thông báo
  const [snackbarOpen, setSnackbarOpen] = useState(false);
  const [snackbarMessage, setSnackbarMessage] = useState('');


  const navigate = useNavigate();

  interface LoginResponse {
    code: string; // hoặc number, tùy thuộc vào API của bạn
    // Thêm các thuộc tính khác nếu cần
  }

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();
    if (validateForm()) {
      try {
        const response = await authApi.login(email, password) as unknown; // Ép kiểu thành unknown trước
        const loginResponse = response as LoginResponse; // Sau đó ép kiểu thành LoginResponse

        console.log(loginResponse);

        if (loginResponse && loginResponse.code === "200") {
          navigate('/dashboard');
        } else {
          setSnackbarMessage('Đăng nhập thất bại');
          setSnackbarOpen(true);
        }
      } catch (error) {
        console.error('Login error in BE:', error);
        setSnackbarMessage('Đăng nhập thất bại do server');
        setSnackbarOpen(true);
      }
    }
  };

  const handleCloseSnackbar = (event?: React.SyntheticEvent | Event, reason?: string) => {
    if (reason === 'clickaway') {
      return;
    }
    setSnackbarOpen(false);
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
    } else {
      setPasswordError(''); // Không có lỗi
    }

    return isValid;
  };

  return (
    <>
      <CustomSnackbar
        open={snackbarOpen}
        message={snackbarMessage}
        onClose={handleCloseSnackbar}
        severity='error'
      />
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
    </>
  );
};


export default LoginForm;

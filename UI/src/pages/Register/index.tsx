import React, { useState } from 'react';
import { Button, TextField, Box, Typography } from '@mui/material';

const Register: React.FC = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleRegister = (e: React.FormEvent) => {
    e.preventDefault();
    alert('Đăng ký thành công');
  };

  return (
    <Box
      component="form"
      onSubmit={handleRegister}
      sx={{ width: 300, margin: '100px auto', display: 'flex', flexDirection: 'column', gap: 2 }}
    >
      <Typography variant="h5">Register</Typography>
      <TextField
        label="Email"
        type="email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        fullWidth
      />
      <TextField
        label="Password"
        type="password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        fullWidth
      />
      <Button type="submit" variant="contained" color="primary" fullWidth>
        Register
      </Button>
    </Box>
  );
};

export default Register;

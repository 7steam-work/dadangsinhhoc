import React from 'react';
import { Box, Typography } from '@mui/material';
import { UserForm } from '../components/UserForm';

const AddUser: React.FC = () => {
  return (
    <Box sx={{ p: 3 }}>
      <Typography variant="h5" gutterBottom>
        Thêm mới người dùng
      </Typography>
      <UserForm />
    </Box>
  );
};

export default AddUser;
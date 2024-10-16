import React from 'react';
import { Box, Typography } from '@mui/material';
import { UserForm } from '../components/UserForm';
import { useParams } from 'react-router-dom';

const EditUser: React.FC = () => {
  const { id } = useParams<{ id: string }>();

  return (
    <Box sx={{ p: 3 }}>
      <Typography variant="h5" gutterBottom>
        Chỉnh sửa người dùng
      </Typography>
      <UserForm userId={id} />
    </Box>
  );
};

export default EditUser;
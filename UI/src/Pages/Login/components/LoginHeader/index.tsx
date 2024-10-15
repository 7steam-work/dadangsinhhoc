import React from 'react';
import { Box, Typography } from '@mui/material';

const LoginHeader: React.FC = () => {

  return (
    <Box sx={{ textAlign: 'center', mb: 4 }}>
      <Typography variant="h4" component="h1" sx={{ color: 'white', fontWeight: 'bold' }}>
        Đa Dạng Sinh Học
      </Typography>
    </Box>
  );
};

export default LoginHeader;

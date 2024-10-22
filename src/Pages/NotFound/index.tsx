import React from 'react';
import { Box, Typography, Button } from '@mui/material';
import { Link } from 'react-router-dom';

const NotFound: React.FC = () => {
  return (
    <Box
      display="flex"
      flexDirection="column"
      justifyContent="center"
      alignItems="center"
      minHeight="100vh"
    >
      <Typography variant="h1" color="error" gutterBottom>
        404
      </Typography>
      <Typography variant="h5" color="textSecondary" gutterBottom>
        Oops! Trang bạn đang tìm kiếm không tồn tại.
      </Typography>
      <Button component={Link} to="/dashboard" variant="contained" color="primary">
        Quay về Trang chủ
      </Button>
    </Box>
  );
};

export default NotFound;
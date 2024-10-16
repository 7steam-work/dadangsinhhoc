import React from 'react';
import Header from '../Components/Header';
import Sidebar from '../Components/SideBar';
import { Box } from '@mui/material';
import { Outlet } from 'react-router-dom';

const AdminLayout: React.FC = () => {
  return (
    <Box sx={{ display: 'flex', flexDirection: 'column', minHeight: '100vh' }}>
      {/* Header ở trên cùng */}
      <Header />

      {/* Content and Sidebar layout */}
      <Box sx={{ display: 'flex', flex: 1 }}>
        {/* Sidebar chiếm diện tích nhỏ */}
        <Sidebar />

        {/* Nội dung chính */}
        <Box
          component="main"
          sx={{
            flexGrow: 1,
            p: 3,
            backgroundColor: '#f4f6f8',
            minHeight: 'calc(100vh - 64px)',
          }}
        >
          <Outlet />
        </Box>
      </Box>
    </Box>
  );
};

export default AdminLayout;

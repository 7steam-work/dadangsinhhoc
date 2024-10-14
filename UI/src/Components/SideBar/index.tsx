import React from 'react';
import { Box, List, ListItemButton, ListItemText } from '@mui/material';
import { NavLink } from 'react-router-dom';

const Sidebar: React.FC = () => {

  return (
    <Box
      sx={{
        width: '250px',
        backgroundColor: '#065f46',
        color: 'white',
        minHeight: '100vh',
      }}
    >
      <List>
        <ListItemButton component={NavLink} to="/dashboard" >
          <ListItemText primary="Dashboard" />
        </ListItemButton>
        <ListItemButton component={NavLink} to="/user">
          <ListItemText primary="User" />
        </ListItemButton>
      </List>
    </Box>
  );
};

export default Sidebar;

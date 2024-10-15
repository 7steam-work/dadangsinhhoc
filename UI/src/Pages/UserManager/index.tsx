import React, { useState } from 'react';
import CustomTable, { Column } from '../../Components/Table';
import { Box, Tab } from '@mui/material';
import { TabContext, TabList, TabPanel } from '@mui/lab';

const UserManager: React.FC = () => {
  const [value, setValue] = useState('1'); // State để theo dõi tab hiện tại

  const handleChange = (event: React.SyntheticEvent, newValue: string) => {
    setValue(newValue); // Cập nhật giá trị tab khi thay đổi
  };

  const columns: Column[] = [
    { id: 'name', label: 'Tên', minWidth: 150, type: 'text' },
    { id: 'age', label: 'Tuổi', minWidth: 100, type: 'text' },
    { id: 'active', label: 'Trạng thái', minWidth: 100, type: 'switch' },
    { id: 'action', label: 'Hành động', minWidth: 150, type: 'action' },
  ];

  const adminUsers = [
    { name: 'Admin A', age: 35, active: true },
    { name: 'Admin B', age: 42, active: true },
  ];

  const normalUsers = [
    { name: 'User A', age: 22, active: false },
    { name: 'User B', age: 28, active: true },
  ];
  return (
    <div>
      <h1>Danh sách</h1>
      <TabContext value={value}>
        <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
          <TabList onChange={handleChange} aria-label="lab API tabs example">
            <Tab label="User" value="1" />
            <Tab label="Admin" value="2" />
          </TabList>
        </Box>
        <TabPanel value="1">
          <CustomTable columns={columns} rows={adminUsers} />
        </TabPanel>
        <TabPanel value="2">
          <CustomTable columns={columns} rows={normalUsers} />
        </TabPanel>
      </TabContext>
    </div>
  );
};

export default UserManager;

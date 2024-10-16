import React, { useEffect, useState } from 'react';
import CustomTable, { Column } from '../../Components/Table';
import { Box, Tab } from '@mui/material';
import { TabContext, TabList, TabPanel } from '@mui/lab';
import UserApi from '../../Api/UserApi';

const UserManager: React.FC = () => {
  const [listUser, setListUser] = useState([]);
  const [listAdmin, setListAdmin] = useState([]);

  useEffect(() => {
    const fetchUser = async () => {
      const responseUser = await UserApi.getUserByRoll("ROLE_USER");
      setListUser(responseUser.data);

      const responseAdmin = await UserApi.getUserByRoll("ROLE_ADMIN");
      setListAdmin(responseAdmin.data);
    }
    fetchUser();
  }, []);

  const [value, setValue] = useState('1'); // State để theo dõi tab hiện tại

  const handleChange = (_event: React.SyntheticEvent, newValue: string) => {
    setValue(newValue); // Cập nhật giá trị tab khi thay đổi
    
  };

  const columns: Column[] = [
    { id: 'name', label: 'Tên', minWidth: 150, type: 'text' },
    { id: 'email', label: 'Email', minWidth: 200, type: 'text' },
    { id: 'phone', label: 'Số điện thoại', minWidth: 100, type: 'text' },
    { id: 'dob', label: 'Tuổi', minWidth: 75, type: 'text' },
    { id: 'gender', label: 'Giới tính', minWidth: 75, type: 'text' },
    { id: 'address', label: 'Địa chỉ', minWidth: 100, type: 'text' },
    { id: 'lastSigninedTime', label: 'Lần cuối đăng nhập', minWidth: 100, type: 'text' },
    { id: 'age', label: 'Tuổi', minWidth: 100, type: 'text' },
    { id: 'status', label: 'Trạng thái', minWidth: 100, type: 'switch' },
    { id: 'action', label: 'Hành động', minWidth: 100, type: 'action' },
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
          <CustomTable columns={columns} rows={listUser} />
        </TabPanel>
        <TabPanel value="2">
          <CustomTable columns={columns} rows={listAdmin} />
        </TabPanel>
      </TabContext>
    </div>
  );
};

export default UserManager;

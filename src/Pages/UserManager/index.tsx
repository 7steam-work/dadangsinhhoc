import React, { useState } from 'react';
import { Box, Button, Tab } from '@mui/material';
import { TabContext, TabList, TabPanel } from '@mui/lab';
import { useNavigate } from 'react-router-dom';
import { useUserData } from './hooks/useUserData';
import { UserTable } from './components/UserTable';
import UserApi from '../../Api/UserApi';
import { User } from './type';

const UserManager: React.FC = () => {
  const navigate = useNavigate();
  const [tabValue, setTabValue] = useState('1');
  const { listUser, listAdmin, isLoading, handleStatusChange, refreshUsers } = useUserData();

  const handleTabChange = (_event: React.SyntheticEvent, newValue: string) => {
    setTabValue(newValue);
  };

  const handleView = (user: User) => {
    console.log('xem user', user.id)
    // navigate(`/user/${user.id}`);
  };

  const handleEdit = (user: User) => {
    console.log('sửa user', user.id)
    // navigate(`/user/edit/${user.id}`);
  };

  const handleDelete = async (user: User) => {
    if (window.confirm('Bạn có chắc chắn muốn xóa người dùng này?')) {
      try {
        await UserApi.deleteUser(user.id);
        refreshUsers(); // Refresh data sau khi xóa
      } catch (error) {
        console.error('Error deleting user:', error);
        // Có thể thêm xử lý error ở đây
      }
    }
  };

  return (
    <div>
      <h1>Danh sách User</h1>
      <TabContext value={tabValue}>
        <Box sx={{
          borderBottom: 1,
          borderColor: 'divider',
          display: 'flex',
          alignItems: 'center',
          mb: 2
        }}>
          <TabList onChange={handleTabChange}>
            <Tab label="User" value="1" />
            <Tab label="Admin" value="2" />
          </TabList>
          <Button
            variant="contained"
            color="primary"
            onClick={() => navigate('/add-user')}
            sx={{ ml: 'auto', mr: 2 }}
          >
            Thêm mới
          </Button>
        </Box>

        {isLoading ? (
          <div>Loading...</div>
        ) : (
          <>
            <TabPanel value="1">
              <UserTable
                users={listUser}
                onStatusChange={handleStatusChange}
                onView={handleView}
                onEdit={handleEdit}
                onDelete={handleDelete}
              />
            </TabPanel>
            <TabPanel value="2">
              <UserTable
                users={listAdmin}
                onStatusChange={handleStatusChange}
                onView={handleView}
                onEdit={handleEdit}
                onDelete={handleDelete}
              />
            </TabPanel>
          </>
        )}
      </TabContext>
    </div>
  );
};

export default UserManager;
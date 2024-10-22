import React, { useState } from 'react';
import { Box, Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle, Tab } from '@mui/material';
import { TabContext, TabList, TabPanel } from '@mui/lab';
// import { useNavigate } from 'react-router-dom';
import { useUserData } from './hooks/useUserData';
import { UserTable } from './components/UserTable';
import { UserModal } from './components/UserModal';
import UserApi from '../../Api/UserApi';
import { User } from './type';

const UserManager: React.FC = () => {
  // const navigate = useNavigate();
  const [tabValue, setTabValue] = useState('1');
  const { listUser, listAdmin, isLoading, handleStatusChange, refreshUsers } = useUserData();

  // State for modal
  const [modalOpen, setModalOpen] = useState(false);
  const [selectedUserId, setSelectedUserId] = useState<string | undefined>();
  const [modalMode, setModalMode] = useState<'add' | 'edit' | 'readonly'>('add');
  const [deleteDialogOpen, setDeleteDialogOpen] = useState(false);
  const [userToDelete, setUserToDelete] = useState<User | null>(null);


  const handleTabChange = (_event: React.SyntheticEvent, newValue: string) => {
    setTabValue(newValue);
  };

  const handleOpenModal = (mode: 'add' | 'edit' | 'readonly', userId?: string) => {
    setModalMode(mode);
    setSelectedUserId(userId);
    setModalOpen(true);
  };

  const handleCloseModal = () => {
    setModalOpen(false);
    setSelectedUserId(undefined);
    refreshUsers();
  };

  const handleView = (user: User) => {
    handleOpenModal('readonly', user.id);
  };

  const handleEdit = (user: User) => {
    handleOpenModal('edit', user.id);
  };

  const showDialogDelete = (user: User) => {
    setUserToDelete(user);
    setDeleteDialogOpen(true);
  };

  const handleDeleteConfirm = async () => {
    if (userToDelete) {
      try {
        await UserApi.deleteUser(userToDelete.id);
        refreshUsers();
      } catch (error) {
        console.error('Error deleting user:', error);
      }
    }
    setDeleteDialogOpen(false);
    setUserToDelete(null);
  };

  const handleDeleteCancel = () => {
    setDeleteDialogOpen(false);
    setUserToDelete(null);
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
            onClick={() => handleOpenModal('add')}
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
                onDelete={showDialogDelete}
              />
            </TabPanel>
            <TabPanel value="2">
              <UserTable
                users={listAdmin}
                onStatusChange={handleStatusChange}
                onView={handleView}
                onEdit={handleEdit}
                onDelete={showDialogDelete}
              />
            </TabPanel>
          </>
        )}
        <UserModal
          open={modalOpen}
          onClose={handleCloseModal}
          userId={selectedUserId}
          mode={modalMode}
        />

      <Dialog
        open={deleteDialogOpen}
        onClose={handleDeleteCancel}
      >
        <DialogTitle>Xác nhận xóa</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Bạn có chắc chắn muốn xóa người dùng này?
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleDeleteCancel}>Hủy</Button>
          <Button onClick={handleDeleteConfirm} color="error">
            Xóa
          </Button>
        </DialogActions>
      </Dialog>
      </TabContext>
    </div>
  );
};

export default UserManager;
import React from 'react';
import {
  Dialog,
  DialogTitle,
  DialogContent,
  IconButton,
  Typography,
  Box
} from '@mui/material';
import CloseIcon from '@mui/icons-material/Close';
import { UserForm } from './UserForm';

interface UserModalProps {
  open: boolean;
  onClose: () => void;
  userId?: string;
  mode: 'add' | 'edit' | 'readonly';
}

export const UserModal: React.FC<UserModalProps> = ({
  open,
  onClose,
  userId,
  mode
}) => {
  const handleSuccess = () => {
    onClose();
  };

  const handleClose = (_event: React.MouseEvent, reason: string) => {
    if (reason !== 'backdropClick') {
      onClose();
    }
  };
  return (
    <Dialog
      open={open}
      onClose={handleClose}
      maxWidth="md"
      fullWidth
    >
      <DialogTitle>
        <Box display="flex" justifyContent="space-between" alignItems="center">
          <Typography variant="h6">
            {mode === 'add' ? 'Thêm mới người dùng' : mode === 'edit' ? 'Chỉnh sửa người dùng' : 'Xem chi tiết người dùng'}
          </Typography>
          <IconButton onClick={onClose} size="small">
            <CloseIcon />
          </IconButton>
        </Box>
      </DialogTitle>
      <DialogContent dividers>
        <UserForm
          userId={userId}
          onSuccess={handleSuccess}
          isModal={true}
          readOnly={mode === 'readonly'}
        />
      </DialogContent>
    </Dialog>
  );
};
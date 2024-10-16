import React from 'react';
import { CustomTable, Column, ActionButton } from '../../../Components/Table';
import { User } from '../type';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import VisibilityIcon from '@mui/icons-material/Visibility';

interface UserTableProps {
  users: User[];
  onStatusChange: (user: User, status: boolean) => void;
  onView: (user: User) => void;
  onEdit: (user: User) => void;
  onDelete: (user: User) => void;
}

export const UserTable: React.FC<UserTableProps> = ({
  users,
  onStatusChange,
  onView,
  onEdit,
  onDelete
}) => {
  const columns: Column[] = [
    { id: 'name', label: 'Tên', minWidth: 150 },
    { id: 'email', label: 'Email', minWidth: 150 },
    { id: 'phone', label: 'Số điện thoại', minWidth: 120 },
    { id: 'gender', label: 'Giới tính', minWidth: 75 },
    { id: 'address', label: 'Địa chỉ', minWidth: 150 },
    { id: 'lastSigninedTime', label: 'Lần cuối đăng nhập', minWidth: 100 },
    { id: 'status', label: 'Trạng thái', minWidth: 100, type: 'switch' },
    { id: 'action', label: 'Hành động', minWidth: 160, type: 'action' },
  ];

  const actions: ActionButton[] = [
    {
      icon: <VisibilityIcon />,
      tooltip: 'Xem chi tiết',
      onClick: onView,
      color: 'primary'
    },
    {
      icon: <EditIcon />,
      tooltip: 'Chỉnh sửa',
      onClick: onEdit,
      color: 'secondary'
    },
    {
      icon: <DeleteIcon />,
      tooltip: 'Xóa',
      onClick: onDelete,
      color: 'error'
    }
  ];

  return (
    <CustomTable
      columns={columns}
      rows={users}
      actions={actions}
      onSwitchChange={onStatusChange}
    />
  );
};
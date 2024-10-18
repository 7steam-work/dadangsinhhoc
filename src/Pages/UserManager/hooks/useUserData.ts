import { useState, useEffect } from 'react';
import UserApi from '../../../Api/UserApi';
import { User } from '../type';

export const useUserData = () => {
  const [listUser, setListUser] = useState<User[]>([]);
  const [listAdmin, setListAdmin] = useState<User[]>([]);
  const [isLoading, setIsLoading] = useState(false);

  const updateUserStatus = (users: User[]) => {
    const currentDate = new Date();
    return users.map((user) => {
      const lastSigninedTime = new Date(user.lastSigninedTime);
      const diffTime = currentDate.getTime() - lastSigninedTime.getTime();
      const diffDays = diffTime / (1000 * 3600 * 24);

      return {
        ...user,
        lastSigninedTime: diffDays < 1 ? 'Gần đây' : `Cách đây ${Math.floor(diffDays)} ngày`,
      };
    });
  };

  const fetchUsers = async () => {
    try {
      setIsLoading(true);
      const [responseUser, responseAdmin] = await Promise.all([
        UserApi.getUserByRoll("ROLE_USER"),
        UserApi.getUserByRoll("ROLE_ADMIN")
      ]);

      setListUser(updateUserStatus(responseUser.data));
      setListAdmin(updateUserStatus(responseAdmin.data));
    } catch (error) {
      console.error('Error fetching users:', error);
      // Có thể thêm xử lý error ở đây
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  const handleStatusChange = async (user: User, newStatus: boolean) => {
    try {
      // Gọi API để cập nhật status
      await UserApi.updateUserStatus(user.id, newStatus);
      // Cập nhật local state
      const updateList = (list: User[]) =>
        list.map(item => item.id === user.id ? { ...item, status: newStatus } : item);

      setListUser(updateList(listUser));
      setListAdmin(updateList(listAdmin));
    } catch (error) {
      console.error('Error updating user status:', error);
      // Có thể thêm xử lý error ở đây
    }
  };

  return {
    listUser,
    listAdmin,
    isLoading,
    handleStatusChange,
    refreshUsers: fetchUsers
  };
};
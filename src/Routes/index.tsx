import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import AdminLayout from '../Layouts/AdminLayout';
import Login from '../Pages/Login';
import Register from '../Pages/Register';
import Dashboard from '../Pages/Dashboard';
import UserManager from '../Pages/UserManager';
import AddUser from '../Pages/UserManager/Pages/AddUser';
import EditUser from '../Pages/UserManager/Pages/EditUser';

const AppRoutes: React.FC = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route element={<AdminLayout />}>
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/user" element={<UserManager />} />
          <Route path="/add-user" element={<AddUser />} />
          <Route path="/user/edit/:id" element={<EditUser />} />
        </Route>
      </Routes>
    </Router>
  );
};

export default AppRoutes;

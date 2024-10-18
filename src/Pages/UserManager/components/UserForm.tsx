import React from 'react';
import {
  Box,
  TextField,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
  FormHelperText,
  Button,
  Grid,
  FormControlLabel,
  Switch,
  CircularProgress
} from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { useUserForm } from '../hooks/useUserForm';

interface UserFormProps {
  userId?: string;
  onSuccess?: () => void;
  isModal?: boolean;
  readOnly?: boolean;
}

export const UserForm: React.FC<UserFormProps> = ({ userId, onSuccess, isModal, readOnly = false }) => {
  const navigate = useNavigate();
  const {
    formData,
    errors,
    isLoading,
    isSubmitting,
    handleInputChange,
    handleSubmit
  } = useUserForm(userId);

  const onSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    const success = await handleSubmit();
    if (success) {
      if (isModal && onSuccess) {
        onSuccess();
      } else {
        navigate('/user');
      }
    }
  };

  if (isLoading) {
    return (
      <Box display="flex" justifyContent="center" alignItems="center" minHeight="400px">
        <CircularProgress />
      </Box>
    );
  }

  return (
    <Box component="form" onSubmit={onSubmit} noValidate sx={{ mt: 2 }}>
      <Grid container spacing={3}>
        <Grid item xs={12} sm={6}>
          <TextField
            fullWidth
            label="Tên"
            value={formData.name}
            onChange={(e) => handleInputChange('name', e.target.value)}
            error={!!errors.name}
            helperText={errors.name}
            disabled={readOnly}
          />
        </Grid>

        <Grid item xs={12} sm={6}>
          <TextField
            fullWidth
            label="Email"
            type="email"
            value={formData.email}
            onChange={(e) => handleInputChange('email', e.target.value)}
            error={!!errors.email}
            helperText={errors.email}
            disabled={readOnly}
          />
        </Grid>

        <Grid item xs={12} sm={6}>
          <TextField
            fullWidth
            label="Số điện thoại"
            value={formData.phone}
            onChange={(e) => handleInputChange('phone', e.target.value)}
            error={!!errors.phone}
            helperText={errors.phone}
            disabled={readOnly}
          />
        </Grid>

        <Grid item xs={12} sm={6}>
          <FormControl fullWidth error={!!errors.gender}>
            <InputLabel>Giới tính</InputLabel>
            <Select
              value={formData.gender}
              onChange={(e) => handleInputChange('gender', e.target.value)}
              label="Giới tính"
              disabled={readOnly}
            >
              <MenuItem value="MALE">Nam</MenuItem>
              <MenuItem value="FEMALE">Nữ</MenuItem>
              {/* <MenuItem value="OTHER">Khác</MenuItem> */}
            </Select>
            {errors.gender && <FormHelperText>{errors.gender}</FormHelperText>}
          </FormControl>
        </Grid>

        <Grid item xs={12}>
          <TextField
            fullWidth
            label="Địa chỉ"
            value={formData.address}
            onChange={(e) => handleInputChange('address', e.target.value)}
            error={!!errors.address}
            helperText={errors.address}
            disabled={readOnly}
          />
        </Grid>

        {!userId && (
          <Grid item xs={12} sm={6}>
            <TextField
              fullWidth
              label="Mật khẩu"
              type="password"
              value={formData.password}
              onChange={(e) => handleInputChange('password', e.target.value)}
              error={!!errors.password}
              helperText={errors.password}
            />
          </Grid>
        )}

          <Grid item xs={12} sm={6}>
            <TextField
              fullWidth
              type="date"
              value={formData.dob}
              onChange={(e) => handleInputChange('dob', e.target.value)}
              error={!!errors.dob}
              disabled={readOnly}
            />
          </Grid>

        <Grid item xs={12} sm={6}>
          <FormControl fullWidth error={!!errors.role}>
            <InputLabel>Vai trò</InputLabel>
            <Select
              value={formData.role}
              onChange={(e) => handleInputChange('role', e.target.value)}
              label="Vai trò"
              disabled={readOnly}
            >
              <MenuItem value="ROLE_USER">User</MenuItem>
              <MenuItem value="ROLE_ADMIN">Admin</MenuItem>
            </Select>
            {errors.role && <FormHelperText>{errors.role}</FormHelperText>}
          </FormControl>
        </Grid>

        <Grid item xs={12}>
          <FormControlLabel
            control={
              <Switch
                checked={formData.status}
                onChange={(e) => handleInputChange('status', e.target.checked)}
                disabled={readOnly}
              />
            }
            label="Trạng thái hoạt động"
          />
        </Grid>

        {!readOnly && (
          <Grid item xs={12}>
            <Box sx={{ display: 'flex', gap: 2, justifyContent: 'flex-end' }}>
              <Button
                variant="outlined"
                onClick={isModal ? onSuccess : () => navigate('/user')}
                disabled={isSubmitting}
              >
                Hủy
              </Button>
              <Button
                type="submit"
                variant="contained"
                disabled={isSubmitting}
                startIcon={isSubmitting ? <CircularProgress size={20} /> : null}
              >
                {userId ? 'Cập nhật' : 'Thêm mới'}
              </Button>
            </Box>
          </Grid>
        )}
      </Grid>
    </Box>
  );
};
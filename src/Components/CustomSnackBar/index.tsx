import React from 'react';
import Snackbar from '@mui/material/Snackbar';
import MuiAlert, { AlertProps } from '@mui/material/Alert';

interface CustomSnackbarProps {
  open: boolean;
  message: string;
  onClose: (event?: React.SyntheticEvent | Event, reason?: string) => void;
  severity: 'success' | 'info' | 'warning' | 'error';
}


const Alert = React.forwardRef<HTMLDivElement, AlertProps>(function Alert(props, ref) {
  return <MuiAlert elevation={6} ref={ref} variant="filled" {...props} />;
});

const CustomSnackbar: React.FC<CustomSnackbarProps> = ({ open, message, onClose, severity = 'error' }) => {
  return (
    <Snackbar open={open} anchorOrigin={{ vertical: 'top', horizontal: 'right' }} autoHideDuration={6000} onClose={onClose}>
      <Alert onClose={onClose} severity={severity} sx={{ width: '100%' }}>
        {message}
      </Alert>
    </Snackbar>
  );
};

export default CustomSnackbar;
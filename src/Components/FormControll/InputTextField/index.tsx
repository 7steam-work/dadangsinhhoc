import React from 'react';
import { TextField } from '@mui/material';

interface InputTextFieldProps {
  label: string;
  value: string;
  onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
  error?: boolean;
  helperText?: string;
}

const InputTextField: React.FC<InputTextFieldProps> = ({
  label,
  value,
  onChange,
  error,
  helperText,
}) => {
  return (
    <TextField
      label={label}
      type="text"
      variant="outlined"
      fullWidth
      value={value}
      onChange={onChange}
      error={error}
      helperText={helperText}
      InputProps={{
        style: { backgroundColor: '#f3f4f6', borderBottomWidth: '2px' },
      }}
    />
  );
};

export default InputTextField;

import React from 'react';
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Button, IconButton } from '@mui/material';
import AddIcon from '@mui/icons-material/Add';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import VisibilityOutlinedIcon from '@mui/icons-material/VisibilityOutlined';

// Define types cho columns và rows
export interface Column {
  id: string;
  label: string;
  minWidth?: number;
  type?: string;
}

interface Row {
  name: string;
  age: number;
  active: boolean;
}

// CustomTable component
interface CustomTableProps {
  columns: Column[];
  rows: Row[];
}

const CustomTable: React.FC<CustomTableProps> = ({ columns, rows }) => {

  // Handlers cho các nút chức năng
  const handleDetail = (row: Row) => {
    alert(`Chi tiết: ${row.name}`);
  };

  const handleEdit = (row: Row) => {
    alert(`Sửa: ${row.name}`);
  };

  const handleDelete = (row: Row) => {
    alert(`Xóa: ${row.name}`);
  };

  return (
    <Paper sx={{ width: '100%', overflow: 'hidden' }}>
      <TableContainer sx={{ maxHeight: 440 }}>
        <Table stickyHeader aria-label="customized table">
          <TableHead>
            <TableRow>
              {columns.map((column) => (
                <TableCell
                  key={column.id}
                  style={{ minWidth: column.minWidth }}
                >
                  {column.label}
                </TableCell>
              ))}
            </TableRow>
          </TableHead>
          <TableBody>
            {rows.map((row, index) => (
              <TableRow hover role="checkbox" tabIndex={-1} key={index}>
                {columns.map((column) => {
                  const value = row[column.id as keyof Row];

                  // Kiểm tra xem cột có phải là "action" để hiển thị các nút chức năng
                  if (column.type === 'action') {
                    return (
                      <TableCell key={column.id}>
                        {/* Nút Thêm */}
                        <IconButton color="primary" onClick={() => handleDetail(row)}>
                          <VisibilityOutlinedIcon />
                        </IconButton>
                        {/* Nút Sửa */}
                        <IconButton color="secondary" onClick={() => handleEdit(row)}>
                          <EditIcon />
                        </IconButton>
                        {/* Nút Xóa */}
                        <IconButton color="error" onClick={() => handleDelete(row)}>
                          <DeleteIcon />
                        </IconButton>
                      </TableCell>
                    );
                  }

                  // Hiển thị giá trị thông thường
                  return (
                    <TableCell key={column.id}>
                      {column.type === 'switch' ? (
                        value ? 'Hoạt động' : 'Không hoạt động'
                      ) : (
                        value
                      )}
                    </TableCell>
                  );
                })}
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </Paper>
  );
};

export default CustomTable;

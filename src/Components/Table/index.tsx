import React, { useState } from 'react';
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Button, IconButton, TablePagination } from '@mui/material';
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
  const [page, setPage] = useState(0); // State cho trang hiện tại
  const [rowsPerPage, setRowsPerPage] = useState(5); // Số hàng trên mỗi trang

  // Hàm xử lý thay đổi trang
  const handleChangePage = (event: unknown, newPage: number) => {
    setPage(newPage);
  };

  // Hàm xử lý thay đổi số hàng trên mỗi trang
  const handleChangeRowsPerPage = (event: React.ChangeEvent<HTMLInputElement>) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0); // Reset về trang đầu tiên khi thay đổi số hàng
  };

  // Tính toán hàng để hiển thị trên trang hiện tại
  const displayedRows = rows.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage);


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
            {displayedRows.map((row, index) => (
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

      <TablePagination
        rowsPerPageOptions={[5, 10, 25]} // Các tùy chọn số hàng trên mỗi trang
        component="div"
        count={rows.length} // Tổng số hàng
        rowsPerPage={rowsPerPage} // Số hàng trên mỗi trang
        page={page} // Trang hiện tại
        onPageChange={handleChangePage} // Hàm xử lý thay đổi trang
        onRowsPerPageChange={handleChangeRowsPerPage} // Hàm xử lý thay đổi số hàng trên mỗi trang
      />
    </Paper>
  );
};

export default CustomTable;

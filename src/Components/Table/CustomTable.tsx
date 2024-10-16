import React, { useState } from 'react';
import { Table, TableContainer, Paper } from '@mui/material';
import { TableHeader } from './Components/TableHeader';
import { TableBody } from './Components/TableBody';
import { TablePagination } from './Components/TablePagination';
import { CustomTableProps } from './types';

const CustomTable = <T extends Record<string, any>>({
  columns,
  rows,
  actions = [],
  onSwitchChange,
  rowsPerPageOptions = [5, 10, 25],
  initialRowsPerPage = 5,
  customComponents = {}
}: CustomTableProps<T>) => {
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(initialRowsPerPage);

  const handleChangePage = (event: unknown, newPage: number) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event: React.ChangeEvent<HTMLInputElement>) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  const displayedRows = rows.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage);

  return (
    <Paper sx={{ width: '100%', overflow: 'hidden' }}>
      <TableContainer sx={{ maxHeight: 440 }}>
        <Table stickyHeader aria-label="customized table">
          <TableHeader columns={columns} />
          <TableBody
            columns={columns}
            rows={displayedRows}
            actions={actions}
            onSwitchChange={onSwitchChange}
            customComponents={customComponents}
          />
        </Table>
      </TableContainer>

      <TablePagination
        count={rows.length}
        page={page}
        rowsPerPage={rowsPerPage}
        rowsPerPageOptions={rowsPerPageOptions}
        onPageChange={handleChangePage}
        onRowsPerPageChange={handleChangeRowsPerPage}
      />
    </Paper>
  );
};

export default CustomTable;
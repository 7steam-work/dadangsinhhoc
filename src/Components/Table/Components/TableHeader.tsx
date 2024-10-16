import React from 'react';
import { TableHead, TableRow, TableCell } from '@mui/material';
import { Column } from '../types';

interface TableHeaderProps {
  columns: Column[];
}

export const TableHeader: React.FC<TableHeaderProps> = ({ columns }) => (
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
);
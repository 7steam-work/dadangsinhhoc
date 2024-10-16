import React from 'react';
import { TableBody as MuiTableBody, TableRow, TableCell } from '@mui/material';
import { ActionButton, Column } from '../types';
import { TableCellContent } from './TableCellContent';

interface TableBodyProps<T> {
  columns: Column[];
  rows: T[];
  actions?: ActionButton[];
  onSwitchChange?: (row: T, value: boolean) => void;
  customComponents?: {
    [key: string]: (row: T) => React.ReactNode;
  };
}

export const TableBody = <T extends Record<string, any>>({
  columns,
  rows,
  actions,
  onSwitchChange,
  customComponents
}: TableBodyProps<T>) => (
  <MuiTableBody>
    {rows.map((row, index) => (
      <TableRow hover role="checkbox" tabIndex={-1} key={index}>
        {columns.map((column) => (
          <TableCell key={column.id}>
            <TableCellContent
              column={column}
              row={row}
              actions={actions}
              onSwitchChange={onSwitchChange}
              customComponents={customComponents}
            />
          </TableCell>
        ))}
      </TableRow>
    ))}
  </MuiTableBody>
);
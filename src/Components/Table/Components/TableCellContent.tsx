import React from 'react';
import { Switch } from '@mui/material';
import { ActionButton, Column } from '../types';
import { TableActions } from './TableActions';

interface TableCellContentProps {
  column: Column;
  row: any;
  actions?: ActionButton[];
  onSwitchChange?: (row: any, checked: boolean) => void;
  customComponents?: {
    [key: string]: (row: any) => React.ReactNode;
  };
}

export const TableCellContent: React.FC<TableCellContentProps> = ({
  column,
  row,
  actions = [],
  onSwitchChange,
  customComponents = {}
}) => {
  const value = row[column.id];

  switch (column.type) {
    case 'action':
      return <TableActions actions={actions} row={row} />;

    case 'switch':
      return (
        <Switch
          checked={Boolean(value)}
          onChange={(e) => onSwitchChange?.(row, e.target.checked)}
        />
      );

    case 'custom':
      if (customComponents[column.id]) {
        return customComponents[column.id](row);
      }
      return String(value);

    default:
      return column.render ? column.render(row) : String(value);
  }
};
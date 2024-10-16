import React from 'react';
import { IconButton, Tooltip } from '@mui/material';
import { ActionButton } from '../types';

interface TableActionsProps {
  actions: ActionButton[];
  row: any;
}

export const TableActions: React.FC<TableActionsProps> = ({ actions, row }) => (
  <div style={{ display: 'flex', gap: '8px' }}>
    {actions.map((action, index) => (
      action.show?.(row) !== false && (
        <Tooltip key={index} title={action.tooltip}>
          <IconButton
            color={action.color || "primary"}
            onClick={() => action.onClick(row)}
          >
            {action.icon}
          </IconButton>
        </Tooltip>
      )
    ))}
  </div>
);
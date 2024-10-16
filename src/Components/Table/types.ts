export interface Column {
  id: string;
  label: string;
  minWidth?: number;
  type?: 'text' | 'action' | 'switch' | 'custom';
  render?: (row: any) => React.ReactNode;
}

export interface ActionButton {
  icon: React.ReactNode;
  tooltip: string;
  onClick: (row: any) => void;
  color?: "inherit" | "primary" | "secondary" | "error" | "info" | "success" | "warning";
  show?: (row: any) => boolean;
}

export interface CustomTableProps<T> {
  columns: Column[];
  rows: T[];
  actions?: ActionButton[];
  onSwitchChange?: (row: T, value: boolean) => void;
  rowsPerPageOptions?: number[];
  initialRowsPerPage?: number;
  customComponents?: {
    [key: string]: (row: T) => React.ReactNode;
  };
}
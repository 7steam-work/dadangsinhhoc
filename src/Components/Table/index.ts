export { default as CustomTable } from './CustomTable';

export type {
  Column,
  ActionButton,
  CustomTableProps
} from './types';

// Re-export individual components for flexibility
export { TableHeader } from './Components/TableHeader';
export { TableBody } from './Components/TableBody';
export { TableActions } from './Components/TableActions';
export { TableCellContent } from './Components/TableCellContent';
export { TablePagination } from './Components/TablePagination';
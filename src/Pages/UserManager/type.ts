export interface User {
  name: string;
  email: string;
  phone: string;
  gender: string;
  address: string;
  lastSigninedTime: string;
  status: boolean;
  id: string; // Thêm id để xử lý actions
}

export interface UserFormData {
  name: string;
  email: string;
  phone: string;
  gender: string;
  address: string;
  password?: string; // Optional for edit mode
  dob: string;
  role: string;
  status: boolean;
}

export interface UserFormErrors {
  name?: string;
  email?: string;
  phone?: string;
  gender?: string;
  address?: string;
  password?: string;
  dob?: Date;
  role?: string;
  status?: boolean;
}
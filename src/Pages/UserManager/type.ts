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
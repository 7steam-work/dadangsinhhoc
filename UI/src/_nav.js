import { cilRectangle, cilPenNib, cilNewspaper, cilEnvelopeClosed, cilAnimal, cilPlant, cilSpreadsheet } from '@coreui/icons'

export default [
  {
    component: 'CNavItem',
    name: 'Dashboard',
    to: '/dashboard',
    icon: 'cil-speedometer',
    // badge: {
    //   color: 'primary',
    //   text: 'NEW',
    // },
  },
  {
    component: 'CNavTitle',
    name: 'Danh mục quản lý',
  },
  {
    component: 'CNavGroup',
    name: 'Người dùng',
    to: '/nguoi-dung',
    icon: 'cil-people',
    items: [
      {
        component: 'CNavItem',
        name: 'Danh sách',
        to: '/nguoi-dung/danh-sach',
      },
      {
        component: 'CNavItem',
        name: 'Thêm mới',
        to: '/nguoi-dung/them-moi',
      },
      {
        component: 'CNavItem',
        name: 'Thông tin cá nhân',
        to: '/nguoi-dung/thong-tin-ca-nhan',
      },
    ],
  },
  {
    component: 'CNavGroup',
    name: 'Banner',
    to: '/banner',
    icon: cilRectangle,
    items: [
      {
        component: 'CNavItem',
        name: 'Danh sách',
        to: '/banner/danh-sach',
      },
      {
        component: 'CNavItem',
        name: 'Thêm mới',
        to: '/banner/them-moi',
      },
    ],
  },
  {
    component: 'CNavItem',
    name: 'Giới thiệu',
    to: '/gioi-thieu',
    icon: cilPenNib,
  },
  {
    component: 'CNavGroup',
    name: 'Tin tức',
    to: '/tin-tuc',
    icon: cilNewspaper,
    items: [
      {
        component: 'CNavItem',
        name: 'Danh sách',
        to: '/tin-tuc/danh-sach',
      },
      {
        component: 'CNavItem',
        name: 'Thêm mới',
        to: '/tin-tuc/them-moi',
      },
    ],
  },
  {
    component: 'CNavItem',
    name: 'Tin nhắn',
    to: '/tin-nhan',
    icon: cilEnvelopeClosed,
  },
  {
    component: 'CNavGroup',
    name: 'Động vật',
    to: '/dong-vat',
    icon: cilAnimal,
    items: [
      {
        component: 'CNavItem',
        name: 'Ngành',
        to: '/dong-vat/nganh',
      },
      {
        component: 'CNavItem',
        name: 'Lớp',
        to: '/dong-vat/lop',
      },
      {
        component: 'CNavItem',
        name: 'Bộ',
        to: '/dong-vat/bo',
      },
      {
        component: 'CNavItem',
        name: 'Họ',
        to: '/dong-vat/ho',
      },
      {
        component: 'CNavItem',
        name: 'Loài',
        to: '/dong-vat/loai',
      },
    ],
  },
  {
    component: 'CNavGroup',
    name: 'Thực vật',
    to: '/thuc-vat',
    icon: cilPlant,
    items: [
      {
        component: 'CNavItem',
        name: 'Ngành',
        to: '/thuc-vat/nganh',
      },
      {
        component: 'CNavItem',
        name: 'Lớp',
        to: '/thuc-vat/lop',
      },
      {
        component: 'CNavItem',
        name: 'Bộ',
        to: '/thuc-vat/bo',
      },
      {
        component: 'CNavItem',
        name: 'Họ',
        to: '/thuc-vat/ho',
      },
      {
        component: 'CNavItem',
        name: 'Loài',
        to: '/thuc-vat/loai',
      },
    ],
  },
  {
    component: 'CNavItem',
    name: 'Thống kê',
    to: '/thong-ke',
    icon: cilSpreadsheet,
  }
]

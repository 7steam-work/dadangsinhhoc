import { h, resolveComponent } from 'vue'
import { createRouter, createWebHashHistory } from 'vue-router'

import DefaultLayout from '@/layouts/DefaultLayout'
import Homepage from '@/views/guest/Homepage.vue'
import Login from '@/views/pages/Login.vue'

const routes = [
  {
    path: '/',
    redirect: '/homepage', // Redirect đến trang homepage khi ứng dụng khởi động
  },
  {
    path: '/homepage',
    name: 'Homepage',
    component: Homepage,
  },
  {
    path: '/info',
    name: 'Info',
    component: () => import('@/views/guest/GioiThieu.vue'),
  },
  {
    path: '/csdl/dong-vat',
    name: 'CSDL Động vật',
    component: () => import('@/views/guest/CSDLDongThucVat.vue'),
  },
  {
    path: '/csdl/thuc-vat',
    name: 'CSDL Thực vật',
    component: () => import('@/views/guest/CSDLDongThucVat.vue'),
  },
  {
    path: '/csdl/thong-tin-loai/:id',
    name: 'Thông tin loài',
    component: () => import('@/views/guest/ThongTinLoai.vue'),
  },
  {
    path: '/news',
    name: 'News',
    component: () => import('@/views/guest/TinTuc.vue'),
  },
  {
    path: '/contact',
    name: 'Contact',
    component: () => import('@/views/guest/LienHe.vue'),
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
  },
  {
    path: '/home',
    name: 'Home',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: DefaultLayout,
    redirect: '/dashboard',
    //meta: { requiresAuth: true },  // Cần đăng nhập để truy cập
    children: [
      {
        path: '/dashboard',
        name: 'Dashboard',
        component: () => import('@/views/dashboard/Dashboard1.vue'),
      },
      {
        path: '/nguoi-dung',
        name: 'Người dùng',
        component: {
          render() {
            return h(resolveComponent('router-view'))
          },
        },
        redirect: '/nguoi-dung/danh-sach',
        children: [
          {
            path: '/nguoi-dung/danh-sach',
            name: 'Danh sách người dùng',
            component: () => import('@/views/nguoidung/DanhSachNguoiDung.vue'),
          },
          {
            path: '/nguoi-dung/chi-tiet',
            name: 'Chi tiết người dùng',
            component: () => import('@/views/nguoidung/ChiTietNguoiDung.vue'),
          },
          {
            path: '/nguoi-dung/them-moi',
            name: 'Thêm mới người dùng',
            component: () => import('@/views/nguoidung/ThemMoiNguoiDung.vue'),
          },
          {
            path: '/nguoi-dung/chinh-sua/:id',
            name: 'Chỉnh sửa người dùng',
            component: () => import('@/views/nguoidung/ChinhSuaNguoiDung.vue'),
          },
          {
            path: '/nguoi-dung/thong-tin-ca-nhan',
            name: 'Thông tin cá nhân',
            component: () => import('@/views/nguoidung/ThongTinCaNhan.vue'),
          },
          {
            path: '/nguoi-dung/cap-nhat-thong-tin-ca-nhan',
            name: 'Cập nhật thông tin cá nhân',
            component: () => import('@/views/nguoidung/CapNhatThongTinCaNhan.vue'),
          },
          {
            path: '/nguoi-dung/doi-mat-khau',
            name: 'Đổi mật khẩu',
            component: () => import('@/views/nguoidung/DoiMatKhau.vue'),
          },
        ],
      },
      {
        path: '/banner',
        name: 'Banner',
        component: {
          render() {
            return h(resolveComponent('router-view'))
          },
        },
        redirect: '/banner/danh-sach',
        children: [
          {
            path: '/banner/danh-sach',
            name: 'Danh sách banner',
            component: () => import('@/views/banner/DanhSachBanner.vue'),
          },
          {
            path: '/banner/them-moi',
            name: 'Thêm mới banner',
            component: () => import('@/views/banner/ThemMoiBanner.vue'),
          },
        ],
      },
      {
        path: '/gioi-thieu',
        name: 'Giới thiệu',
        component: {
          render() {
            return h(resolveComponent('router-view'))
          },
        },
        redirect: '/gioi-thieu',
        children: [
          {
            path: '/gioi-thieu',
            name: 'Danh sách giới thiệu',
            component: () => import('@/views/gioithieu/DanhSachGioiThieu.vue'),
          },
          {
            // path: '/gioi-thieu/chinh-sua/:id',
            path: '/gioi-thieu/chinh-sua',
            name: 'Chỉnh sửa giới thiệu',
            component: () => import('@/views/gioithieu/ChinhSuaGioiThieu.vue'),
          },
        ],
      },
      {
        path: '/tin-tuc',
        name: 'Tin tức',
        component: {
          render() {
            return h(resolveComponent('router-view'))
          },
        },
        redirect: '/tin-tuc/danh-sach',
        children: [
          {
            path: '/tin-tuc/danh-sach',
            name: 'Danh sách tin tức',
            component: () => import('@/views/tintuc/DanhSachTinTuc.vue'),
          },
          {
            path: '/tin-tuc/them-moi',
            name: 'Thêm mới tin tức',
            component: () => import('@/views/tintuc/ThemMoiTinTuc.vue'),
          },
          {
            path: '/tin-tuc/chinh-sua',
            name: 'Chỉnh sửa tin tức',
            component: () => import('@/views/tintuc/ChinhSuaTinTuc.vue'),
          },
        ],
      },
      {
        path: '/tin-nhan',
        name: 'Tin nhắn',
        component: () => import('@/views/tinnhan/DanhSachTinNhan.vue'),
      },
      {
        path: '/dong-vat',
        name: 'Động vật',
        component: {
          render() {
            return h(resolveComponent('router-view'))
          },
        },
        redirect: '/dong-vat/nganh',
        children: [
          {
            path: '/dong-vat/nganh',
            name: 'Ngành động vật',
            component: () => import('@/views/dongthucvat/nganh/DanhSachNganh.vue'),
          },
          {
            path: '/dong-vat/lop',
            name: 'Lớp động vật',
            component: () => import('@/views/dongthucvat/lop/DanhSachLop.vue'),
          },
          {
            path: '/dong-vat/bo',
            name: 'Bộ động vật',
            component: () => import('@/views/dongthucvat/bo/DanhSachBo.vue'),
          },
          {
            path: '/dong-vat/ho',
            name: 'Họ động vật',
            component: () => import('@/views/dongthucvat/ho/DanhSachHo.vue'),
          },
          {
            path: '/dong-vat/loai',
            name: 'Loài động vật',
            component: {
              render() {
                return h(resolveComponent('router-view'))
              },
            },
            redirect: '/dong-vat/loai/danh-sach',
            children: [ // Thêm các route con vào đây
              {
                path: 'danh-sach',
                name: 'Danh sách loài động vật',
                component: () => import('@/views/dongthucvat/loai/DanhSachLoai.vue'),
              },
              {
                path: 'them-moi',
                name: 'Thêm mới loài động vật',
                component: () => import('@/views/dongthucvat/loai/ThemMoiLoai.vue'),
              },
              {
                // path: 'chinh-sua/:id', // :id là tham số động để nhận ID loài
                path: 'chinh-sua',
                name: 'Chỉnh sửa loài động vật',
                component: () => import('@/views/dongthucvat/loai/ChinhSuaLoai.vue'),
              },
            ]
          },
        ],
      },
      {
        path: '/thuc-vat',
        name: 'Thực vật',
        component: {
          render() {
            return h(resolveComponent('router-view'))
          },
        },
        redirect: '/thuc-vat/nganh',
        children: [
          {
            path: '/thuc-vat/nganh',
            name: 'Ngành thực vật',
            component: () => import('@/views/dongthucvat/nganh/DanhSachNganh.vue'),
          },
          {
            path: '/thuc-vat/lop',
            name: 'Lớp thực vật',
            component: () => import('@/views/dongthucvat/lop/DanhSachLop.vue'),
          },
          {
            path: '/thuc-vat/bo',
            name: 'Bộ thực vật',
            component: () => import('@/views/dongthucvat/bo/DanhSachBo.vue'),
          },
          {
            path: '/thuc-vat/ho',
            name: 'Họ thực vật',
            component: () => import('@/views/dongthucvat/ho/DanhSachHo.vue'),
          },
          {
            path: '/thuc-vat/loai',
            name: 'Loài thực vật',
            component: {
              render() {
                return h(resolveComponent('router-view'))
              },
            },
            redirect: '/thuc-vat/loai/danh-sach',
            children: [ // Thêm các route con vào đây
              {
                path: 'danh-sach',
                name: 'Danh sách loài thực vật',
                component: () => import('@/views/dongthucvat/loai/DanhSachLoai.vue'),
              },
              {
                path: 'them-moi',
                name: 'Thêm mới loài thực vật',
                component: () => import('@/views/dongthucvat/loai/ThemMoiLoai.vue'),
              },
              {
                // path: 'chinh-sua/:id', // :id là tham số động để nhận ID loài
                path: 'chinh-sua',
                name: 'Chỉnh sửa loài thực vật',
                component: () => import('@/views/dongthucvat/loai/ChinhSuaLoai.vue'),
              },
            ]
          },
        ],
      },
      {
        path: '/thong-ke',
        name: 'Thống kê',
        component: () => import('@/views/thongke/ThongKe.vue'),
      },
    ],
  },
]

const router = createRouter({
  history: createWebHashHistory(import.meta.env.BASE_URL),
  routes,
  scrollBehavior() {
    // always scroll to top
    return { top: 0 }
  },
})

// Navigation Guard: Bảo vệ các route cần đăng nhập
// router.beforeEach((to, from, next) => {
//   const isAuthenticated = localStorage.getItem('token')  // Kiểm tra token đã lưu
//
//   if (to.matched.some(record => record.name !== 'Login')) {  // Kiểm tra các trang khác ngoài Login
//     if (!isAuthenticated) {
//       next({ name: 'Login' })  // Chuyển hướng đến trang Login nếu chưa đăng nhập
//     } else {
//       next()  // Tiếp tục đến trang yêu cầu nếu đã đăng nhập
//     }
//   } else {
//     next()  // Không yêu cầu đăng nhập, tiếp tục
//   }
// })


export default router

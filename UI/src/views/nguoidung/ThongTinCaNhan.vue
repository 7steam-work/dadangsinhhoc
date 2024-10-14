<script setup>
import { CIcon } from '@coreui/icons-vue'
import { cilPencil } from '@coreui/icons'

</script>

<template>
    <CCol :xs="12">
        <CCard class="mb-4">
            <CCardBody>
                <CTable striped>
                    <CTableBody>
                        <CTableRow>
                            <CTableHeaderCell scope="row">Họ tên:</CTableHeaderCell>
                            <CTableDataCell>Mark</CTableDataCell>
                        </CTableRow>
                        <CTableRow>
                            <CTableHeaderCell scope="row">Email:</CTableHeaderCell>
                            <CTableDataCell>Jacob@gmail.com</CTableDataCell>
                        </CTableRow>
                        <CTableRow>
                            <CTableHeaderCell scope="row">Số điện thoại:</CTableHeaderCell>
                            <CTableDataCell>099.999.9999</CTableDataCell>
                        </CTableRow>
                        <CTableRow>
                            <CTableHeaderCell scope="row">Giới tính:</CTableHeaderCell>
                            <CTableDataCell>Nam</CTableDataCell>
                        </CTableRow>
                        <CTableRow>
                            <CTableHeaderCell scope="row">Ngày sinh:</CTableHeaderCell>
                            <CTableDataCell>2002/03/20</CTableDataCell>
                        </CTableRow>
                        <CTableRow>
                            <CTableHeaderCell scope="row">Địa chỉ:</CTableHeaderCell>
                            <CTableDataCell>Hà Nội</CTableDataCell>
                        </CTableRow>
                        <CTableRow>
                            <CTableHeaderCell scope="row">Ngày đăng ký:</CTableHeaderCell>
                            <CTableDataCell>2024/08/13</CTableDataCell>
                        </CTableRow>
                        <CTableRow>
                            <CTableHeaderCell scope="row">Ngày cập nhật:</CTableHeaderCell>
                            <CTableDataCell>2024/09/02</CTableDataCell>
                        </CTableRow>
                        <CTableRow>
                            <CTableHeaderCell scope="row">Lần cuối đăng nhập:</CTableHeaderCell>
                            <CTableDataCell>2024/09/03</CTableDataCell>
                        </CTableRow>
                        <CTableRow>
                            <CTableHeaderCell scope="row">Trạng thái:</CTableHeaderCell>
                            <CTableDataCell>Bật</CTableDataCell>
                        </CTableRow>
                        <CTableRow>
                            <CTableHeaderCell scope="row">Nhóm người dùng:</CTableHeaderCell>
                            <CTableDataCell>Admin</CTableDataCell>
                        </CTableRow>
                    </CTableBody>
                </CTable>
                <div class="d-flex justify-content-left mb-3">
                    <CButton variant="outline" shape="rounded-0" color="success" class="me-2"  @click="chuyenDenTrangChinhSua">
                        <CIcon class="me-2" :icon="cilPencil" />Chỉnh sửa
                    </CButton>
                    <CButton variant="outline" shape="rounded-0" color="info" @click="chuyenDenTrangDoiMatKhau">Đổi mật
                        khẩu</CButton>
                </div>
            </CCardBody>
        </CCard>
    </CCol>
</template>

<script>
export default {
    data() {
        return {
            users: [] // Dữ liệu người dùng sẽ được tải từ API
        };
    },
    mounted() {
        // Gọi API để lấy danh sách người dùng và gán vào biến users
        this.fetchUsers();
    },
    methods: {
        async fetchUsers() {
            try {
                const response = await fetch('/api/users'); // Thay '/api/users' bằng endpoint API thực tế của bạn
                const data = await response.json();
                this.users = data;
            } catch (error) {
                console.error('Lỗi khi tải danh sách người dùng:', error);
                // Xử lý lỗi (ví dụ: hiển thị thông báo lỗi)
            }
        },
        chuyenDenTrangChinhSua() {
            this.$router.push({ name: 'Cập nhật thông tin cá nhân' });
        },
        chuyenDenTrangDoiMatKhau() {
            this.$router.push({ name: 'Đổi mật khẩu' });
        }
    }
};
</script>

<template>
    <div class="d-flex justify-content-center">
        <div class="col-md-8">
            <CCard>
                <CCardBody>
                    <CForm @submit.prevent="themNguoiDung">
                        <!-- Họ tên -->
                        <div class="mb-3">
                            <CFormLabel for="hoTen">Họ tên:</CFormLabel>
                            <CFormInput id="hoTen" v-model="nguoiDung.hoTen" required />
                        </div>

                        <!-- Email -->
                        <div class="mb-3">
                            <CFormLabel for="email">Email:</CFormLabel>
                            <CFormInput id="email" v-model="nguoiDung.email" type="email" required />
                        </div>

                        <!-- Số điện thoại -->
                        <div class="mb-3">
                            <CFormLabel for="sdt">Số điện thoại:</CFormLabel>
                            <CFormInput id="sdt" v-model="nguoiDung.sdt" required />
                        </div>

                        <!-- Mật khẩu cũ-->
                        <div class="mb-3">
                            <CFormLabel for="matKhauCu">Mật khẩu cũ:</CFormLabel>
                            <CInputGroup>
                                <CFormInput id="matKhau" v-model="nguoiDung.matKhauCu"
                                    :type="hienThiMatKhau ? 'text' : 'password'" readonly value="cuutui" />
                                <CButton type="button" color="secondary" variant="outline" @click="hienThiMatKhau = !hienThiMatKhau">
                                    {{ hienThiMatKhau ? '***' : 'abc' }}</CButton>
                            </CInputGroup>
                        </div>

                        <!-- Mật khẩu -->
                        <div class="mb-3">
                            <CFormLabel for="matKhau">Mật khẩu mới:</CFormLabel>
                            <CFormInput id="matKhau" v-model="nguoiDung.matKhau" type="password" required />
                        </div>

                        <!-- Nhập lại mật khẩu -->
                        <div class="mb-3">
                            <CFormLabel for="nhapLaiMatKhau">Nhập lại mật khẩu:</CFormLabel>
                            <CFormInput id="nhapLaiMatKhau" v-model="nhapLaiMatKhau" type="password" required />
                        </div>

                        <!-- Giới tính -->
                        <div class="mb-3">
                            <CFormLabel for="gioiTinh">Giới tính:</CFormLabel>
                            <CFormSelect id="gioiTinh" v-model="nguoiDung.gioiTinh">
                                <option value="Nam">Nam</option>
                                <option value="Nữ">Nữ</option>
                                <option value="Khác">Khác</option>
                            </CFormSelect>
                        </div>

                        <!-- Ngày sinh -->
                        <div class="mb-3">
                            <CFormLabel for="ngaySinh">Ngày sinh:</CFormLabel>
                            <input type="date" id="ngaySinh" v-model="nguoiDung.ngaySinh" class="form-control" required>
                        </div>

                        <!-- Địa chỉ -->
                        <div class="mb-3">
                            <CFormLabel for="diaChi">Địa chỉ:</CFormLabel>
                            <CFormInput id="diaChi" v-model="nguoiDung.diaChi" />
                        </div>


                        <!-- Trạng thái -->
                        <div class="mb-3">
                            <CFormLabel>Trạng thái:</CFormLabel>
                            <div>
                                <CFormCheck inline :checked="nguoiDung.trangThai === 'Bật'" label="Bật" type="radio"
                                    value="Bật" v-model="nguoiDung.trangThai" />
                                <CFormCheck inline :checked="nguoiDung.trangThai === 'Tắt'" label="Tắt" type="radio"
                                    value="Tắt" v-model="nguoiDung.trangThai" />
                            </div>
                        </div>

                        <!-- Nhóm người dùng -->
                        <div class="mb-3">
                            <CFormLabel for="nhomNguoiDung">Nhóm người dùng:</CFormLabel>
                            <CFormSelect id="nhomNguoiDung" v-model="nguoiDung.nhomNguoiDung">
                                <option value="Admin">Admin</option>
                                <option value="Ban Quản lý RPH Nam Giang">Ban Quản lý RPH Nam Giang</option>
                                <option value="Ban Quản lý RPH Mường Tè">Ban Quản lý RPH Mường Tè</option>
                            </CFormSelect>
                        </div>

                        <!-- Buttons -->
                        <div class="d-flex justify-content-center">
                            <CButton class="me-2" color="primary" type="submit">Xác nhận</CButton>
                            <CButton color="secondary" type="reset">Hủy</CButton>
                        </div>
                    </CForm>
                </CCardBody>
            </CCard>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            nguoiDung: {
                hoTen: '',
                email: '',
                sdt: '',
                matKhau: '',
                ngaySinh: null, // Hoặc new Date() nếu muốn ngày hiện tại là mặc định
                diaChi: '',
                gioiTinh: 'Nam',
                trangThai: 'Bật',
                nhomNguoiDung: 'Admin'
            },
            nhapLaiMatKhau: '',
            hienThiMatKhau: false,
        };
    },
    methods: {
        themNguoiDung() {
            // Kiểm tra mật khẩu khớp
            if (this.nguoiDung.matKhau !== this.nhapLaiMatKhau) {
                alert('Mật khẩu không khớp!');
                return;
            }

            // Gửi dữ liệu người dùng lên API để chỉnh sửa
            console.log('Người dùng chỉnh sửa:', this.nguoiDung);
            // API call sẽ được thực hiện ở đây
        }
    }
};
</script>
<template>
    <div class="d-flex justify-content-center">
      <div class="col-md-8">
        <CCard>
          <CCardBody>
            <CForm @submit.prevent="capNhatThongTinCaNhan">
              <!-- Họ tên -->
              <div class="mb-3">
                <CFormLabel for="hoTen">Họ tên:</CFormLabel>
                <CFormInput id="hoTen" v-model="nguoiDung.hoTen" required />
              </div>
  
              <!-- Email -->
              <div class="mb-3">
                <CFormLabel for="email" >Email:</CFormLabel>
                <CFormInput id="email" v-model="nguoiDung.email" type="email" required />
              </div>
  
              <!-- Số điện thoại -->
              <div class="mb-3">
                <CFormLabel for="sdt">Số điện thoại:</CFormLabel>
                <CFormInput id="sdt" v-model="nguoiDung.sdt" required />
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
  
              <!-- Buttons -->
              <div class="d-flex justify-content-center">
                <CButton class="me-2" color="primary" type="submit">Cập nhật</CButton>
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
          ngaySinh: null, // Hoặc new Date() nếu muốn ngày hiện tại là mặc định
          diaChi: '',
          gioiTinh: 'Nam',
        },
      };
    },
    methods: {
      capNhatThongTinCaNhan() {
        // Gửi dữ liệu người dùng lên API để chỉnh sửa
        console.log('Thông tin chỉnh sửa:', this.nguoiDung);
        // API call sẽ được thực hiện ở đây
      }
    }
  };
  </script>
  
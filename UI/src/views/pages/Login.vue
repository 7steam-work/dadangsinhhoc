<template>
  <div class="wrapper min-vh-100 d-flex flex-row align-items-center">
    <CContainer>
      <CRow class="justify-content-center">
        <CCol :md="8">
          <CCardGroup>
            <CCard class="p-4">
              <CCardBody>
                <CForm @submit.prevent="login">
                  <h1>Đăng nhập</h1>
                  <p class="text-body-secondary">Truy cập vào trang quản lý</p>
                  <CInputGroup class="mb-3">
                    <CInputGroupText>
                      <CIcon icon="cil-user" />
                    </CInputGroupText>
                    <CFormInput v-model="email" placeholder="Email" autocomplete="email" />
                  </CInputGroup>
                  <CInputGroup class="mb-4">
                    <CInputGroupText>
                      <CIcon icon="cil-lock-locked" />
                    </CInputGroupText>
                    <CFormInput v-model="password" type="password" placeholder="Password"
                      autocomplete="current-password" />
                  </CInputGroup>
                  <CRow>
                    <CCol :xs="6">
                      <CButton type="submit" color="primary" class="px-4"> Login </CButton>
                    </CCol>
                  </CRow>
                </CForm>
              </CCardBody>
            </CCard>
          </CCardGroup>
        </CCol>
      </CRow>
    </CContainer>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      email: '',
      password: '',
    }
  },
  methods: {
    async login() {
      try {
        const response = await axios.post('http://localhost:8080/api/users/login', {
          email: this.email,
          password: this.password
        });
        if (response.data.code === 200) {
          // Lưu token vào localStorage
          console.log('response: ', response.data);
          localStorage.setItem('token', response.data.data.token);
          console.log('token: ', response.data.data.token);
          // Chuyển hướng đến trang chính
          console.error('Done');

          this.$router.push({ name: 'Dashboard' }).then(() => {
            console.log("Successfully redirected to Dashboard");
          }).catch((err) => {
            console.error("Failed to redirect:", err);
          });
        } else {
          alert(response.data.message);
        }
      } catch (error) {
        console.error('Lỗi đăng nhập:', error);
        alert('Đã xảy ra lỗi trong quá trình đăng nhập');
      }
    }
  }
}
</script>

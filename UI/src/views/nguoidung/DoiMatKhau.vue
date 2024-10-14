<script setup>
import {
  CForm, CFormInput, CButton, CCard, CCardBody, CFormFeedback
} from '@coreui/vue';
import { ref } from 'vue';
import axios from 'axios';

const currentPassword = ref('');
const newPassword = ref('');
const confirmPassword = ref('');

const error = ref(null);
const success = ref(null);

const currentPasswordTouched = ref(false);
const newPasswordTouched = ref(false);
const confirmPasswordTouched = ref(false);

const validateMatKhau = (matKhau) => {
  const regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\W_]).{8,}$/;
  return regex.test(matKhau);
};

const doiMatKhau = async () => {
  error.value = null;
  success.value = null;

  // Kiểm tra mật khẩu hiện tại
  currentPasswordTouched.value = true;
  if (!currentPassword.value) {
    return;
  }

  // Kiểm tra mật khẩu mới
  newPasswordTouched.value = true;
  if (!validateMatKhau(newPassword.value)) {
    return;
  }

  // Kiểm tra xác nhận mật khẩu mới
  confirmPasswordTouched.value = true;
  if (newPassword.value !== confirmPassword.value) {
    return;
  }

  try {
    // Gửi yêu cầu đổi mật khẩu đến API backend
    const response = await axios.post('http://localhost:8080/api/users/change-password', {
      currentPassword: currentPassword.value,
      newPassword: newPassword.value,
    });

    if (response.data.status === 'OK') {
      success.value = 'Đổi mật khẩu thành công!';
      resetForm();
    } else {
      error.value = response.data.message || 'Đã có lỗi xảy ra. Vui lòng thử lại sau.';
    }
  } catch (err) {
    console.error('Lỗi khi đổi mật khẩu:', err);
    error.value = 'Đã có lỗi xảy ra. Vui lòng thử lại sau.';
  }
};

const resetForm = () => {
  currentPassword.value = '';
  newPassword.value = '';
  confirmPassword.value = '';

  // Reset touch states
  currentPasswordTouched.value = false;
  newPasswordTouched.value = false;
  confirmPasswordTouched.value = false;
};
</script>

<template>
  <div class="row px-2">
    <div class="col-md-6">
      <CCard class="shadow">
        <CCardBody>
          <div class="row">
            <CForm class="col-lg-9 col-md-8 col-12" @submit.prevent="doiMatKhau" novalidate>
              <div v-if="error" class="alert alert-danger">{{ error }}</div>
              <div v-if="success" class="alert alert-success">{{ success }}</div>

              <div class="form-group mb-3">
                <label class="control-label" for="currentPassword">Nhập mật khẩu hiện tại:</label>
                <CFormInput 
                  type="password" 
                  id="currentPassword" 
                  v-model="currentPassword" 
                  required 
                  @blur="currentPasswordTouched = true"
                  :invalid="currentPasswordTouched && !currentPassword"
                />
                <CFormFeedback v-if="currentPasswordTouched && !currentPassword" invalid>
                  Vui lòng nhập mật khẩu hiện tại
                </CFormFeedback>
              </div>

              <div class="form-group mb-3">
                <label class="control-label" for="newPassword">Nhập mật khẩu mới:</label>
                <CFormInput 
                  type="password" 
                  id="newPassword" 
                  v-model="newPassword" 
                  required 
                  @blur="newPasswordTouched = true"
                  :invalid="newPasswordTouched && !validateMatKhau(newPassword)"
                />
                <CFormFeedback v-if="newPasswordTouched && !validateMatKhau(newPassword)" invalid>
                  Mật khẩu phải có ít nhất 8 ký tự, bao gồm chữ hoa, chữ thường, số và ký tự đặc biệt.
                </CFormFeedback>
              </div>

              <div class="form-group mb-3">
                <label class="control-label" for="confirmPassword">Nhập lại mật khẩu mới:</label>
                <CFormInput 
                  type="password" 
                  id="confirmPassword" 
                  v-model="confirmPassword" 
                  required 
                  @blur="confirmPasswordTouched = true"
                  :invalid="confirmPasswordTouched && newPassword !== confirmPassword"
                />
                <CFormFeedback v-if="confirmPasswordTouched && newPassword !== confirmPassword" invalid>
                  Mật khẩu không khớp!
                </CFormFeedback>
              </div>

              <div class="form-group m-2 text-center">
                <CButton color="primary" type="submit">Đổi mật khẩu
                </CButton>
                <CButton color="secondary" type="reset" class="ms-2" @click="resetForm">Reset
                </CButton>
              </div>
            </CForm>
          </div>
        </CCardBody>
      </CCard>
    </div>

    <div class="col-md-6">
      <CCard class="shadow">
        <CCardBody>
          <i>
            Mật khẩu là một chuỗi các ký tự quy định như sau:
            <br>
            - Không chứa dấu cách trống.
            <br>
            - Có độ dài ít nhất là 8 ký tự.
            <br>
            - Có ít nhất một ký tự chữ cái viết thường (a-z).
            <br>
            - Có ít nhất một ký tự chữ cái viết hoa (A-Z).
            <br>
            - Có ít nhất một ký tự đặc biệt (@#$%^&+=).
            <br>
            - Có ít nhất một ký tự số (0-9).
          </i>
        </CCardBody>
      </CCard>
    </div>
  </div>
</template>

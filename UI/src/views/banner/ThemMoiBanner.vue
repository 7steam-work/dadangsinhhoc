<template>
  <div class="d-flex justify-content-center">
    <div class="col-md-8">
      <CCard>
        <CCardBody>
          <CForm @submit.prevent="themBanner">
            <!-- Ảnh -->
            <div class="mb-3">
              <CFormLabel for="image">Ảnh:</CFormLabel>
              <CFormInput id="image" v-model="banner.image" type="file" required />
            </div>

            <!-- Trạng thái -->
            <div class="mb-3">
              <CFormLabel>Trạng thái:</CFormLabel>
              <div>
                <CFormCheck inline :checked="banner.trangThai === 'Bật'" label="Bật" type="radio" value="Bật"
                            v-model="banner.trangThai" />
                <CFormCheck inline :checked="banner.trangThai === 'Tắt'" label="Tắt" type="radio" value="Tắt"
                            v-model="banner.trangThai" />
              </div>
            </div>

            <!-- Buttons -->
            <div class="d-flex justify-content-center">
              <CButton class="me-2" color="primary" type="submit">Thêm banner</CButton>
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
      banner: {
        image: '',
        text:'',
        trangThai: 'Bật',
      },
    };
  },
  methods: {
    themBanner() {

      // Gửi dữ liệu người dùng lên API để thêm mới
      console.log('Người dùng mới:', this.banner);
      // API call sẽ được thực hiện ở đây
    }
  }
};
</script>

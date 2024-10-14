<script setup>
import { CIcon } from '@coreui/icons-vue'
import { cilPencil } from '@coreui/icons'

</script>

<template>
  <CCol :xs="12">
    <CCard class="mb-4">
      <CCardBody>
        <CTable hover bordered striped>
          <CTableHead>
            <CTableRow>
              <CTableHeaderCell scope="col">STT</CTableHeaderCell>
              <CTableHeaderCell scope="col">Tiêu đề</CTableHeaderCell>
              <CTableHeaderCell scope="col">Người cập nhật</CTableHeaderCell>
              <CTableHeaderCell scope="col">Ngày cập nhật</CTableHeaderCell>
              <CTableHeaderCell scope="col"></CTableHeaderCell>
            </CTableRow>
          </CTableHead>
          <CTableBody>
            <CTableRow>
              <CTableHeaderCell scope="row">1</CTableHeaderCell>
              <CTableDataCell class="col-7">
                <p>Giới thiệu về Phần mềm cơ sở dữ liệu về các hệ sinh thái có đa dạng sinh học cao trong phạm vi rừng phòng hộ</p>
              </CTableDataCell>
              <CTableDataCell>administrators</CTableDataCell>
              <CTableDataCell>1/26/2024 2:16:04 AM</CTableDataCell>
              <CTableDataCell>
                <a class="me-2" href="/#/gioi-thieu/chinh-sua">
                  <CIcon class="text-info" :icon="cilPencil" />
                </a>
              </CTableDataCell>
            </CTableRow>
          </CTableBody>
        </CTable>
      </CCardBody>
    </CCard>
  </CCol>
</template>

<script>

export default {
  data() {
    return {
      banner: [] // Dữ liệu banner sẽ được tải từ API
    };
  },
  mounted() {
    // Gọi API để lấy danh sách banner và gán vào biến banner
    this.fetchBanner();
  },
  methods: {
    async fetchBanner() {
      try {
        const response = await fetch('/api/banner'); // Thay '/api/banner' bằng endpoint API thực tế của bạn
        const data = await response.json();
        this.banner = data;
      } catch (error) {
        console.error('Lỗi khi tải danh sách banner:', error);
        // Xử lý lỗi (ví dụ: hiển thị thông báo lỗi)
      }
    }
  }
};
</script>

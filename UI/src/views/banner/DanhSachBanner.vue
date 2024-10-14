<script setup>
import { ref } from 'vue'
import { CIcon } from '@coreui/icons-vue'
import { cilSearch } from '@coreui/icons'  // Import icon cil-search
import { cilPlus } from '@coreui/icons'  // Import icon cil-Plus
import { cilTrash } from '@coreui/icons'

// trạng thái để điều khiển modal
const isDeleteModalVisible = ref(false);
const selectedBannerId = ref(null);

const openDeleteModal = (bannerId) => {
  selectedBannerId.value = bannerId;
  isDeleteModalVisible.value = true;
}

const closeDeleteModal = () => {
  isDeleteModalVisible.value = false;
  selectedBannerId.value = null;
}

const deleteBanner = () => {
  banners.value = banners.value.filter(banner => banner.id !== selectedBannerId.value);
  console.log("Xóa với ID:", selectedBannerId.value);
  closeDeleteModal();
}

// dữ liệu giả lập
const banners = ref([
  
]);

// Hàm tạo đường dẫn hình ảnh
const imagePath = (relativePath) => {
  return `/images/${relativePath}`;
};

// Fetch danh sách khi component được mount
const fetchbanners = async () => {
  try {
    const response = await fetch('/api/ho');
    const data = await response.json();
    banners.value = data;
  } catch (error) {
    console.error('Lỗi khi tải danh sách:', error);
  }
}

fetchbanners();
</script>

<template>
  <CCol :xs="12">
    <CCard class="mb-4">
      <CCardHeader class="d-flex justify-content-between align-items-center">
        <router-link :to="{ name: 'Thêm mới banner' }">
          <CButton variant="outline" shape="rounded-0" color="success">
            <CIcon class="me-2" :icon="cilPlus" />Thêm mới
          </CButton>
        </router-link>
        <CInputGroup class="w-50">
          <CFormInput aria-label="Tìm kiếm" aria-describedby="btnGroupAddon" />
          <CInputGroupText id="basic-addon2">
            <CIcon :icon="cilSearch" />
          </CInputGroupText>
          <CButton type="button" color="secondary" variant="outline" id="button-addon2"
            aria-describedby="btnGroupAddon">Tìm kiếm</CButton>
        </CInputGroup>
      </CCardHeader>
      <CCardBody>
        <CTable hover bordered striped>
          <CTableHead>
            <CTableRow>
              <CTableHeaderCell scope="col">STT</CTableHeaderCell>
              <CTableHeaderCell scope="col">Ảnh</CTableHeaderCell>
              <CTableHeaderCell scope="col">Ngày tạo</CTableHeaderCell>
              <CTableHeaderCell scope="col"></CTableHeaderCell>
            </CTableRow>
          </CTableHead>
          <CTableBody>
            <CTableRow v-for="(banner, index) in banners" :key="banner.id">
              <CTableHeaderCell scope="row">{{ index + 1 }}</CTableHeaderCell>
              <CTableDataCell>
                <img v-if="banner.imagePath" :src="imagePath(banner.imagePath)" alt=" " class="img-thumbnail"
                  style="height: 40px; width: 60px;">
              </CTableDataCell>
              <CTableDataCell>{{ ho.createdAt }}</CTableDataCell>
              <CTableDataCell>
                <a href="javascript:void(0)" @click="openDeleteModal(banner.id)">
                  <CIcon class="text-danger" :icon="cilTrash" />
                </a>
              </CTableDataCell>
            </CTableRow>
          </CTableBody>
        </CTable>
      </CCardBody>
    </CCard>
  </CCol>

  <!-- Modal Xóa -->
  <CModal :visible="isDeleteModalVisible" @close="closeDeleteModal">
    <CModalHeader>
      <CModalTitle>Xác nhận xóa</CModalTitle>
    </CModalHeader>
    <CModalBody>Bạn có chắc chắn muốn xóa không?</CModalBody>
    <CModalFooter>
      <CButton color="secondary" @click="closeDeleteModal">Hủy</CButton>
      <CButton color="danger" @click="deleteBanner">Xóa</CButton>
    </CModalFooter>
  </CModal>
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
    },
    imagePath(relativePath) {
      // Giả sử bạn lưu trữ hình ảnh trong thư mục 'public/images'
      return `/images/${relativePath}`;
    },
  }
};
</script>

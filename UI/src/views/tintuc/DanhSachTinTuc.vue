<script setup>
import { ref } from 'vue'
import { CIcon } from '@coreui/icons-vue'
import { cilSearch, cilPlus, cilTrash, cilPencil } from '@coreui/icons'
import { CModal, CModalHeader, CModalTitle, CModalBody, CModalFooter, CButton } from '@coreui/vue'

// Các trạng thái để điều khiển modal
const isModalVisible = ref(false);
const selectedNewsId = ref(null); // Lưu trữ ID được chọn để xóa

const openDeleteModal = (newsId) => {
    selectedNewsId.value = newsId;
    isModalVisible.value = true;
}

const closeModal = () => {
    isModalVisible.value = false;
    selectedNewsId.value = null;
}

// Dữ liệu giả lập
const newss = ref([
    
]);

const deleteNews = () => {
    // Gọi API để xóa với selectedNewsId.value
    newss.value = newss.value.filter(news => news.id !== selectedNewsId.value);
    console.log("Xóa với ID:", selectedNewsId.value);
    // Sau khi xóa thành công, ẩn modal
    closeModal();
}


// Fetch danh sách khi component được mount
//const newss = ref([]);

const fetchNewss = async () => {
    try {
        const response = await fetch('/api/news'); // Thay '/api/newss' bằng endpoint API thực tế của bạn
        const data = await response.json();
        newss.value = data;
    } catch (error) {
        console.error('Lỗi khi tải danh sách:', error);
    }
}

fetchNewss();
</script>

<template>
    <CCol :xs="12">
        <CCard class="mb-4">
            <CCardHeader class="d-flex justify-content-between align-items-center">
                <router-link :to="{ name: 'Thêm mới tin tức' }">
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
                            <CTableHeaderCell scope="col">Tiêu đề</CTableHeaderCell>
                            <CTableHeaderCell scope="col">Hình ảnh</CTableHeaderCell>
                            <CTableHeaderCell scope="col">Đơn vị</CTableHeaderCell>
                            <CTableHeaderCell scope="col">Người tạo</CTableHeaderCell>
                            <CTableHeaderCell scope="col">Ngày tạo</CTableHeaderCell>
                            <CTableHeaderCell scope="col"></CTableHeaderCell>
                        </CTableRow>
                    </CTableHead>
                    <CTableBody>
                        <CTableRow v-for="news in newss" :key="news.id">
                            <CTableHeaderCell scope="row">{{ index + 1 }}</CTableHeaderCell>
                            <CTableDataCell>{{ news.title }}</CTableDataCell>
                            <CTableDataCell>
                                <img v-if="news.imagePath" :src="imagePath(news.imagePath)" alt=""
                                    class="img-thumbnail" style="height: 40px; width: 60px;">
                            </CTableDataCell>
                            <CTableDataCell>{{ news.rPH }}</CTableDataCell>
                            <CTableDataCell>{{ news.author }}</CTableDataCell>
                            <CTableDataCell>{{ news.createdAt }}</CTableDataCell>
                            <CTableDataCell>
                                <!-- <router-link :to="{ name: 'Chỉnh sửa tin tức', params: { id: news.id } }" class="me-2"> -->
                                <router-link :to="{ name: 'Chỉnh sửa tin tức' }" class="me-2">
                                    <CIcon class="text-info" :icon="cilPencil" />
                                </router-link>
                                <a href="javascript:void(0)" @click="openDeleteModal(news.id)">
                                    <CIcon class="text-danger" :icon="cilTrash" />
                                </a>
                            </CTableDataCell>
                        </CTableRow>
                    </CTableBody>
                </CTable>
            </CCardBody>
        </CCard>
    </CCol>

    <!-- Modal xác nhận xóa -->
    <CModal :visible="isModalVisible" @close="closeModal">
        <CModalHeader>
            <CModalTitle>Xác nhận xóa</CModalTitle>
        </CModalHeader>
        <CModalBody>Bạn có chắc chắn muốn xóa không?</CModalBody>
        <CModalFooter>
            <CButton color="secondary" @click="closeModal">Hủy</CButton>
            <CButton color="danger" @click="deleteNews">Xóa</CButton>
        </CModalFooter>
    </CModal>
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
                const response = await axios.post('http://localhost:8080/api/newss/login', {
                    email: this.email,
                    password: this.password
                });
                if (response.data.code === 200) {
                    // Lưu token vào localStorage
                    localStorage.setItem('token', response.data.data);
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
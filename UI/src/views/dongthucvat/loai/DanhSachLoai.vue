<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import axios from 'axios'
import { CIcon } from '@coreui/icons-vue'
import { cilSearch, cilPlus, cilTrash, cilPencil } from '@coreui/icons'
import {
    CCol,
    CCard,
    CCardHeader,
    CButton,
    CInputGroup,
    CFormInput,
    CInputGroupText,
    CCardBody,
    CTable,
    CTableHead,
    CTableRow,
    CTableHeaderCell,
    CTableBody,
    CTableDataCell,
    CModal,
    CModalHeader,
    CModalTitle,
    CModalBody,
    CModalFooter
} from '@coreui/vue';

// Các trạng thái để điều khiển modal
const isModalVisible = ref(false);
const selectedLoaiId = ref(null); // Lưu trữ ID được chọn để xóa
const loais = ref([]); // Lưu trữ danh sách các loài từ API
const loading = ref(false); // Trạng thái tải dữ liệu

const openDeleteModal = (loaiId) => {
    selectedLoaiId.value = loaiId;
    isModalVisible.value = true;
}

const closeModal = () => {
    isModalVisible.value = false;
    selectedLoaiId.value = null;
}

// Gọi API xóa loài
const deleteLoai = async () => {
    try {
        await axios.delete(`http://localhost:8080/api/loai/deleteLoai/${selectedLoaiId.value}`);
        loais.value = loais.value.filter(loai => loai.id !== selectedLoaiId.value);
        console.log("Xóa với ID:", selectedLoaiId.value);
        closeModal();
    } catch (error) {
        console.error('Lỗi khi xóa:', error);
    }
}

//Lấy route hiện tại để xác định 'dong-vat' hoặc 'thuc-vat'
const route = useRoute();
const isDongVat = ref(route.path.includes('/dong-vat')); // Kiểm tra route có chứa '/dong-vat' không
console.log('Is Động Vật:', isDongVat.value);

// Fetch danh sách khi component được mount
const fetchLoais = async () => {
    try {
        loading.value = true;
        const response = await axios.get(`http://localhost:8080/api/loai/getAllLoaiByLoai/${isDongVat.value}`);
        if (response.data && response.data.data) {
            loais.value = response.data.data;
        } else {
            console.error('Không có dữ liệu trả về từ API');
        }
    } catch (error) {
        console.error('Lỗi khi tải danh sách:', error);
    } finally {
        loading.value = false;
    }
}
onMounted(() => {
    fetchLoais();
});
</script>

<template>
    <CCol :xs="12">
        <CCard class="mb-4">
            <CCardHeader class="d-flex justify-content-between align-items-center">
                <router-link :to="{ name: 'Thêm mới loài thực vật' }">
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
                            <CTableHeaderCell scope="col">Tên tiếng Việt</CTableHeaderCell>
                            <CTableHeaderCell scope="col">Tên Latinh</CTableHeaderCell>
                            <CTableHeaderCell scope="col">Họ</CTableHeaderCell>
                            <CTableHeaderCell scope="col">Ngày cập nhật</CTableHeaderCell>
                            <CTableHeaderCell scope="col">Rừng phòng hộ</CTableHeaderCell>
                            <CTableHeaderCell scope="col"></CTableHeaderCell>
                        </CTableRow>
                    </CTableHead>
                    <CTableBody>
                        <CTableRow v-for="(loai, index) in loais" :key="loai.id">
                            <CTableHeaderCell scope="row">{{ index + 1 }}</CTableHeaderCell>
                            <CTableDataCell>{{ loai.name }}</CTableDataCell>
                            <CTableDataCell>{{ loai.nameLatinh }}</CTableDataCell>
                            <CTableDataCell>{{ loai.idHo }}</CTableDataCell>
                            <CTableDataCell>{{ loai.updatedAt }}</CTableDataCell>
                            <CTableDataCell>{{ loai.rPH }}</CTableDataCell>
                            <CTableDataCell @click.stop>
                                <!-- <router-link :to="{ name: 'Chỉnh sửa loài', params: { id: loai.id } }" class="me-2"> -->
                                <router-link :to="{ name: 'Chỉnh sửa loài động vật' }" class="me-2">
                                    <CIcon class="text-info" :icon="cilPencil" />
                                </router-link>
                                <a href="javascript:void(0)" @click="openDeleteModal(loai.id)">
                                    <CIcon class="text-danger" :icon="cilTrash" />
                                </a>
                            </CTableDataCell>
                        </CTableRow>
                    </CTableBody>
                </CTable>
            </CCardBody>
        </CCard>
    </CCol>

    <CModal :visible="isModalVisible" @close="closeModal">
        <CModalHeader>
            <CModalTitle>Xác nhận xóa</CModalTitle>
        </CModalHeader>
        <CModalBody>Bạn có chắc chắn muốn xóa không?</CModalBody>
        <CModalFooter>
            <CButton color="secondary" @click="closeModal">Hủy</CButton>
            <CButton color="danger" @click="deleteLoai">Xóa</CButton>
        </CModalFooter>
    </CModal>
</template>
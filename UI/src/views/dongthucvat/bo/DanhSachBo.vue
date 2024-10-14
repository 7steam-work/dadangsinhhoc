<script setup>
import { ref } from 'vue'
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
    CModalFooter,
    CForm,
    CFormLabel,
    CFormCheck
} from '@coreui/vue';

// Các trạng thái để điều khiển modal
const isDeleteModalVisible = ref(false);
const isEditModalVisible = ref(false);
const isAddModalVisible = ref(false);
const selectedBoId = ref(null);
const editingBo = ref({
    name: '',
    nameLatinh: '',
    lop: '',
    status: true
});
const newBo = ref({
    name: '',
    nameLatinh: '',
    lop: '',
    status: true
});

const openDeleteModal = (boId) => {
    selectedBoId.value = boId;
    isDeleteModalVisible.value = true;
}

const openEditModal = (bo) => {
    editingBo.value = { ...bo, status: true }; // Assuming status is not in the original data
    isEditModalVisible.value = true;
}

const openAddModal = () => {
    newBo.value = { name: '', nameLatinh: '', status: true };
    isAddModalVisible.value = true;
}

const closeDeleteModal = () => {
    isDeleteModalVisible.value = false;
    selectedBoId.value = null;
}

const closeEditModal = () => {
    isEditModalVisible.value = false;
    editingBo.value = { name: '', nameLatinh: '', status: true };
}

const closeAddModal = () => {
    isAddModalVisible.value = false;
    newBo.value = { name: '', nameLatinh: '', status: true };
}

// Dữ liệu giả lập
const bos = ref([
    {
        id: 1,
        name: 'Hổ Đông Dương',
        nameLatinh: 'Panthera tigris tigris',
        lop: 'Panthera tigris tigris',
        updatedAt: '2023-05-15',
        createdBy: 'createdBy Nam Giang'
    },
    {
        id: 2,
        name: 'Voọc mũi hếch',
        nameLatinh: 'Rhinopithecus avunculus',
        lop: 'Rhinopithecus avunculus',
        updatedAt: '2023-08-20',
        createdBy: 'createdBy Mường Tè'
    },
    {
        id: 3,
        name: 'Pơ mu',
        nameLatinh: 'Fokienia hodginsii',
        updatedAt: '2023-11-10',
        createdBy: 'createdBy Nam Giang'
    },
    {
        id: 4,
        name: 'Đỗ quyên',
        nameLatinh: 'Rhododendron spp.',
        updatedAt: '2023-06-03',
        createdBy: 'createdBy Mường Tè'
    }
]);

const deleteBo = () => {
    bos.value = bos.value.filter(bo => bo.id !== selectedBoId.value);
    console.log("Xóa với ID:", selectedBoId.value);
    closeDeleteModal();
}

const saveEditedBo = () => {
    const index = bos.value.findIndex(bo => bo.id === editingBo.value.id);
    if (index !== -1) {
        bos.value[index] = { ...bos.value[index], ...editingBo.value };
    }
    closeEditModal();
}

const addNewBo = () => {
    const newId = Math.max(...bos.value.map(n => n.id)) + 1;
    const currentDate = new Date().toISOString().split('T')[0];
    const newBoWithMetadata = {
        ...newBo.value,
        id: newId,
        updatedAt: currentDate,
        createdBy: 'Current User' // Replace with actual current user logic
    };
    bos.value.push(newBoWithMetadata);
    closeAddModal();
}

// Fetch danh sách khi component được mount
const fetchbos = async () => {
    try {
        const response = await fetch('/api/bo');
        const data = await response.json();
        bos.value = data;
    } catch (error) {
        console.error('Lỗi khi tải danh sách:', error);
    }
}

fetchbos();
</script>

<template>
    <CCol :xs="12">
        <CCard class="mb-4">
            <CCardHeader class="d-flex justify-content-between align-items-center">
                <CButton variant="outline" shape="rounded-0" color="success" @click="openAddModal">
                    <CIcon class="me-2" :icon="cilPlus" />Thêm mới
                </CButton>
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
                            <CTableHeaderCell scope="col">Lớp</CTableHeaderCell>
                            <CTableHeaderCell scope="col">Ngày cập nhật</CTableHeaderCell>
                            <CTableHeaderCell scope="col">Người tạo</CTableHeaderCell>
                            <CTableHeaderCell scope="col"></CTableHeaderCell>
                        </CTableRow>
                    </CTableHead>
                    <CTableBody>
                        <CTableRow v-for="(bo, index) in bos" :key="bo.id">
                            <CTableHeaderCell scope="row">{{ index + 1 }}</CTableHeaderCell>
                            <CTableDataCell>{{ bo.name }}</CTableDataCell>
                            <CTableDataCell>{{ bo.nameLatinh }}</CTableDataCell>
                            <CTableDataCell>{{ bo.lop }}</CTableDataCell>
                            <CTableDataCell>{{ bo.updatedAt }}</CTableDataCell>
                            <CTableDataCell>{{ bo.createdBy }}</CTableDataCell>
                            <CTableDataCell>
                                <a href="javascript:void(0)" @click="openEditModal(bo)" class="me-2">
                                    <CIcon class="text-info" :icon="cilPencil" />
                                </a>
                                <a href="javascript:void(0)" @click="openDeleteModal(bo.id)">
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
            <CButton color="danger" @click="deleteBo">Xóa</CButton>
        </CModalFooter>
    </CModal>

    <!-- Modal Chỉnh sửa -->
    <CModal :visible="isEditModalVisible" @close="closeEditModal">
        <CModalHeader>
            <CModalTitle>Chỉnh sửa bộ</CModalTitle>
        </CModalHeader>
        <CModalBody>
            <CForm>
                <div class="mb-3">
                    <CFormLabel for="editNameVi">Tên tiếng Việt:</CFormLabel>
                    <CFormInput id="editNameVi" v-model="editingBo.name" />
                </div>
                <div class="mb-3">
                    <CFormLabel for="editNameLatinh">Tên Latinh:</CFormLabel>
                    <CFormInput id="editNameLatinh" v-model="editingBo.nameLatinh" required/>
                </div>
                <div class="mb-3">
                    <CFormLabel for="editLop">Lớp:</CFormLabel>
                    <CFormSelect id="editLop" v-model="editingBo.lop" :options="danhSachLop" required />
                </div>
                <div class="mb-3">
                    <CFormLabel>Trạng thái:</CFormLabel>
                    <CFormCheck id="editStatusOn" label="Bật" name="editStatus" :checked="editingBo.status"
                        @change="editingBo.status = true" />
                    <CFormCheck id="editStatusOff" label="Tắt" name="editStatus" :checked="!editingBo.status"
                        @change="editingBo.status = false" />
                </div>
            </CForm>
        </CModalBody>
        <CModalFooter>
            <CButton color="secondary" @click="closeEditModal">Hủy</CButton>
            <CButton color="primary" @click="saveEditedBo">Lưu thay đổi</CButton>
        </CModalFooter>
    </CModal>

    <!-- Modal Thêm mới -->
    <CModal :visible="isAddModalVisible" @close="closeAddModal">
        <CModalHeader>
            <CModalTitle>Thêm bộ mới</CModalTitle>
        </CModalHeader>
        <CModalBody>
            <CForm>
                <div class="mb-3">
                    <CFormLabel for="addNameVi">Tên tiếng Việt:</CFormLabel>
                    <CFormInput id="addNameVi" v-model="newBo.name" />
                </div>
                <div class="mb-3">
                    <CFormLabel for="addNameLatinh">Tên Latinh:</CFormLabel>
                    <CFormInput id="addNameLatinh" v-model="newBo.nameLatinh" />
                </div>
                <div class="mb-3">
                    <CFormLabel for="addLop">Lớp:</CFormLabel>
                    <CFormSelect id="addLop" v-model="newBo.lop" :options="danhSachLop" required />
                </div>
                <div class="mb-3">
                    <CFormLabel>Trạng thái:</CFormLabel>
                    <CFormCheck id="addStatusOn" label="Bật" name="addStatus" :checked="newBo.status"
                        @change="newBo.status = true" />
                    <CFormCheck id="addStatusOff" label="Tắt" name="addStatus" :checked="!newBo.status"
                        @change="newBo.status = false" />
                </div>
            </CForm>
        </CModalBody>
        <CModalFooter>
            <CButton color="secondary" @click="closeAddModal">Hủy</CButton>
            <CButton color="primary" @click="addNewBo">Thêm mới</CButton>
        </CModalFooter>
    </CModal>
</template>
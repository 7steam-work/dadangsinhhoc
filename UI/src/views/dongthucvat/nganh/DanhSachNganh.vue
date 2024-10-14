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
const selectedNganhId = ref(null);
const editingNganh = ref({
    name: '',
    nameLatinh: '',
    status: true
});
const newNganh = ref({
    name: '',
    nameLatinh: '',
    status: true
});

const openDeleteModal = (nganhId) => {
    selectedNganhId.value = nganhId;
    isDeleteModalVisible.value = true;
}

const openEditModal = (nganh) => {
    editingNganh.value = { ...nganh, status: true }; // Assuming status is not in the original data
    isEditModalVisible.value = true;
}

const openAddModal = () => {
    newNganh.value = { name: '', nameLatinh: '', status: true };
    isAddModalVisible.value = true;
}

const closeDeleteModal = () => {
    isDeleteModalVisible.value = false;
    selectedNganhId.value = null;
}

const closeEditModal = () => {
    isEditModalVisible.value = false;
    editingNganh.value = { name: '', nameLatinh: '', status: true };
}

const closeAddModal = () => {
    isAddModalVisible.value = false;
    newNganh.value = { name: '', nameLatinh: '', status: true };
}

// Dữ liệu giả lập
const nganhs = ref([
    {
        id: 1,
        name: 'Hổ Đông Dương',
        nameLatinh: 'Panthera tigris tigris',
        updatedAt: '2023-05-15',
        createdBy: 'createdBy Nam Giang'
    },
    {
        id: 2,
        name: 'Voọc mũi hếch',
        nameLatinh: 'Rhinopithecus avunculus',
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

const deleteNganh = () => {
    nganhs.value = nganhs.value.filter(nganh => nganh.id !== selectedNganhId.value);
    console.log("Xóa với ID:", selectedNganhId.value);
    closeDeleteModal();
}

const saveEditedNganh = () => {
    const index = nganhs.value.findIndex(nganh => nganh.id === editingNganh.value.id);
    if (index !== -1) {
        nganhs.value[index] = { ...nganhs.value[index], ...editingNganh.value };
    }
    closeEditModal();
}

const addNewNganh = () => {
    const newId = Math.max(...nganhs.value.map(n => n.id)) + 1;
    const currentDate = new Date().toISOString().split('T')[0];
    const newNganhWithMetadata = {
        ...newNganh.value,
        id: newId,
        updatedAt: currentDate,
        createdBy: 'Current User' // Replace with actual current user logic
    };
    nganhs.value.push(newNganhWithMetadata);
    closeAddModal();
}

// Fetch danh sách khi component được mount
const fetchnganhs = async () => {
    try {
        const response = await fetch('/api/nganh');
        const data = await response.json();
        nganhs.value = data;
    } catch (error) {
        console.error('Lỗi khi tải danh sách:', error);
    }
}

fetchnganhs();
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
                            <CTableHeaderCell scope="col">Ngày cập nhật</CTableHeaderCell>
                            <CTableHeaderCell scope="col">Người tạo</CTableHeaderCell>
                            <CTableHeaderCell scope="col"></CTableHeaderCell>
                        </CTableRow>
                    </CTableHead>
                    <CTableBody>
                        <CTableRow v-for="(nganh, index) in nganhs" :key="nganh.id">
                            <CTableHeaderCell scope="row">{{ index + 1 }}</CTableHeaderCell>
                            <CTableDataCell>{{ nganh.name }}</CTableDataCell>
                            <CTableDataCell>{{ nganh.nameLatinh }}</CTableDataCell>
                            <CTableDataCell>{{ nganh.updatedAt }}</CTableDataCell>
                            <CTableDataCell>{{ nganh.createdBy }}</CTableDataCell>
                            <CTableDataCell>
                                <a href="javascript:void(0)" @click="openEditModal(nganh)" class="me-2">
                                    <CIcon class="text-info" :icon="cilPencil" />
                                </a>
                                <a href="javascript:void(0)" @click="openDeleteModal(nganh.id)">
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
            <CButton color="danger" @click="deleteNganh">Xóa</CButton>
        </CModalFooter>
    </CModal>

    <!-- Modal Chỉnh sửa -->
    <CModal :visible="isEditModalVisible" @close="closeEditModal">
        <CModalHeader>
            <CModalTitle>Chỉnh sửa ngành</CModalTitle>
        </CModalHeader>
        <CModalBody>
            <CForm>
                <div class="mb-3">
                    <CFormLabel for="editNameVi">Tên tiếng Việt</CFormLabel>
                    <CFormInput id="editNameVi" v-model="editingNganh.name" />
                </div>
                <div class="mb-3">
                    <CFormLabel for="editNameLatinh">Tên Latinh</CFormLabel>
                    <CFormInput id="editNameLatinh" v-model="editingNganh.nameLatinh" />
                </div>
                <div class="mb-3">
                    <CFormLabel>Trạng thái</CFormLabel>
                    <CFormCheck
                        id="editStatusOn"
                        label="Bật"
                        name="editStatus"
                        :checked="editingNganh.status"
                        @change="editingNganh.status = true"
                    />
                    <CFormCheck
                        id="editStatusOff"
                        label="Tắt"
                        name="editStatus"
                        :checked="!editingNganh.status"
                        @change="editingNganh.status = false"
                    />
                </div>
            </CForm>
        </CModalBody>
        <CModalFooter>
            <CButton color="secondary" @click="closeEditModal">Hủy</CButton>
            <CButton color="primary" @click="saveEditedNganh">Lưu thay đổi</CButton>
        </CModalFooter>
    </CModal>

    <!-- Modal Thêm mới -->
    <CModal :visible="isAddModalVisible" @close="closeAddModal">
        <CModalHeader>
            <CModalTitle>Thêm ngành mới</CModalTitle>
        </CModalHeader>
        <CModalBody>
            <CForm>
                <div class="mb-3">
                    <CFormLabel for="addNameVi">Tên tiếng Việt</CFormLabel>
                    <CFormInput id="addNameVi" v-model="newNganh.name" />
                </div>
                <div class="mb-3">
                    <CFormLabel for="addNameLatinh">Tên Latinh</CFormLabel>
                    <CFormInput id="addNameLatinh" v-model="newNganh.nameLatinh" />
                </div>
                <div class="mb-3">
                    <CFormLabel>Trạng thái</CFormLabel>
                    <CFormCheck
                        id="addStatusOn"
                        label="Bật"
                        name="addStatus"
                        :checked="newNganh.status"
                        @change="newNganh.status = true"
                    />
                    <CFormCheck
                        id="addStatusOff"
                        label="Tắt"
                        name="addStatus"
                        :checked="!newNganh.status"
                        @change="newNganh.status = false"
                    />
                </div>
            </CForm>
        </CModalBody>
        <CModalFooter>
            <CButton color="secondary" @click="closeAddModal">Hủy</CButton>
            <CButton color="primary" @click="addNewNganh">Thêm mới</CButton>
        </CModalFooter>
    </CModal>
</template>
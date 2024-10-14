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
const selectedLopId = ref(null);
const editingLop = ref({
    name: '',
    nameLatinh: '',
    nganh: '',
    status: true
});
const newLop = ref({
    name: '',
    nameLatinh: '',
    nganh: '',
    status: true
});

const openDeleteModal = (lopId) => {
    selectedLopId.value = lopId;
    isDeleteModalVisible.value = true;
}

const openEditModal = (lop) => {
    editingLop.value = { ...lop, status: true }; // Assuming status is not in the original data
    isEditModalVisible.value = true;
}

const openAddModal = () => {
    newLop.value = { name: '', nameLatinh: '', status: true };
    isAddModalVisible.value = true;
}

const closeDeleteModal = () => {
    isDeleteModalVisible.value = false;
    selectedLopId.value = null;
}

const closeEditModal = () => {
    isEditModalVisible.value = false;
    editingLop.value = { name: '', nameLatinh: '', status: true };
}

const closeAddModal = () => {
    isAddModalVisible.value = false;
    newLop.value = { name: '', nameLatinh: '', status: true };
}

// Dữ liệu giả lập
const lops = ref([
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

const deleteLop = () => {
    lops.value = lops.value.filter(lop => lop.id !== selectedLopId.value);
    console.log("Xóa với ID:", selectedLopId.value);
    closeDeleteModal();
}

const saveEditedLop = () => {
    const index = lops.value.findIndex(lop => lop.id === editingLop.value.id);
    if (index !== -1) {
        lops.value[index] = { ...lops.value[index], ...editingLop.value };
    }
    closeEditModal();
}

const addNewLop = () => {
    const newId = Math.max(...lops.value.map(n => n.id)) + 1;
    const currentDate = new Date().toISOString().split('T')[0];
    const newLopWithMetadata = {
        ...newLop.value,
        id: newId,
        updatedAt: currentDate,
        createdBy: 'Current User' // Replace with actual current user logic
    };
    lops.value.push(newLopWithMetadata);
    closeAddModal();
}

// Fetch danh sách khi component được mount
const fetchlops = async () => {
    try {
        const response = await fetch('/api/lop');
        const data = await response.json();
        lops.value = data;
    } catch (error) {
        console.error('Lỗi khi tải danh sách:', error);
    }
}

fetchlops();
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
                            <CTableHeaderCell scope="col">Ngành</CTableHeaderCell>
                            <CTableHeaderCell scope="col">Ngày cập nhật</CTableHeaderCell>
                            <CTableHeaderCell scope="col">Người tạo</CTableHeaderCell>
                            <CTableHeaderCell scope="col"></CTableHeaderCell>
                        </CTableRow>
                    </CTableHead>
                    <CTableBody>
                        <CTableRow v-for="(lop, index) in lops" :key="lop.id">
                            <CTableHeaderCell scope="row">{{ index + 1 }}</CTableHeaderCell>
                            <CTableDataCell>{{ lop.name }}</CTableDataCell>
                            <CTableDataCell>{{ lop.nameLatinh }}</CTableDataCell>
                            <CTableDataCell>{{ lop.nganh }}</CTableDataCell>
                            <CTableDataCell>{{ lop.updatedAt }}</CTableDataCell>
                            <CTableDataCell>{{ lop.createdBy }}</CTableDataCell>
                            <CTableDataCell>
                                <a href="javascript:void(0)" @click="openEditModal(lop)" class="me-2">
                                    <CIcon class="text-info" :icon="cilPencil" />
                                </a>
                                <a href="javascript:void(0)" @click="openDeleteModal(lop.id)">
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
            <CButton color="danger" @click="deleteLop">Xóa</CButton>
        </CModalFooter>
    </CModal>

    <!-- Modal Chỉnh sửa -->
    <CModal :visible="isEditModalVisible" @close="closeEditModal">
        <CModalHeader>
            <CModalTitle>Chỉnh sửa lớp</CModalTitle>
        </CModalHeader>
        <CModalBody>
            <CForm>
                <div class="mb-3">
                    <CFormLabel for="editNameVi">Tên tiếng Việt:</CFormLabel>
                    <CFormInput id="editNameVi" v-model="editingLop.name" />
                </div>
                <div class="mb-3">
                    <CFormLabel for="editNameLatinh">Tên Latinh:</CFormLabel>
                    <CFormInput id="editNameLatinh" v-model="editingLop.nameLatinh" required/>
                </div>
                <div class="mb-3">
                    <CFormLabel for="editNganh">Ngành:</CFormLabel>
                    <CFormSelect id="editNganh" v-model="editingLop.nganh" :options="danhSachNganh" required />
                </div>
                <div class="mb-3">
                    <CFormLabel>Trạng thái:</CFormLabel>
                    <CFormCheck id="editStatusOn" label="Bật" name="editStatus" :checked="editingLop.status"
                        @change="editingLop.status = true" />
                    <CFormCheck id="editStatusOff" label="Tắt" name="editStatus" :checked="!editingLop.status"
                        @change="editingLop.status = false" />
                </div>
            </CForm>
        </CModalBody>
        <CModalFooter>
            <CButton color="secondary" @click="closeEditModal">Hủy</CButton>
            <CButton color="primary" @click="saveEditedLop">Lưu thay đổi</CButton>
        </CModalFooter>
    </CModal>

    <!-- Modal Thêm mới -->
    <CModal :visible="isAddModalVisible" @close="closeAddModal">
        <CModalHeader>
            <CModalTitle>Thêm lớp mới</CModalTitle>
        </CModalHeader>
        <CModalBody>
            <CForm>
                <div class="mb-3">
                    <CFormLabel for="addNameVi">Tên tiếng Việt:</CFormLabel>
                    <CFormInput id="addNameVi" v-model="newLop.name" />
                </div>
                <div class="mb-3">
                    <CFormLabel for="addNameLatinh">Tên Latinh:</CFormLabel>
                    <CFormInput id="addNameLatinh" v-model="newLop.nameLatinh" />
                </div>
                <div class="mb-3">
                    <CFormLabel for="addNganh">Ngành:</CFormLabel>
                    <CFormSelect id="addNganh" v-model="newLop.nganh" :options="danhSachNganh" required />
                </div>
                <div class="mb-3">
                    <CFormLabel>Trạng thái:</CFormLabel>
                    <CFormCheck id="addStatusOn" label="Bật" name="addStatus" :checked="newLop.status"
                        @change="newLop.status = true" />
                    <CFormCheck id="addStatusOff" label="Tắt" name="addStatus" :checked="!newLop.status"
                        @change="newLop.status = false" />
                </div>
            </CForm>
        </CModalBody>
        <CModalFooter>
            <CButton color="secondary" @click="closeAddModal">Hủy</CButton>
            <CButton color="primary" @click="addNewLop">Thêm mới</CButton>
        </CModalFooter>
    </CModal>
</template>
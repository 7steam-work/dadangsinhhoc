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
const selectedMessageId = ref(null);
const editingMessage = ref({
    note: '',
    status: true
});

const openDeleteModal = (messageId) => {
    selectedMessageId.value = messageId;
    isDeleteModalVisible.value = true;
}

const openEditModal = (message) => {
    editingMessage.value = { ...message, status: true }; // Assuming status is not in the original data
    isEditModalVisible.value = true;
}

const closeDeleteModal = () => {
    isDeleteModalVisible.value = false;
    selectedMessageId.value = null;
}

const closeEditModal = () => {
    isEditModalVisible.value = false;
    editingMessage.value = { name: '', nameLatinh: '', status: true };
}

// Dữ liệu giả lập
const messages = ref([
    {
        id: 1,
        name: 'Hổ Đông Dương',
        email: 'Panthera tigris tigris',
        phone: '2023-05-15',
        rPH: 'createdBy Nam Giang',
        status: 'Đã trả lời'
    },
    {
        id: 2,
        name: 'Dương',
        email: 'Panthera tigris tigris',
        phone: '2023-05-15',
        rPH: 'createdBy Nam Giang',
        status: 'Chưa trả lời'
    }
]);

const deleteMessage = () => {
    messages.value = messages.value.filter(message => message.id !== selectedMessageId.value);
    console.log("Xóa với ID:", selectedMessageId.value);
    closeDeleteModal();
}

const saveEditedMessage = () => {
    const index = messages.value.findIndex(message => message.id === editingMessage.value.id);
    if (index !== -1) {
        messages.value[index] = { ...messages.value[index], ...editingMessage.value };
    }
    closeEditModal();
}

// Fetch danh sách khi component được mount
const fetchmessages = async () => {
    try {
        const response = await fetch('/api/message');
        const data = await response.json();
        messages.value = data;
    } catch (error) {
        console.error('Lỗi khi tải danh sách:', error);
    }
}

fetchmessages();
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
                            <CTableHeaderCell scope="col">Tên người gửi</CTableHeaderCell>
                            <CTableHeaderCell scope="col">Email</CTableHeaderCell>
                            <CTableHeaderCell scope="col">Số điện thoại</CTableHeaderCell>
                            <CTableHeaderCell scope="col">Đơn vị</CTableHeaderCell>
                            <CTableHeaderCell scope="col">Trạng thái</CTableHeaderCell>
                            <CTableHeaderCell scope="col"></CTableHeaderCell>
                        </CTableRow>
                    </CTableHead>
                    <CTableBody>
                        <CTableRow v-for="(message, index) in messages" :key="message.id">
                            <CTableHeaderCell scope="row">{{ index + 1 }}</CTableHeaderCell>
                            <CTableDataCell>{{ message.name }}</CTableDataCell>
                            <CTableDataCell>{{ message.email }}</CTableDataCell>
                            <CTableDataCell>{{ message.phone }}</CTableDataCell>
                            <CTableDataCell>{{ message.rPH }}</CTableDataCell>
                            <CTableDataCell>{{ message.status }}</CTableDataCell>
                            <CTableDataCell>
                                <a href="javascript:void(0)" @click="openEditModal(message)" class="me-2">
                                    <CIcon class="text-info" :icon="cilPencil" />
                                </a>
                                <a href="javascript:void(0)" @click="openDeleteModal(message.id)">
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
            <CButton color="danger" @click="deleteMessage">Xóa</CButton>
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
                    <label class="control-label" for="note">Ghi chú:</label>
                    <textarea id="note" v-model="editingMessage.note" class="form-control" rows="4"></textarea>
                </div>
                <div class="mb-3">
                    <CFormLabel>Trạng thái:</CFormLabel>
                    <CFormCheck id="editStatusDone" label="Đã trả lời" name="editStatus" :checked="editingMessage.status"
                        @change="editingMessage.status = true" />
                    <CFormCheck id="editStatusNotYet" label="Chưa trả lời" name="editStatus" :checked="!editingMessage.status"
                        @change="editingMessage.status = false" />
                </div>
            </CForm>
        </CModalBody>
        <CModalFooter>
            <CButton color="secondary" @click="closeEditModal">Hủy</CButton>
            <CButton color="primary" @click="saveEditedMessage">Lưu thay đổi</CButton>
        </CModalFooter>
    </CModal>
</template>
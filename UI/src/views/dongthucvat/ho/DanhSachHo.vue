<script setup>
import { ref, computed, watch } from 'vue'
import { useRoute } from 'vue-router'
import { CIcon } from '@coreui/icons-vue'
import { cilSearch, cilPlus, cilTrash, cilPencil } from '@coreui/icons'
import { useJwt } from '@vueuse/integrations/useJwt'
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
  CFormCheck,
  CPagination,
  CPaginationItem,
  CAlert
} from '@coreui/vue';

// Các trạng thái để điều khiển modal
const isDeleteModalVisible = ref(false);
const isEditModalVisible = ref(false);
const isAddModalVisible = ref(false);
const selectedHoId = ref(null);
const editingHo = ref({});
const user = ref({});
var oldNameLatinh = '';
const newHo = ref({
  name: '',
  nameLatinh: '',
  idBo: '',
  status: true
});

const getCurrentUser = async () => {
  const data = ref([]);
  const error = ref(null);
  try {
    const response = await fetch(`http://localhost:8080/api/users/findUserByEmail/${getCurrentUserFromToken()}`);
    if (!response.ok) throw new Error("Some thing went wrong...");
    data.value = await response.json();
    user.value = data.value.data;
    console.log('ID người dùng hiện tại:', user.value.id);
    return user.value.id;
  } catch (err) {
    error.value = err;
    console.log('Lỗi khi tải dữ liệu:', error.value);
  }
}

// Hàm lấy thông tin người dùng từ token
const getCurrentUserFromToken = () => {
  const token = localStorage.getItem('token');
  if (!token) {
    throw new Error('Token không tồn tại');
  }

  const { payload } = useJwt(token); // Giải mã token và lấy payload

  if (payload.value) {
    console.log('Token đã giải mã:', payload.value);
    // Trích xuất thông tin người dùng từ payload.value
    console.log('Email người dùng hiện tại:', payload.value.sub);
    return payload.value.sub;
  } else {
    console.error('Không thể giải mã token hoặc payload không tồn tại');
    return null;
  }
};

const openDeleteModal = (hoId) => {
  selectedHoId.value = hoId;
  isDeleteModalVisible.value = true;
}

const openEditModal = async (hoId) => {
  const data = ref([]);
  const error = ref(null);
  try {
    const response = await fetch(`http://localhost:8080/api/Ho/getHoById/${hoId}`);
    if (!response.ok) throw new Error("Some thing went wrong...");
    data.value = await response.json();
    editingHo.value = data.value.data;
    oldNameLatinh = editingHo.value.nameLatinh;
    console.log("Đang chỉnh sửa: ", editingHo.value);
    isEditModalVisible.value = true;
  } catch (err) {
    error.value = err;
    console.log('Lỗi khi tải dữ liệu:', error.value);
  }
}

const openAddModal = () => {
  newHo.value = { name: '', nameLatinh: '', status: true };
  isAddModalVisible.value = true;
}

const closeDeleteModal = () => {
  isDeleteModalVisible.value = false;
  selectedHoId.value = null;
}

const closeEditModal = () => {
  isEditModalVisible.value = false;
  editingHo.value = { name: '', nameLatinh: '', status: true };
}

const closeAddModal = () => {
  isAddModalVisible.value = false;
  newHo.value = { name: '', nameLatinh: '', status: true };
}

const currentPage = ref(0); // Trang hiện tại
const pageSize = ref(20); // Số lượng mục trên mỗi trang
const totalPages = ref(0); // Tổng số trang

const prevPage = () => {
  if (currentPage.value > 0) {
    currentPage.value--;
    searchHos();
  }
};

const nextPage = () => {
  if (currentPage.value < totalPages.value - 1) {
    currentPage.value++;
    searchHos();
  }
};

const goToPage = (page) => {
  currentPage.value = page - 1;
  searchHos();
};

//Lấy route hiện tại để xác định 'dong-vat' hoặc 'thuc-vat'
const route = useRoute();
const isDongVat = ref(route.path.includes('/dong-vat')); // Kiểm tra route có chứa '/dong-vat' không
console.log('Is Động Vật:', isDongVat.value);

const hos = ref([]);

const searchKeyword = ref('');  // Biến lưu trữ từ khóa tìm kiếm

const searchHos = async () => {
  try {
    watch(searchKeyword, (newKeyword, oldKeyword) => {
      if (newKeyword !== oldKeyword) {
        currentPage.value = 0;
      }
    });

    const data = ref([]);
    const response = await fetch(`http://localhost:8080/api/Ho/searchHo?keyword=${searchKeyword.value}&loai=${isDongVat.value}&page=${currentPage.value}&size=${pageSize.value}`);
    if (!response.ok) throw new Error('Có lỗi xảy ra khi tìm kiếm họ');

    data.value = await response.json();
    data.value = data.value.data;
    hos.value = data.value.content;  // Cập nhật danh sách họ dựa trên kết quả tìm kiếm
    totalPages.value = data.value.totalPages;
    console.log('Kết quả tìm kiếm:', hos.value);
    console.log('Trang hiện tại:', currentPage.value);
  } catch (error) {
    console.error('Lỗi khi tìm kiếm:', error);
    // Bạn có thể thêm thông báo lỗi cho người dùng
  }
};
searchHos();

const alertMessage = ref(''); // Thông báo
const alertType = ref(''); // Loại thông báo (success, error)
const showAlert = ref(false);
const alertMessageModal = ref(''); // Thông báo
const alertTypeModal = ref(''); // Loại thông báo (success, error)
const showAlertModal = ref(false);

const deleteHo = async () => {
  try {
    // Lấy token từ localStorage hoặc từ store của bạn
    const token = localStorage.getItem('token');
    console.log("token:", token);

    console.log('Xóa với ID: ', selectedHoId.value);
    // Gửi yêu cầu DELETE đến backend để xóa họ
    const response = await fetch(`http://localhost:8080/api/Ho/deleteHo/${selectedHoId.value}`, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      },
    });

    if (!response.ok) throw new Error('Có lỗi xảy ra khi xóa họ');

    alertMessage.value = 'Xóa thành công!';
    alertType.value = 'success';
    showAlert.value = true;
    setTimeout(() => {
      showAlert.value = false;
    }, 3000)
    // Cập nhật lại danh sách trên frontend
    searchHos();
    closeDeleteModal(); // Đóng modal sau khi xóa thành công
  } catch (error) {
    console.error('Lỗi khi xóa:', error);
    // Thông báo lỗi cho người dùng
    alertMessage.value = 'Có lỗi xảy ra khi xóa!';
    alertType.value = 'danger';
    showAlert.value = true;
    setTimeout(() => {
      showAlert.value = false;
    }, 3000);
  }
}

const danhSachBo = computed(() => {
  return bos.value.map(bo => ({
    label: bo.name,
    value: bo.id
  }))
})

const saveEditedHo = async () => {
  try {
    const currentDate = new Date().toISOString();
    const updatedBy = await getCurrentUser(); // Chờ Promise updatedBy được fulfilled
    const editedHoWithMetadata = {
      ...editingHo.value,
      updatedAt: currentDate,
      updatedBy: updatedBy
    };

    if (!editedHoWithMetadata.nameLatinh) {
      // Hiển thị thông báo lỗi cho người dùng
      alertMessageModal.value = 'Vui lòng nhập tên Latinh!';
      alertTypeModal.value = 'danger';
      showAlertModal.value = true;
      setTimeout(() => {
        showAlertModal.value = false;
      }, 3000);
      return;
    }

    if (!editedHoWithMetadata.idBo) {
      // Hiển thị thông báo lỗi cho người dùng
      alertMessageModal.value = 'Vui lòng chọn bộ!';
      alertTypeModal.value = 'danger';
      showAlertModal.value = true;
      setTimeout(() => {
        showAlertModal.value = false;
      }, 3000);
      return;
    }

    // Kiểm tra trùng tên Latin trước khi sửa
    const responseCheck = await fetch(`http://localhost:8080/api/Ho/checkDuplicateNameLatinh?nameLatinh=${editedHoWithMetadata.nameLatinh}&isAddingNew=false&oldNameLatinh=${oldNameLatinh}`);
    if (!responseCheck.ok) throw new Error('Có lỗi xảy ra khi kiểm tra trùng tên Latin');

    const data = await responseCheck.json();
    console.log("Kiểm tra trùng: ", data.data);
    if (!data.data) { // Nếu trùng tên Latin
      // Hiển thị thông báo lỗi cho người dùng
      alertMessageModal.value = 'Tên Latinh đã tồn tại!';
      alertTypeModal.value = 'danger';
      showAlertModal.value = true;
      setTimeout(() => {
        showAlertModal.value = false;
      }, 3000);
      return;
    }

    // Lấy token từ localStorage hoặc từ store của bạn
    const token = localStorage.getItem('token');
    console.log("token:", token);

    // Gửi yêu cầu PUT đến backend để chỉnh sửa
    const response = await fetch(`http://localhost:8080/api/Ho/updateHo/${editedHoWithMetadata.id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      },
      body: JSON.stringify(editedHoWithMetadata),
    });

    console.log(editedHoWithMetadata);

    if (!response.ok) throw new Error('Có lỗi xảy ra khi chỉnh sửa họ');

    alertMessage.value = 'Chỉnh sửa thành công!';
    alertType.value = 'success';
    showAlert.value = true;
    setTimeout(() => {
      showAlert.value = false;
    }, 3000);
    // Cập nhật lại danh sách họ trên frontend
    searchHos();
    closeEditModal();  // Đóng modal sau khi chỉnh sửa thành công
    console.log('Chỉnh sửa thành công với id:', editedHoWithMetadata.id);
  } catch (error) {
    console.error('Lỗi khi chỉnh sửa:', error);
    alertMessage.value = 'Có lỗi xảy ra khi chỉnh sửa!';
    alertType.value = 'danger';
    showAlert.value = true;
    setTimeout(() => {
      showAlert.value = false;
    }, 3000);
  }
}

const addNewHo = async () => {
  try {
    const currentDate = new Date().toISOString();
    const createdBy = await getCurrentUser();
    const newHoWithMetadata = {
      ...newHo.value,
      id: 1000,
      loai: isDongVat.value,
      createdAt: currentDate,
      createdBy: createdBy
    };

    if (!newHoWithMetadata.nameLatinh) {
      // Hiển thị thông báo lỗi cho người dùng
      alertMessageModal.value = 'Vui lòng nhập tên Latinh!';
      alertTypeModal.value = 'danger';
      showAlertModal.value = true;
      setTimeout(() => {
        showAlertModal.value = false;
      }, 3000);
      return;
    }

    if (!newHoWithMetadata.idBo) {
      // Hiển thị thông báo lỗi cho người dùng
      alertMessageModal.value = 'Vui lòng chọn bộ!';
      alertTypeModal.value = 'danger';
      showAlertModal.value = true;
      setTimeout(() => {
        showAlertModal.value = false;
      }, 3000);
      return;
    }

    // Kiểm tra trùng tên Latin trước khi thêm mới
    const responseCheck = await fetch(`http://localhost:8080/api/Ho/checkDuplicateNameLatinh?nameLatinh=${newHoWithMetadata.nameLatinh}&isAddingNew=true`);
    if (!responseCheck.ok) throw new Error('Có lỗi xảy ra khi kiểm tra trùng tên Latin');

    const data = await responseCheck.json();
    console.log("Kiểm tra trùng: ", data.data);
    if (!data.data) { // Nếu trùng tên Latin
      // Hiển thị thông báo lỗi cho người dùng
      alertMessageModal.value = 'Tên Latinh đã tồn tại!';
      alertTypeModal.value = 'danger';
      showAlertModal.value = true;
      setTimeout(() => {
        showAlertModal.value = false;
      }, 3000);
      return;
    }

    console.log("Dữ liệu thêm mới: ", newHoWithMetadata);

    alertMessage.value = 'Thêm mới thành công!';
    alertType.value = 'success';
    showAlert.value = true;
    setTimeout(() => {
      showAlert.value = false;
    }, 3000);

    // Lấy token từ localStorage hoặc từ store của bạn
    const token = localStorage.getItem('token');
    console.log("token:", token);

    // Gửi yêu cầu POST đến backend để thêm mới
    const response = await fetch('http://localhost:8080/api/Ho/addNewHo', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}` // Thêm token vào header
      },
      body: JSON.stringify(newHoWithMetadata),
    });

    if (!response.ok) {
      const errorData = await response.json();
      throw new Error(errorData.message || 'Có lỗi xảy ra khi thêm mới họ');
    }

    // Thêm mới vào danh sách nếu thành công
    searchHos();
    closeAddModal();  // Đóng modal sau khi thêm thành công
    console.log('Thêm mới thành công.',);
  } catch (error) {
    console.error('Lỗi khi thêm mới:', error);
    // Bạn có thể thêm thông báo lỗi cho người dùng
    alertMessage.value = 'Có lỗi xảy ra khi thêm mới!';
    alertType.value = 'danger';
    showAlert.value = true;
    setTimeout(() => {
      showAlert.value = false;
    }, 3000);
  }
}

const bos = ref([]);
// Bảng khóa ngoại
const fetchBos = async () => {
  const data = ref([]);
  const error = ref(null);
  try {
    const response = await fetch(`http://localhost:8080/api/bo/getAllBoByLoai/${isDongVat.value}`);
    if (!response.ok) throw new Error("Some thing went wrong...");
    data.value = await response.json();
    bos.value = data.value.data;
    console.log("Danh sách bảng khóa ngoại: ", bos.value);
  } catch (err) {
    error.value = err;
    console.log('Lỗi khi tải danh sách:', error.value);
  }
}
fetchBos();

const getBoName = (idBo) => {
  const bo = bos.value.find(b => b.id === idBo);
  return bo ? bo.name : '';
};
</script>

<template>
  <CCol :xs="12">
    <CCard class="mb-4">
      <CCardHeader class="d-flex justify-content-between align-items-center">
        <CButton variant="outline" shape="rounded-0" color="success" @click="openAddModal">
          <CIcon class="me-2" :icon="cilPlus" />Thêm mới
        </CButton>
        <CInputGroup class="w-50">
          <CFormInput v-model="searchKeyword" aria-label="Tìm kiếm" aria-describedby="btnGroupAddon" />
          <CInputGroupText id="basic-addon2">
            <CIcon :icon="cilSearch" />
          </CInputGroupText>
          <CButton type="button" color="secondary" variant="outline" id="button-addon2" aria-describedby="btnGroupAddon"
            @click="searchHos">Tìm kiếm</CButton>
        </CInputGroup>
      </CCardHeader>
      <CCardBody>
        <div>
          <CAlert v-if="showAlert" :color="alertType">{{ alertMessage }}</CAlert>
        </div>
        <CTable hover bordered striped>
          <CTableHead>
            <CTableRow>
              <CTableHeaderCell scope="col">STT</CTableHeaderCell>
              <CTableHeaderCell scope="col">Tên tiếng Việt</CTableHeaderCell>
              <CTableHeaderCell scope="col">Tên Latinh</CTableHeaderCell>
              <CTableHeaderCell scope="col">Bộ</CTableHeaderCell>
              <CTableHeaderCell scope="col">Ngày cập nhật</CTableHeaderCell>
              <CTableHeaderCell scope="col">Người tạo</CTableHeaderCell>
              <CTableHeaderCell scope="col"></CTableHeaderCell>
            </CTableRow>
          </CTableHead>
          <CTableBody>
            <CTableRow v-for="(ho, index) in hos" :key="ho.id">
              <CTableHeaderCell scope="row">{{ index + 1 }}</CTableHeaderCell>
              <CTableDataCell>{{ ho.name }}</CTableDataCell>
              <CTableDataCell>{{ ho.nameLatinh }}</CTableDataCell>
              <CTableDataCell>{{ getBoName(ho.idBo) }}</CTableDataCell>
              <CTableDataCell>{{ ho.updatedAt }}</CTableDataCell>
              <CTableDataCell>{{ ho.createdBy }}</CTableDataCell>
              <CTableDataCell>
                <a href="javascript:void(0)" @click="openEditModal(ho.id)" class="me-2">
                  <CIcon class="text-info" :icon="cilPencil" />
                </a>
                <a href="javascript:void(0)" @click="openDeleteModal(ho.id)">
                  <CIcon class="text-danger" :icon="cilTrash" />
                </a>
              </CTableDataCell>
            </CTableRow>
          </CTableBody>
        </CTable>
        <div class="pagination-container">
          <CPagination :v-model:active-page="currentPage" :pages="totalPages" aria-label="Page navigation example">
            <CPaginationItem :disabled="currentPage === 0" @click="prevPage">Previous</CPaginationItem>
            <CPaginationItem v-for="page in totalPages" :key="page" :active="page === currentPage + 1"
              @click="goToPage(page)">{{ page }}</CPaginationItem>
            <CPaginationItem :disabled="currentPage === totalPages - 1" @click="nextPage">Next</CPaginationItem>
          </CPagination>
        </div>
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
      <CButton color="danger" @click="deleteHo">Xóa</CButton>
    </CModalFooter>
  </CModal>

  <!-- Modal Chỉnh sửa -->
  <CModal :visible="isEditModalVisible" @close="closeEditModal">
    <CModalHeader>
      <CModalTitle>Chỉnh sửa họ</CModalTitle>
    </CModalHeader>
    <CModalBody>
      <div>
        <CAlert v-if="showAlertModal" :color="alertTypeModal">{{ alertMessageModal }}</CAlert>
      </div>
      <CForm>
        <div class="mb-3">
          <CFormLabel for="editNameVi">Tên tiếng Việt:</CFormLabel>
          <CFormInput id="editNameVi" v-model="editingHo.name" />
        </div>
        <div class="mb-3">
          <CFormLabel for="editNameLatinh">Tên Latinh:</CFormLabel>
          <CFormInput id="editNameLatinh" v-model="editingHo.nameLatinh" required />
        </div>
        <div class="mb-3">
          <CFormLabel for="editBo">Bộ:</CFormLabel>
          <CFormSelect id="editBo" v-model="editingHo.idBo" :options="danhSachBo" required />
        </div>
        <div class="mb-3">
          <CFormLabel>Trạng thái:</CFormLabel>
          <CFormCheck id="editStatusOn" type="radio" label="Bật" name="editStatus" :checked="editingHo.status"
            @change="editingHo.status = true" />
          <CFormCheck id="editStatusOff" type="radio" label="Tắt" name="editStatus" :checked="!editingHo.status"
            @change="editingHo.status = false" />
        </div>
      </CForm>
    </CModalBody>
    <CModalFooter>
      <CButton color="secondary" @click="closeEditModal">Hủy</CButton>
      <CButton color="primary" @click="saveEditedHo">Lưu thay đổi</CButton>
    </CModalFooter>
  </CModal>

  <!-- Modal Thêm mới -->
  <CModal :visible="isAddModalVisible" @close="closeAddModal">
    <CModalHeader>
      <CModalTitle>Thêm họ mới</CModalTitle>
    </CModalHeader>
    <CModalBody>
      <div>
        <CAlert v-if="showAlertModal" :color="alertTypeModal">{{ alertMessageModal }}</CAlert>
      </div>
      <CForm>
        <div class="mb-3">
          <CFormLabel for="addNameVi">Tên tiếng Việt:</CFormLabel>
          <CFormInput id="addNameVi" v-model="newHo.name" />
        </div>
        <div class="mb-3">
          <CFormLabel for="addNameLatinh">Tên Latinh:</CFormLabel>
          <CFormInput id="addNameLatinh" v-model="newHo.nameLatinh" required />
        </div>
        <div class="mb-3">
          <CFormLabel for="addBo">Bộ:</CFormLabel>
          <CFormSelect id="addBo" v-model="newHo.idBo" :options="danhSachBo" required />
        </div>
        <div class="mb-3">
          <CFormLabel>Trạng thái:</CFormLabel>
          <CFormCheck id="addStatusOn" type="radio" label="Bật" name="addStatus" :checked="newHo.status"
            @change="newHo.status = true" />
          <CFormCheck id="addStatusOff" type="radio" label="Tắt" name="addStatus" :checked="!newHo.status"
            @change="newHo.status = false" />
        </div>
      </CForm>
    </CModalBody>
    <CModalFooter>
      <CButton color="secondary" @click="closeAddModal">Hủy</CButton>
      <CButton color="primary" @click="addNewHo">Thêm mới</CButton>
    </CModalFooter>
  </CModal>
</template>
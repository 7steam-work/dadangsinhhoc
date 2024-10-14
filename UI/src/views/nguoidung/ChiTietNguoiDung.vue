<script setup>
import { CIcon } from '@coreui/icons-vue'

</script>

<template>
    <CCol :xs="12">
        <CCard class="mb-4">
            <CCardBody>
                <CTable striped>
                    <CTableBody>
                        <CTableRow>
                            <CTableHeaderCell scope="row">Họ tên:</CTableHeaderCell>
                            <CTableDataCell>Mark</CTableDataCell>
                        </CTableRow>
                        <CTableRow>
                            <CTableHeaderCell scope="row">Email:</CTableHeaderCell>
                            <CTableDataCell>Jacob@gmail.com</CTableDataCell>
                        </CTableRow>
                        <CTableRow>
                            <CTableHeaderCell scope="row">Số điện thoại:</CTableHeaderCell>
                            <CTableDataCell>099.999.9999</CTableDataCell>
                        </CTableRow>
                        <CTableRow>
                            <CTableHeaderCell scope="row">Giới tính:</CTableHeaderCell>
                            <CTableDataCell>Nam</CTableDataCell>
                        </CTableRow>
                        <CTableRow>
                            <CTableHeaderCell scope="row">Ngày sinh:</CTableHeaderCell>
                            <CTableDataCell>2002/03/20</CTableDataCell>
                        </CTableRow>
                        <CTableRow>
                            <CTableHeaderCell scope="row">Địa chỉ:</CTableHeaderCell>
                            <CTableDataCell>Hà Nội</CTableDataCell>
                        </CTableRow>
                        <CTableRow>
                            <CTableHeaderCell scope="row">Ngày đăng ký:</CTableHeaderCell>
                            <CTableDataCell>2024/08/13</CTableDataCell>
                        </CTableRow>
                        <CTableRow>
                            <CTableHeaderCell scope="row">Ngày cập nhật:</CTableHeaderCell>
                            <CTableDataCell>2024/09/02</CTableDataCell>
                        </CTableRow>
                        <CTableRow>
                            <CTableHeaderCell scope="row">Lần cuối đăng nhập:</CTableHeaderCell>
                            <CTableDataCell>2024/09/03</CTableDataCell>
                        </CTableRow>
                        <CTableRow>
                            <CTableHeaderCell scope="row">Trạng thái:</CTableHeaderCell>
                            <CTableDataCell>Bật</CTableDataCell>
                        </CTableRow>
                        <CTableRow>
                            <CTableHeaderCell scope="row">Nhóm người dùng:</CTableHeaderCell>
                            <CTableDataCell>Admin</CTableDataCell>
                        </CTableRow>
                    </CTableBody>
                </CTable>
                <div class="d-flex justify-content-left mb-3">
                    <CButton variant="outline" shape="rounded-0" color="success" class="me-2"
                        @click="chuyenDenTrangChinhSua">
                        <CIcon class="me-2" :icon="cilPencil" />Chỉnh sửa
                    </CButton>
                    <CButton variant="outline" shape="rounded-0" color="danger" @click="openDeleteModal">
                        <CIcon class="me-2" :icon="cilTrash" />Xóa
                    </CButton>
                </div>
            </CCardBody>
        </CCard>
    </CCol>

    <CModal :visible="showModal" @close="closeModal">
        <CModalHeader>
            <CModalTitle>Xác nhận xóa</CModalTitle>
        </CModalHeader>
        <CModalBody>Bạn có chắc chắn muốn xóa người dùng này không?</CModalBody>
        <CModalFooter>
            <CButton color="secondary" @click="closeModal">Hủy</CButton>
            <CButton color="danger" @click="deleteUser">Xóa</CButton>
        </CModalFooter>
    </CModal>
</template>

<script>
import { cilPencil, cilTrash } from '@coreui/icons';
import { CModal, CModalHeader, CModalTitle, CModalBody, CModalFooter, CButton } from '@coreui/vue';

export default {
  data() {
    return {
      users: [], 
      showModal: false,
      userIdToDelete: null,
      cilPencil,
      cilTrash
    };
  },
  mounted() {
    this.fetchUsers();
  },
  methods: {
    chuyenDenTrangChinhSua() {
      // Lấy ID người dùng từ đâu đó (ví dụ: từ URL hoặc Vuex store)
    //   const userId = ...; // Thay thế ... bằng cách lấy ID người dùng
    //   this.$router.push({ name: 'Chỉnh sửa người dùng', params: { id: userId } });
    },
    openDeleteModal() {
      // Lấy ID người dùng từ đâu đó (ví dụ: từ URL hoặc Vuex store)
    //   const userId = ...; // Thay thế ... bằng cách lấy ID người dùng
    //   this.userIdToDelete = userId;
    //   this.showModal = true;
    },
    closeModal() {
      this.showModal = false;
      this.userIdToDelete = null;
    },
    async deleteUser() {
      try {
        const response = await fetch(`/api/users/${this.userIdToDelete}`, { 
          method: 'DELETE'
        });

        if (response.ok) {
          // Xóa thành công, chuyển hướng về danh sách người dùng
          this.$router.push({ name: 'Danh sách người dùng' });
        } else {
          const errorData = await response.json();
          console.error('Lỗi khi xóa người dùng:', errorData.message || 'Đã có lỗi xảy ra');
          // Hiển thị thông báo lỗi cho người dùng
        }
      } catch (error) {
        console.error('Lỗi khi xóa người dùng:', error);
        // Hiển thị thông báo lỗi cho người dùng
      } finally {
        this.closeModal();
      }
    }
  }
};
</script>
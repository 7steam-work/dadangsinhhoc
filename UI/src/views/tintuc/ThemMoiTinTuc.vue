<script setup>
import CKEditor from '@/components/CKEditor.vue'; // Import component CKEditor
</script>

<template>
    <div class="d-flex justify-content-center mb-2">
        <div class="">
            <CCard>
                <CCardBody>
                    <CForm @submit.prevent="themTinTuc">
                        <!-- Tiêu đề -->
                        <div class="mb-3">
                            <CFormLabel for="title">Tiêu đề:</CFormLabel>
                            <CFormInput id="tiltle" v-model="tinTuc.hoTen" required />
                        </div>

                        <!-- Hình ảnh -->
                        <div class="form-group mb-3">
                            <label class="control-label" for="image">Hình ảnh:</label>
                            <CFormInput type="file" id="image" multiple v-model="tinTuc.image" />
                        </div>

                        <!-- Rừng phòng hộ -->
                        <div class="mb-3">
                            <CFormLabel for="rPH">Rừng phòng hộ:</CFormLabel>
                            <CFormSelect id="rPH" v-model="tinTuc.rPH">
                                <option value="0">Nam Giang</option>
                                <option value="1">Mường Tè</option>
                            </CFormSelect>
                        </div>

                        <!-- Trạng thái -->
                        <div class="mb-3">
                            <CFormLabel>Trạng thái:</CFormLabel>
                            <div>
                                <CFormCheck inline :checked="tinTuc.trangThai === 'Bật'" label="Bật" type="radio"
                                    value="Bật" v-model="tinTuc.trangThai" />
                                <CFormCheck inline :checked="tinTuc.trangThai === 'Tắt'" label="Tắt" type="radio"
                                    value="Tắt" v-model="tinTuc.trangThai" />
                            </div>
                        </div>

                        <!-- Nội dung -->
                        <div class="mb-3">
                            <CFormLabel>Nội dung:</CFormLabel>
                            <CKEditor class="w-100" v-model="tinTuc.content" />
                        </div>

                        <!-- Buttons -->
                        <div class="d-flex justify-content-center">
                            <CButton class="me-2" color="primary" type="submit">Thêm tin tức</CButton>
                            <CButton color="secondary" type="reset">Hủy</CButton>
                        </div>
                    </CForm>
                </CCardBody>
            </CCard>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            tinTuc: {
                title: '',
                image: [],
                rPH: null,
                trangThai: 'Bật',
                content: ''
            },
        };
    },
    methods: {
        themTinTuc() {
            // Gửi dữ liệu người dùng lên API để thêm mới
            console.log('Tin tức mới:', this.tinTuc);
            // API call sẽ được thực hiện ở đây
        }
    }
};
</script>
<script setup>
import CKEditor from '@/components/CKEditor.vue'; // Import component CKEditor
</script>

<template>
    <div class="d-flex justify-content-center mb-2">
        <CCard class="shadow">
            <CCardBody>
                <CForm>
                    <div class="row">
                        <!-- Cột bên trái -->
                        <div class="col-md-6">
                            <!-- Tiêu đề -->
                        <div class="mb-3">
                            <CFormLabel for="title">Tiêu đề:</CFormLabel>
                            <CFormInput id="tiltle" v-model="tinTuc.hoTen" required />
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
                        </div>

                        <!-- Cột bên phải -->
                        <div class="col-md-6">
                            <div class="mb-3">
                                <label class="control-label mb-2" style="display:block" for="image">Hình ảnh:</label>
                                <div class="row" style="max-height: 250px; overflow-y: auto;">
                                    <div v-for="(imageUrl, index) in tinTuc.image" :key="index" class="col-md-6 mb-2">
                                        <div class="d-flex flex-column align-items-center">
                                            <img :src="imageUrl" alt="" class="img-thumbnail mb-2"
                                                style="height: 100px; width: 100px;">
                                            <CFormCheck :id="'deleteImage' + index" v-model="imagesToDelete"
                                                :value="imageUrl" label="Xóa ảnh" />
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="mb-3">
                                <!-- <label class="control-label" for="hinhAnh">Hình ảnh:</label> -->
                                <!-- <CFormInput type="file" id="hinhAnh" multiple v-model="tinTuc.hinhAnh" /> -->
                                <CFormInput type="file" id="hinhAnh" multiple @change="onFileChange" />
                            </div>
                        </div>
                    </div>

                    <div class="form-group text-center">
                        <CButton color="primary" type="submit">Lưu</CButton>
                        <CButton color="secondary" class="ms-2">Hủy</CButton>
                    </div>
                </CForm>
            </CCardBody>
        </CCard>
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
            hinhAnhMoi: null, // Dữ liệu hình ảnh mới được chọn
            imagesToDelete: [], // Mảng chứa các URL hình ảnh cần xóa
        };
    },
    methods: {
        methods: {
        async chinhSuaLoai() {
            // ... (Xử lý lưu thông tin tin tức, bao gồm cả việc xóa hình ảnh và thêm hình ảnh mới)
        },
        onFileChange(event) {
            const files = event.target.files;
            this.loai.hinhAnh = []; // Xóa dữ liệu cũ
            for (let i = 0; i < files.length; i++) {
                const file = files[i];
                // Bạn có thể thực hiện thao tác với các file như upload hoặc lưu trữ
                this.loai.hinhAnh.push(file);
            }
        }
    }
    }
};
</script>
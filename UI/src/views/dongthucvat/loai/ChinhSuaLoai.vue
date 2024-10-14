<template>
    <div class="d-flex justify-content-center mb-2">
        <CCard class="shadow">
            <CCardBody>
                <CForm>
                    <div class="row">
                        <!-- Cột bên trái -->
                        <div class="col-md-6">
                            <div class="form-group mb-3">
                                <label class="control-label" for="tenTiengViet">Tên tiếng Việt:</label>
                                <CFormInput id="tenTiengViet" v-model="loai.tenTiengViet" required />
                                <CFormFeedback invalid>Vui lòng nhập tên tiếng Việt</CFormFeedback>
                            </div>

                            <div class="form-group mb-3">
                                <label class="control-label" for="tenLatinh">Tên Latinh:</label>
                                <CFormInput id="tenLatinh" v-model="loai.tenLatinh" required />
                                <CFormFeedback invalid>Vui lòng nhập tên Latinh</CFormFeedback>
                            </div>

                            <div class="form-group mb-3">
                                <label class="control-label" for="ho">Họ:</label>
                                <CFormSelect id="ho" v-model="loai.ho" :options="danhSachHo" required />
                                <CFormFeedback invalid>Vui lòng chọn họ</CFormFeedback>
                            </div>

                            <div class="form-group mb-3">
                                <label class="control-label" for="rungPhongHo">Rừng phòng hộ:</label>
                                <CFormSelect id="rungPhongHo" v-model="loai.rungPhongHo" :options="danhSachRungPhongHo"
                                    required />
                                <CFormFeedback invalid>Vui lòng chọn rừng phòng hộ</CFormFeedback>
                            </div>

                            <div class="form-group mb-3">
                                <label class="control-label" for="giaTriSuDung">Giá trị sử dụng:</label>
                                <textarea id="giaTriSuDung" v-model="loai.giaTriSuDung" class="form-control"
                                    rows="4"></textarea>
                            </div>

                            <div class="form-group mb-3">
                                <label class="control-label" for="phanBo">Phân bố:</label>
                                <CFormInput id="phanBo" v-model="loai.phanBo" />
                            </div>

                            <div class="form-group mb-3">
                                <label class="control-label" for="nguonTaiLieu">Nguồn tài liệu:</label>
                                <CFormInput id="nguonTaiLieu" v-model="loai.nguonTaiLieu" />
                            </div>

                            <div class="form-group mb-3">
                                <label class="control-label" for="dacDiemHinhThaiSinhThai">Đặc điểm hình thái sinh
                                    thái:</label>
                                <CKEditor v-model="loai.dacDiemHinhThaiSinhThai" />
                            </div>
                        </div>

                        <!-- Cột bên phải -->
                        <div class="col-md-6">
                            <div class="row">
                                <!-- Cột 1 của mức độ bảo tồn -->
                                <div class="col-md-6">
                                    <div class="form-group mb-3">
                                        <label class="control-label" for="mucDoBaoTonIUCN">Mức độ bảo tồn IUCN:</label>
                                        <CFormSelect id="mucDoBaoTonIUCN" v-model="loai.mucDoBaoTonIUCN"
                                            :options="danhSachMucDoBaoTonIUCN" required />
                                        <CFormFeedback invalid>Vui lòng chọn mức độ bảo tồn IUCN</CFormFeedback>
                                    </div>

                                    <div class="form-group mb-3">
                                        <label class="control-label" for="mucDoBaoTonSDVN">Mức độ bảo tồn SDVN:</label>
                                        <CFormSelect id="mucDoBaoTonSDVN" v-model="loai.mucDoBaoTonSDVN"
                                            :options="danhSachMucDoBaoTonSDVN" required />
                                        <CFormFeedback invalid>Vui lòng chọn mức độ bảo tồn SDVN</CFormFeedback>
                                    </div>
                                </div>

                                <!-- Cột 2 của mức độ bảo tồn -->
                                <div class="col-md-6">
                                    <div class="form-group mb-3">
                                        <label class="control-label" for="mucDoBaoTonNDCP">Mức độ bảo tồn NDCP:</label>
                                        <CFormSelect id="mucDoBaoTonNDCP" v-model="loai.mucDoBaoTonNDCP"
                                            :options="danhSachMucDoBaoTonNDCP" required />
                                        <CFormFeedback invalid>Vui lòng chọn mức độ bảo tồn NDCP</CFormFeedback>
                                    </div>

                                    <div class="form-group mb-3">
                                        <label class="control-label" for="mucDoBaoTonND64CP">Mức độ bảo tồn
                                            ND64CP:</label>
                                        <CFormSelect id="mucDoBaoTonND64CP" v-model="loai.mucDoBaoTonND64CP"
                                            :options="danhSachMucDoBaoTonND64CP" required />
                                        <CFormFeedback invalid>Vui lòng chọn mức độ bảo tồn ND64CP</CFormFeedback>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group mb-3">
                                <label class="control-label mb-2" style="display:block" for="hinhAnh">Hình ảnh:</label>
                                <div class="row" style="max-height: 250px; overflow-y: auto;">
                                    <div v-for="(imageUrl, index) in loai.hinhAnh" :key="index" class="col-md-6 mb-2">
                                        <div class="d-flex flex-column align-items-center">
                                            <img :src="imageUrl" alt="Hình ảnh loài" class="img-thumbnail mb-2"
                                                style="height: 100px; width: 100px;">
                                            <CFormCheck :id="'deleteImage' + index" v-model="imagesToDelete"
                                                :value="imageUrl" label="Xóa ảnh" />
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group mb-3">
                                <!-- <label class="control-label" for="hinhAnh">Hình ảnh:</label> -->
                                <!-- <CFormInput type="file" id="hinhAnh" multiple v-model="loai.hinhAnh" /> -->
                                <CFormInput type="file" id="hinhAnh" multiple @change="onFileChange" />
                            </div>

                            <div class="form-group mb-3">
                                <label class="control-label">Trạng thái:</label>
                                <div>
                                    <CFormCheck inline :checked="loai.trangThai === 'Bật'" label="Bật" type="radio"
                                        value="Bật" v-model="loai.trangThai" />
                                    <CFormCheck inline :checked="loai.trangThai === 'Tắt'" label="Tắt" type="radio"
                                        value="Tắt" v-model="loai.trangThai" />
                                </div>
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
import CKEditor from '@/components/CKEditor.vue';

export default {
    components: {
        CKEditor
    },
    data() {
        return {
            loai: {
                tenTiengViet: '',
                tenLatinh: '',
                ho: null,
                rungPhongHo: null,
                dacDiemHinhThaiSinhThai: '',
                giaTriSuDung: '',
                phanBo: '',
                nguonTaiLieu: '',
                hinhAnh: [], // Mảng chứa các URL hình ảnh
                mucDoBaoTonIUCN: null,
                mucDoBaoTonSDVN: null,
                mucDoBaoTonNDCP: null,
                mucDoBaoTonND64CP: null,
                trangThai: 'Bật'
            },
            hinhAnhMoi: null, // Dữ liệu hình ảnh mới được chọn
            imagesToDelete: [], // Mảng chứa các URL hình ảnh cần xóa
        
            danhSachHo: [
                'Họ Mèo (Felidae)',
                'Họ Chó (Canidae)',
                'Họ Gấu (Ursidae)'
            ],
            danhSachRungPhongHo: [
                'Vườn Quốc gia Cát Tiên',
                'Rừng phòng hộ Nam Giang',
                'Rừng phòng hộ Mường Tè'
            ],
            danhSachMucDoBaoTonIUCN: [
                'Nguy cấp (Critically Endangered - CR)',
                'Sắp nguy cấp (Endangered - EN)',
                'Sẽ nguy cấp (Vulnerable - VU)'
            ],
            danhSachMucDoBaoTonSDVN: [
                'Nhóm IA - Đặc biệt nghiêm ngặt',
                'Nhóm IB - Nghiêm ngặt',
                'Nhóm IIA - Nguy cấp'
            ],
            danhSachMucDoBaoTonNDCP: [
                'Quyết định 84/2003/QĐ-TTg',
                'Quyết định khác 1',
                'Quyết định khác 2'
            ],
            danhSachMucDoBaoTonND64CP: [
                'Nghị định 64/2003/NĐ-CP',
                'Nghị định khác 1',
                'Nghị định khác 2'
            ],
        }
    },
    // mounted() {
    //     // ... (Lấy dữ liệu loài từ API hoặc Vuex)
    // },
    mounted() {
        // Gán dữ liệu giả lập ngay khi trang được mount
        this.loai = {
            tenTiengViet: 'Hổ Đông Dương',
            tenLatinh: 'Panthera tigris corbetti',
            ho: 'Họ Mèo (Felidae)',
            rungPhongHo: 'Vườn Quốc gia Cát Tiên',
            dacDiemHinhThaiSinhThai: 'Thân dài, chân khỏe, có vằn đen trên nền lông vàng hung...',
            giaTriSuDung: 'Nghiên cứu khoa học. Du lịch sinh thái...',
            phanBo: 'Miền Trung và Tây Nguyên của Việt Nam...',
            nguonTaiLieu: 'Sách Động vật rừng Việt Nam...',
            hinhAnh: [
                'https://cdn.tuoitre.vn/2019/4/10/photo-1-1554812303220344622.jpg',
                'https://static.dangtin.com/2021/03/23/hinh-anh-ho-dong-duong-1616432402142.jpg'
            ],
            mucDoBaoTonIUCN: 'Nguy cấp (Critically Endangered - CR)',
            mucDoBaoTonSDVN: 'Nhóm IA - Đặc biệt nghiêm ngặt',
            mucDoBaoTonNDCP: 'Quyết định 84/2003/QĐ-TTg',
            mucDoBaoTonND64CP: 'Nghị định 64/2003/NĐ-CP',
            trangThai: 'Bật'
        };
    },
    methods: {
        async chinhSuaLoai() {
            // ... (Xử lý lưu thông tin loài, bao gồm cả việc xóa hình ảnh và thêm hình ảnh mới)
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
</script>

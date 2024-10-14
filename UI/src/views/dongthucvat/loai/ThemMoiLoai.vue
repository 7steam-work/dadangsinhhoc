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
                                <label class="control-label" for="hinhAnh">Hình ảnh:</label>
                                <CFormInput type="file" id="hinhAnh" multiple v-model="loai.hinhAnh" />
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
                hinhAnh: null,
                mucDoBaoTonIUCN: null,
                mucDoBaoTonSDVN: null,
                mucDoBaoTonNDCP: null,
                mucDoBaoTonND64CP: null,
                trangThai: 'Bật'
            },
        }
    }
}
</script>

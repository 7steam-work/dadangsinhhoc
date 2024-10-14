<template>
  <CCard class="mb-4">
    <CCardBody>
      <h1 class="text-center mt-2">Thống kê thông tin loài</h1>
      <CForm method="post" class="mt-4">
        <div class="form-validation col-lg-9 col-md-8 col-12 m-auto">
          <CRow class="mt-3 justify-content-center">
            <CFormLabel class="col-lg-2 col-form-label" for="rungPhongHo" @submit.prevent="xuatFileExcel">
              Đơn vị: <span class="text-danger">*</span>
            </CFormLabel>
            <div class="col-lg-6 d-flex align-items-center justify-content-between">
              <CFormCheck inline type="radio" name="typeNationPark" value="NationParkNamGiang" v-model="rungPhongHo"
                label="RPH Nam Giang" :checked="rungPhongHo === 'NationParkNamGiang'" />
              <CFormCheck inline type="radio" name="typeNationPark" value="NationParkMuongTe" v-model="rungPhongHo"
                label="RPH Mường Tè" />
            </div>
          </CRow>

          <CRow class="mt-3 justify-content-center">
            <CFormLabel class="col-lg-2 col-form-label" for="loai">
              Loài: <span class="text-danger">*</span>
            </CFormLabel>
            <div class="col-lg-6 d-flex align-items-center justify-content-between">
              <CFormCheck inline type="radio" name="TypeSpceies" value="-1" v-model="loai" label="Tất cả"
                :checked="loai === '-1'" />
              <CFormCheck inline type="radio" name="TypeSpceies" value="1" v-model="loai" label="Động vật" />
              <CFormCheck inline type="radio" name="TypeSpceies" value="0" v-model="loai" label="Thực vật" />
            </div>
          </CRow>

          <CRow class="mt-3 justify-content-center">
            <CFormLabel class="col-lg-2 col-form-label" for="tenFile">
              Tên file: <span class="text-danger">*</span>
            </CFormLabel>
            <div class="col-lg-6 d-flex align-items-center ">
              <CFormInput type="text" id="tenFile" name="FileName" v-model="tenFile" placeholder="Nhập tên file..."
                required />
            </div>
          </CRow>

          <CRow class="mt-3 justify-content-center">
            <CFormLabel class="col-lg-2 col-form-label">
              Thời gian: <span class="text-danger">*</span>
            </CFormLabel>
            <div class="col-lg-6 d-flex align-items-center ">
              <input type="date" id="thoiGianBatDau" v-model="thoiGianBatDau" class="form-control" format="dd/MM/yyyy">
              <div class="ms-1 me-1">-</div>
              <input type="date" id="thoiGianBatDau" v-model="thoiGianBatDau" class="form-control" format="dd/MM/yyyy">
            </div>
          </CRow>

          <CRow class="mt-1 justify-content-center">
            <div class="col-lg-8 ml-auto text-center">
              <CButton color="primary" type="submit" class="mt-3">Xuất file Excel</CButton>
            </div>
          </CRow>
        </div>
      </CForm>
    </CCardBody>
  </CCard>
</template>

<script>
export default {

  data() {
    return {
      rungPhongHo: 'NationParkNamGiang',
      loai: '-1',
      tenFile: '',
      thoiGianBatDau: null,
      thoiGianKetThuc: null,
    };
  },
  methods: {
    xuatFileExcel() {
      // Xử lý logic xuất file Excel ở đây
      console.log('Xuất file Excel với dữ liệu:', {
        rungPhongHo: this.rungPhongHo,
        loai: this.loai,
        tenFile: this.tenFile,
        thoiGianBatDau: this.thoiGianBatDau,
        thoiGianKetThuc: this.thoiGianKetThuc
      });
    }
  }
};
</script>
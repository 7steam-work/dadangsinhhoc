<script setup>
import { CIcon } from '@coreui/icons-vue'
import { cilMobile } from '@coreui/icons'
import { cilLocationPin } from '@coreui/icons'
import { cilHome } from '@coreui/icons'
</script>

<template>
  <header class="header">
    <div class="container">
      <div class="row">
        <div class="col-1">
          <img src="http://ddshnamgiangmuongte.vnuforest.com/images/logo.png" alt="" width="70" height="70">
        </div>
        <div class="col-6 title-header">TRANG QUẢN LÝ ĐA DẠNG SINH HỌC RỪNG PHÒNG HỘ NAM GIANG VÀ MƯỜNG TÈ</div>
        <div class="col-2">
          <div class="mt-2 d-flex">
            <CIcon style="color: white; margin-right: 5px;" :icon="cilMobile" size="lg" />
            <div class="">
              <p style="color: #ffc107; margin: 0; font-size: 10px;">HOTLINE</p>
              <p class="m-0" style="color: #fff; margin: 0; font-size: 13px;">0243.2032002</p>
            </div>
          </div>
        </div>
        <div class="col-3">
          <div class="mt-2 d-flex">
            <CIcon style="color: white; margin-right: 5px;" :icon="cilLocationPin" size="xl" />
            <div class="">
              <p style="color: #ffc107; margin: 0; font-size: 10px;">ĐỊA CHỈ</p>
              <p class="m-0" style="color: #fff; margin: 0; font-size: 13px;">xã Cà Dy, huyện Nam Giang,
                tỉnh Quảng Nam</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </header>
  <div class="banner">
    <div class="container">
      <nav class="navbar navbar-expand-lg navbar-light bg-light rounded-pill py-4">
        <div class="container-fluid ">
          <div class="collapse navbar-collapse justify-content-center" id="navbarNav">
            <ul class="navbar-nav">
              <li class="nav-item">
                <router-link to="/homepage" class="nav-link active" aria-current="page">
                  <CIcon style="font-size: large;" :icon="cilHome" />
                </router-link>
              </li>
              <li class="nav-item">
                <router-link to="/info" class="nav-link">Giới thiệu</router-link>
              </li>
              <li class="nav-item">
                <router-link to="/csdl/dong-vat" class="nav-link">CSDL Động vật</router-link>
              </li>
              <li class="nav-item">
                <router-link to="/csdl/thuc-vat" class="nav-link">CSDL Thực vật</router-link>
              </li>
              <li class="nav-item">
                <router-link to="/news" class="nav-link">Tin tức</router-link>
              </li>
              <li class="nav-item">
                <router-link to="/contact" class="nav-link">Liên hệ</router-link>
              </li>
              <li class="nav-item">
                <router-link to="/login" class="nav-link">Đăng nhập</router-link>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    </div>
  </div>
</template>

<script>
import 'https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js';
</script>

<style scoped>
@import 'https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css';

a {
  text-decoration: none;
}

ul {
  list-style-type: none;
}

.nav-justified .nav-item .nav-link {
  width: 80%;
}

.nav-pills .nav-link {
  border: 1px solid #dee2e6 !important;
  color: black;
  font-size: 15px;
}

/* header */
.header {
  background-color: #007F18;
  padding: 18px 0 12px;
}

.header .title-header {
  height: 20px;
  color: #ffc107;
  font-size: 20px;
  text-align: center;
}

.header p {
  margin: 0px;
  line-height: 1.5;
}

/* banner */
.banner {
  background-image: url(http://ddshnamgiangmuongte.vnuforest.com/images/cover.png);
  background-repeat: no-repeat;
  /* Do not repeat the image */
  background-size: cover;
  /* Resize the background image to cover the entire container */
  padding-bottom: 125px;
}

.banner .nav-item .nav-link {
  font-size: 13px;
  color: #333;
  text-transform: uppercase;
  font-weight: 600;
  padding: 0 35px;
  display: block;
  height: 16px;
  line-height: 16px;
  border-left: dashed 1px #008E1B;
  transition: all 0.2s;
}

.banner .nav-item .nav-link.active {
  font-size: 13px;
  color: green;
  text-transform: uppercase;
  font-weight: 600;
  padding: 0 45px;
  display: block;
  height: 16px;
  line-height: 16px;
  border-left: none;
  transition: all 0.2s;
}
</style>
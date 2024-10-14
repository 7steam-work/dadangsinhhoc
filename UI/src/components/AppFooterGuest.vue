<template>
  <footer class="footer p-5">
    <div class="container px-5">
      <div class="row">
        <div class="col-3">
          <ul>
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
          </ul>
        </div>
        <div class="col-6">
          <ul style="line-height: 40px;">
            <li>TRANG QUẢN LÝ ĐA DẠNG SINH HỌC RỪNG PHÒNG HỘ NAM GIANG VÀ MƯỜNG TÈ</li>
            <li>Địa chỉ: Xã Cà Dy, huyện Nam Giang, tỉnh Quảng Nam</li>
            <li>Hotline: 0243.2032002</li>
            <li>Email: namgiang-muongte@rph.com.vn</li>
          </ul>
        </div>
        <div class="col-3">
          <img src="http://ddshnamgiangmuongte.vnuforest.com/images/logo.png" alt="" width="180" height="180">
        </div>
      </div>
    </div>
  </footer>
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

/* footer */
.footer {
  background-color: #083E12;
  color: white;
}

.footer ul li {
  list-style-type: none;
}

.footer ul li a {
  line-height: 40px;
  color: white;
  transition: all 0.2s;
  display: block;
  font-size: 13px;
}

.footer ul li a:hover {
  text-indent: 5px;
  color: #FFC700;
}
</style>
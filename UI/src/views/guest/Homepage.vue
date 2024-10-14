<script setup>
import AppFooter from '@/components/AppFooterGuest.vue'
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
    <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active"
                aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"
                aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"
                aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner">
            <div class="banner" style=" position: absolute; z-index: 50; margin: 0 auto; left: 50%; transform: translate(-50%); min-width: 80%;">
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
            <div class="carousel-item active">
                <img src="http://ddshnamgiangmuongte.vnuforest.com/banner/917cc4ed-6695-403d-bbb4-11be1b32b7e9.JPG" class="d-block w-100 h-100"
                    alt="...">
            </div>
            <div class="carousel-item">
                <img src="http://ddshnamgiangmuongte.vnuforest.com/banner/682cfa83-b2e8-4d57-9e2b-5c9e0f0fa9e8.JPG" class="d-block w-100 h-100"
                    alt="...">
            </div>
            <div class="carousel-item">
                <img src="http://ddshnamgiangmuongte.vnuforest.com/banner/afb58d2f-904d-481a-b242-045308a23741.JPG" class="d-block w-100 h-100"
                    alt="...">
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators"
            data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators"
            data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>

    <div class="content">
        <div class="container">
            <div class="list-animal mx-5">
                <h3>Danh sách loài - Số lượng: 489</h3>
                <div class="row">
                    <div class="col-md-6 col-xs-12 animal-item">
                        <div class="row">
                            <div class="col-2">
                                <div class="stt">01.</div>
                            </div>
                            <div class="col-4">
                                <img src="http://ddshnamgiangmuongte.vnuforest.com/user-content/1842-1.jpg" alt=""
                                    class="h-100 w-100">
                            </div>
                            <div class="col-6">
                                <div class="noidung">
                                    <h5>Loài: Ếch cây trung bộ</h5>
                                    <p>Họ: Ếch cây</p>
                                    <p>BỘ KHÔNG ĐUÔI</p>
                                    <p>LỚP LƯỠNG CƯ</p>
                                    <p>Ngành có dây sống</p>
                                    <router-link to="/" style="color: green; font-weight: bold;">CHI TIẾT</router-link>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-xs-12 animal-item">
                        <div class="row">
                            <div class="col-2">
                                <div class="stt">01.</div>
                            </div>
                            <div class="col-4">
                                <img src="http://ddshnamgiangmuongte.vnuforest.com/user-content/1842-1.jpg" alt=""
                                    class="h-100 w-100">
                            </div>
                            <div class="col-6">
                                <div class="noidung">
                                    <h5>Loài: Ếch cây trung bộ</h5>
                                    <p>Họ: Ếch cây</p>
                                    <p>BỘ KHÔNG ĐUÔI</p>
                                    <p>LỚP LƯỠNG CƯ</p>
                                    <p>Ngành có dây sống</p>
                                    <router-link to="/" style="color: green; font-weight: bold;">CHI TIẾT</router-link>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-xs-12 animal-item">
                        <div class="row">
                            <div class="col-2">
                                <div class="stt">01.</div>
                            </div>
                            <div class="col-4">
                                <img src="http://ddshnamgiangmuongte.vnuforest.com/user-content/1842-1.jpg" alt=""
                                    class="h-100 w-100">
                            </div>
                            <div class="col-6">
                                <div class="noidung">
                                    <h5>Loài: Ếch cây trung bộ</h5>
                                    <p>Họ: Ếch cây</p>
                                    <p>BỘ KHÔNG ĐUÔI</p>
                                    <p>LỚP LƯỠNG CƯ</p>
                                    <p>Ngành có dây sống</p>
                                    <router-link to="/" style="color: green; font-weight: bold;">CHI TIẾT</router-link>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-xs-12 animal-item">
                        <div class="row">
                            <div class="col-2">
                                <div class="stt">01.</div>
                            </div>
                            <div class="col-4">
                                <img src="http://ddshnamgiangmuongte.vnuforest.com/user-content/1842-1.jpg" alt=""
                                    class="h-100 w-100">
                            </div>
                            <div class="col-6">
                                <div class="noidung">
                                    <h5>Loài: Ếch cây trung bộ</h5>
                                    <p>Họ: Ếch cây</p>
                                    <p>BỘ KHÔNG ĐUÔI</p>
                                    <p>LỚP LƯỠNG CƯ</p>
                                    <p>Ngành có dây sống</p>
                                    <router-link to="/" style="color: green; font-weight: bold;">CHI TIẾT</router-link>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="mt-4">
                <nav aria-label="Page navigation example">
                    <ul class="pagination justify-content-center">
                        <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                        <li class="page-item active"><a class="page-link " href="#">1</a></li>
                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                        <li class="page-item"><a class="page-link" href="#">Next</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>

    <AppFooter />
</template>

<script>
import 'https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js';
export default {
};
</script>

<style scoped>
@import 'https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css';
img{
    max-height: 300px;
    object-fit: cover;
    width: 100%;
    height: auto;
}

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
    /* background-image: url(http://ddshnamgiangmuongte.vnuforest.com/images/cover.png);
    background-repeat: no-repeat;
    background-size: cover; */
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


/* content */
.content .container {
    max-width: 1100px;
}

.list-animal {
    margin-top: 20px;
}

.list-animal .animal-item {
    margin-top: 20px;
    margin-bottom: 10px;
}

.list-animal .animal-item .stt {
    float: right;
    font-size: 40px;
    color: #CECECE;
}

.list-animal .noidung {
    font-size: 12px;
}

.list-animal .noidung p,
.list-animal .noidung a {
    margin-bottom: 5px;
}
</style>
<script setup>
import AppFooter from '@/components/AppFooterGuest.vue'
import AppHeader from '@/components/AppHeaderGuest.vue'
</script>

<template>
    <AppHeader />

    <div class="thongtinloai">
        <div class="container">
            <h1>Thông tin loài</h1>
            <div class="row">
                <div class="col-12">
                    <table class="table table-striped">
                        <tbody>
                            <tr>
                                <th>Tên tiếng việt:</th>
                                <td>Ếch cây trung bộ</td>
                            </tr>
                            <tr>
                                <th>Tên khoa học:</th>
                                <td>Rhacophorus annamensis Smith, 1924</td>
                            </tr>
                            <tr>
                                <th>Họ:</th>
                                <td>Họ Ếch cây</td>
                            </tr>
                            <tr>
                                <th>Bộ:</th>
                                <td>BỘ KHÔNG ĐUÔI</td>
                            </tr>
                            <tr>
                                <th>Lớp:</th>
                                <td>LỚP LƯỠNG CƯ</td>
                            </tr>
                            <tr>
                                <th>Ngành:</th>
                                <td>NGÀNH CÓ DÂY SỐNG</td>
                            </tr>
                            <tr>
                                <th>Mức độ bảo tồn IUCN:</th>
                                <td></td>
                            </tr>
                            <tr>
                                <th>Mức độ bảo tồn SDVN:</th>
                                <td></td>
                            </tr>
                            <tr>
                                <th>Mức độ bảo tồn NDCP:</th>
                                <td></td>
                            </tr>
                            <tr>
                                <th>Mức độ bảo tồn Nd64Cp:</th>
                                <td></td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <div class="col-12">
                    <div class="accordion" id="accordionPanelsStayOpenExample">
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="panelsStayOpen-headingOne">
                                <button class="accordion-button" type="button" @click="togglePanel(1)">
                                    <h4 class="m-0">Thông tin tại BQL rừng phòng hộ Nam Giang</h4>
                                </button>
                            </h2>
                            <div v-if="activePanel === 1" class="accordion-collapse">
                                <div class="accordion-body text-center">
                                    <h3>Hình ảnh</h3>
                                    <div class="popup-image d-flex justify-content-center">
                                        <div class="image-container d-flex flex-wrap justify-content-center"
                                            style="max-width: 80%;">
                                            <div class="image-item py-3" v-for="(image, index) in images" :key="index">
                                                <img :src="image" alt="" height="180px" width="180px"
                                                    @click="showImage(index)">
                                            </div>
                                        </div>
                                    </div>
                                    <h3>Tên khác</h3>
                                    <h3>Phân bố</h3>
                                    <h3>Nguồn tài liệu</h3>
                                    <h3>Đặc điểm</h3>
                                    <h3>Công dụng - Giá trị sử dụng</h3>
                                </div>
                            </div>
                        </div>
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="panelsStayOpen-headingTwo">
                                <button class="accordion-button" type="button" @click="togglePanel(2)">
                                    <h4 class="m-0">Thông tin tại BQL rừng phòng hộ Mường tè</h4>
                                </button>
                            </h2>
                            <div v-if="activePanel === 2" class="accordion-collapse">
                                <div class="accordion-body">
                                    <h3>Không có thông tin</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal for enlarged image -->
        <div v-if="isModalVisible" class="modal">
            <span class="close" @click="closeModal">&times;</span>
            <img class="modal-content" :src="modalImage">
            <div class="caption">{{ currentIndex + 1 }} of {{ images.length }}</div>
            <a class="prev" @click="changeImage(-1)">&#10094;</a>
            <a class="next" @click="changeImage(1)">&#10095;</a>
        </div>
    </div>

    <AppFooter />
</template>

<script>
import 'https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js';
export default {
    data() {
        return {
            activePanel: null,
            isModalVisible: false,
            currentIndex: 0,
            images: [
                "http://ddshnamgiangmuongte.vnuforest.com/user-content/1842-1.jpg",
                "http://ddshnamgiangmuongte.vnuforest.com/user-content/1842.jpg",
                "http://ddshnamgiangmuongte.vnuforest.com/user-content/1842-2.jpg",
                "http://ddshnamgiangmuongte.vnuforest.com/user-content/1842-3.jpg",
            ],
        };
    },
    methods: {
        togglePanel(panelNumber) {
            this.activePanel = this.activePanel === panelNumber ? null : panelNumber;
        },
        showImage(index) {
            this.isModalVisible = true;
            this.currentIndex = index;
        },
        closeModal() {
            this.isModalVisible = false;
        },
        changeImage(direction) {
            this.currentIndex += direction;
            if (this.currentIndex < 0) {
                this.currentIndex = this.images.length - 1;
            } else if (this.currentIndex >= this.images.length) {
                this.currentIndex = 0;
            }
        },
    },
};
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

.thongtinloai {
    padding-top: 40px;
    padding-bottom: 40px;
}

.thongtinloai .container {
    max-width: 1110px;
}

.image-container {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
}

.image-item {
    flex: 1 1 150px;
    /* Điều chỉnh độ rộng cơ sở của ảnh */
    max-width: 25%;
    /* Tối đa 4 ảnh trên một hàng */
    text-align: center;
    /* Căn giữa ảnh trong cột */
}

.image-item img {
    display: inline-block;
}

/* CSS cho modal */
.modal {
    display: block;
    display: none;
    /* Ẩn modal mặc định */
    position: fixed;
    z-index: 999;
    padding-top: 100px;
    /* Thay đổi padding để ảnh đủ chỗ trong màn hình */
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: auto;
    background-color: rgba(0, 0, 0, 0.9);
}

.modal-content {
    margin: auto;
    display: block;
    width: 80%;
    max-width: 700px;
    max-height: 80vh;
    /* Đảm bảo ảnh không vượt quá chiều cao màn hình */
    object-fit: contain;
    /* Điều chỉnh ảnh để phù hợp với không gian */
}

.close {
    cursor: pointer;
    position: absolute;
    top: 15px;
    right: 35px;
    color: #f1f1f1;
    font-size: 40px;
    font-weight: bold;
    transition: 0.3s;
}

.close:hover,
.close:focus {
    color: #bbb;
    text-decoration: none;
    cursor: pointer;
}

.prev,
.next {
    cursor: pointer;
    position: absolute;
    top: 50%;
    width: auto;
    padding: 16px;
    margin-top: -22px;
    color: white;
    font-weight: bold;
    font-size: 30px;
    transition: 0.6s ease;
    border-radius: 0 3px 3px 0;
    user-select: none;
}

.prev {
    left: 0;
}

.next {
    right: 0;
    border-radius: 3px 0 0 3px;
}

.prev:hover,
.next:hover {
    background-color: rgba(0, 0, 0, 0.8);
}

/* CSS cho caption */
.caption {
    position: absolute;
    bottom: 15px;
    left: 50%;
    transform: translateX(-50%);
    color: #f1f1f1;
    font-size: 18px;
    text-align: center;
    width: 100%;
    padding: 10px;
    background-color: rgba(0, 0, 0, 0.5);
}
</style>
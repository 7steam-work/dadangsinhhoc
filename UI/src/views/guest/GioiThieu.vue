<script setup>
import AppFooter from '@/components/AppFooterGuest.vue'
import AppHeader from '@/components/AppHeaderGuest.vue'
</script>

<template>
    <AppHeader />

    <div class="gioithieu">
        <div class="container">
            <ul class="nav nav-pills nav-justified mb-5 mx-5" id="myTab" role="tablist">
                <li class="nav-item" role="presentation">
                    <button class="nav-link active" id="home-tab" data-bs-toggle="tab" data-bs-target="#home"
                        type="button" role="tab" aria-controls="home" aria-selected="true">Giới thiệu chung</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile"
                        type="button" role="tab" aria-controls="profile" aria-selected="false">Giới thiệu về BQL RPH Nam
                        Giang</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="contact-tab" data-bs-toggle="tab" data-bs-target="#contact"
                        type="button" role="tab" aria-controls="contact" aria-selected="false">Giới thiệu về BQL RPH
                        Mường tè</button>
                </li>
            </ul>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                    <h1>Giới thiệu về Phần mềm cơ sở dữ liệu về các hệ sinh thái có đa dạng sinh học cao trong phạm vi
                        rừng
                        phòng hộ</h1>
                </div>
                <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                    <h1>GIỚI THIỆU VỀ BAN QUẢN LÝ RPH NAM GIANG</h1>
                </div>
                <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">
                    <h1>GIỚI THIỆU VỀ BAN QUẢN LÝ RPH MƯỜNG TÈ</h1>
                </div>
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

.gioithieu {
    padding-top: 40px;
}

.gioithieu .container {
    max-width: 1110px;
}
</style>
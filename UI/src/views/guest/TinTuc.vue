<script setup>
import AppFooter from '@/components/AppFooterGuest.vue'
import AppHeader from '@/components/AppHeaderGuest.vue'
</script>

<template>
    <AppHeader />

    <div class="dulich">
        <div class="container">
            <ul class="nav nav-pills nav-justified mb-5 mx-5" id="myTab" role="tablist">
                <li class="nav-item" role="presentation">
                    <button class="nav-link active" id="home-tab" data-bs-toggle="tab" data-bs-target="#home"
                        type="button" role="tab" aria-controls="home" aria-selected="true">Tất cả</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile"
                        type="button" role="tab" aria-controls="profile" aria-selected="false">BQL RPH Nam
                        Giang</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="contact-tab" data-bs-toggle="tab" data-bs-target="#contact"
                        type="button" role="tab" aria-controls="contact" aria-selected="false">BQL RPH Mường tè</button>
                </li>
            </ul>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                    <div class="row row-cols-2 row-cols-md-4 g-4">
                        <div class="col">
                            <div class="card">
                                <img src="http://ddshnamgiangmuongte.vnuforest.com/tours/4d098109-4166-445a-99a0-f1ac5da8a9dc.jpg"
                                    class="card-img-top" alt="...">
                                <div class="card-body">
                                    <router-link to="/">Khám phá du lịch Mường Tè (Lai Châu) với 4 địa điểm du lịch hấp
                                        dẫn nhất</router-link>
                                    <p>1/21/2024 7:56:34 AM</p>
                                </div>
                            </div>
                        </div>
                        <div class="col">
                            <div class="card">
                                <img src="http://ddshnamgiangmuongte.vnuforest.com/tours/4d098109-4166-445a-99a0-f1ac5da8a9dc.jpg"
                                    class="card-img-top" alt="...">
                                <div class="card-body">
                                    <router-link to="/">Khám phá du lịch Mường Tè (Lai Châu) với 4 địa điểm du lịch hấp
                                        dẫn nhất</router-link>
                                    <p>1/21/2024 7:56:34 AM</p>
                                </div>
                            </div>
                        </div>
                        <div class="col">
                            <div class="card">
                                <img src="http://ddshnamgiangmuongte.vnuforest.com/tours/4d098109-4166-445a-99a0-f1ac5da8a9dc.jpg"
                                    class="card-img-top" alt="...">
                                <div class="card-body">
                                    <router-link to="/">Khám phá du lịch Mường Tè (Lai Châu) với 4 địa điểm du lịch hấp
                                        dẫn nhất</router-link>
                                    <p>1/21/2024 7:56:34 AM</p>
                                </div>
                            </div>
                        </div>
                        <div class="col">
                            <div class="card">
                                <img src="http://ddshnamgiangmuongte.vnuforest.com/tours/4d098109-4166-445a-99a0-f1ac5da8a9dc.jpg"
                                    class="card-img-top" alt="...">
                                <div class="card-body">
                                    <router-link to="/">Khám phá du lịch Mường Tè (Lai Châu) với 4 địa điểm du lịch hấp
                                        dẫn nhất</router-link>
                                    <p>1/21/2024 7:56:34 AM</p>
                                </div>
                            </div>
                        </div>
                        <div class="col">
                            <div class="card">
                                <img src="http://ddshnamgiangmuongte.vnuforest.com/tours/4d098109-4166-445a-99a0-f1ac5da8a9dc.jpg"
                                    class="card-img-top" alt="...">
                                <div class="card-body">
                                    <router-link to="/">Khám phá du lịch Mường Tè (Lai Châu) với 4 địa điểm du lịch hấp
                                        dẫn nhất</router-link>
                                    <p>1/21/2024 7:56:34 AM</p>
                                </div>
                            </div>
                        </div>
                        <div class="col">
                            <div class="card">
                                <img src="http://ddshnamgiangmuongte.vnuforest.com/tours/4d098109-4166-445a-99a0-f1ac5da8a9dc.jpg"
                                    class="card-img-top" alt="...">
                                <div class="card-body">
                                    <router-link to="/">Khám phá du lịch Mường Tè (Lai Châu) với 4 địa điểm du lịch hấp
                                        dẫn
                                        nhất</router-link>
                                    <p>1/21/2024 7:56:34 AM</p>
                                </div>
                            </div>
                        </div>
                    </div>
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

.dulich {
    padding-top: 40px;
    background-color: #F3F3F9;
}

.dulich .container {
    max-width: 1110px;
}

.card .card-body a {
    display: -webkit-box;
    font-size: 16px;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    font-weight: bold;
    color: black;
    overflow: hidden;
}

.card .card-body p {
    margin-bottom: 0px;
    text-align: center;
    font-size: 14px;
}
</style>
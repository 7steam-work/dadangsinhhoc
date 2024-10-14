<script setup>
import AppFooter from '@/components/AppFooterGuest.vue'
import AppHeader from '@/components/AppHeaderGuest.vue'
</script>

<template>
    <AppHeader />

    <div class="lienhe">
        <div class="container">
            <h1 class="text-center">GỬI THÔNG TIN</h1>
            <p class="text-center">Bạn hãy điền nội dung tin nhắn vào form dưới đây và gửi cho chúng tôi. Chúng tôi sẽ
                trả lời
                bạn sau khi nhận
                được.</p>
            <div class="row">
                <div class="col-12 mb-3">
                    <div class="row">
                        <div class="col-6">
                            <label for="">Họ và tên:</label>
                            <input type="text" name="" id="" class="form-control">
                        </div>
                        <div class="col-6">
                            <label for="">Email:</label>
                            <input type="text" name="" id="" class="form-control">
                        </div>
                    </div>
                </div>
                <div class="col-12 mb-3">
                    <label for="">Điện thoại:</label>
                    <input type="text" name="" id="" class="form-control">
                </div>
                <div class="col-12 mb-3">
                    <label for="">Rừng phòng hộ:</label>
                    <select name="" id="" class="form-control">
                        <option value="">RPH Nam Giang</option>
                        <option value="">RPH Mường Tè</option>
                    </select>
                </div>
                <div class="col-12 mb-3">
                    <label for="">Nội dung:</label>
                    <textarea name="" id="" placeholder="Nhập nội dung liên hệ" rows="6" class="form-control"
                        style="background-color: #F7F7F7;"></textarea>
                </div>
                <div class="col-12 mb-5">
                    <button class="btn btn-dark w-100">Gửi tin nhắn</button>
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

.lienhe {
    padding-top: 40px;
}

.lienhe .container {
    max-width: 1110px;
}
</style>
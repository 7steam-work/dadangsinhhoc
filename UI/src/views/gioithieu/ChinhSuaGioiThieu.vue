<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import CKEditor from '@/components/CKEditor.vue'; // Import component CKEditor

// Dữ liệu cho phần giới thiệu (ban đầu được tải từ API)
const banner = ref({
    text: '',
});

const router = useRouter();

// Hàm để lấy dữ liệu banner từ API
const fetchBanner = async () => {
    try {
        const response = await fetch('/api/banner');
        const data = await response.json();
        banner.value = data;
    } catch (error) {
        console.error('Lỗi khi tải danh sách banner:', error);
    }
};

// Hàm để lưu dữ liệu sau khi chỉnh sửa
const saveBanner = async () => {
    try {
        const response = await fetch('/api/banner', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(banner.value),
        });

        if (response.ok) {
            alert('Lưu thành công!');
            //router.push('/gioi-thieu');
        } else {
            alert('Đã xảy ra lỗi khi lưu.');
        }
    } catch (error) {
        console.error('Lỗi khi lưu banner:', error);
    }
};

// Hàm để hủy và quay lại trang trước
const cancelEdit = () => {
    router.push('/gioi-thieu');
};

// Gọi hàm fetchBanner khi component được mount
fetchBanner();
</script>

<template>
    <CCol :xs="12">
        <CCard class="mb-4">
            <CCardBody>
                <div class="mb-3">
                    <CFormLabel>Nội dung:</CFormLabel>
                    <CKEditor class="w-100" v-model="banner.text" />
                </div>
                <div class="text-center">
                    <CButton color="primary" @click="saveBanner">Lưu</CButton>
                    <CButton color="secondary" class="ms-2" @click="cancelEdit">Hủy</CButton>
                </div>
            </CCardBody>
        </CCard>
    </CCol>
</template>
import axios from 'axios';

const axiosClient = axios.create({
    baseURL: 'http://localhost:8080/api',
    headers: {
        // 'Content-Type': 'application/json'
    },
});

// Thêm interceptor nếu cần
axiosClient.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem('token'); // Ví dụ lấy token từ localStorage
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

axiosClient.interceptors.response.use(
    (response) => {
        return response.data;
    },
    (error) => {
        return Promise.reject(error);
    }
);

export default axiosClient;
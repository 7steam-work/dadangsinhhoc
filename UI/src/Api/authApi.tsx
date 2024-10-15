import axiosClient from "./axiosClient.js";

const authApi = {
    login(email, password) {
        const url = `auth/login/${email}/${password}`;
        return axiosClient.post(url);
    },

    get(id) {
        const url = `user/findUserById/${id}`
        return axiosClient.get(url)
    }
}

export default authApi;
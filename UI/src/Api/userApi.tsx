import axiosClient from "./axiosClient.tsx";

const userApi = {
    getAll() {
        const url = 'users/getAllUser'
            return axiosClient.get(url);
    },

    get(id) {
        const url = `user/findUserById/${id}`
        return axiosClient.get(url)
    }
}

export default userApi;
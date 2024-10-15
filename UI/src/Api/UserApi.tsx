import axiosClient from "./AxiosClient.tsx";

const UserApi = {
    getAll() {
        const url = '/users/getAllUser';
        return axiosClient.get(url)
    }

};

export default UserApi;
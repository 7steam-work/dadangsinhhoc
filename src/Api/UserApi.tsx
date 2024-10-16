import getAPI from "./AxiosClient.tsx";

const UserApi = {
    getAll() {
        const url = '/users/getAllUser';
        return getAPI.get(url)
    }

};

export default UserApi;
import getAPI from "./AxiosClient.tsx";

const UserApi = {
    getAllUser() {
        const url = '/users/getAllUser';
        return getAPI.get(url)
    },

    getUserById(id: string) {
        const url = `/users/findUserById/${id}`
        return getAPI.get(url)
    },

    getUserByRoll(role: string) {
        const url = `/users/getUsersByRole/${role}`;
        return getAPI.get(url);
    },

    createUser(data: {}) {
        const url = '/users/createUser'
        return getAPI.post(url, data)
    },

    updateUser(id: string, data: {}) {
        const url = `/users/updateUser/${id}`
        return getAPI.put(url, data)
    },

    deleteUser(id: string) {
        const url = `/users/deleteUser/${id}`
        return getAPI.delete(url);
    }

};

export default UserApi;
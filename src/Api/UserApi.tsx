import getAPI from "./AxiosClient.tsx";

const UserApi = {
    getAll() {
        const url = '/users/getAllUser';
        return getAPI.get(url)
    },

    getUserByRoll(role: string) {
        const url = `/users/getUsersByRole/${role}`;
        return getAPI.get(url);
    },

    deleteUser(id: string) {
        const url = `/users/deleteUser/${id}`
        return getAPI.delete(url);
    }

};

export default UserApi;
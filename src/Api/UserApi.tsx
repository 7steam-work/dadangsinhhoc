import getAPI from "./AxiosClient.tsx";

const UserApi = {
    getAll() {
        const url = '/users/getAllUser';
        return getAPI.get(url)
    },

    getUserByRoll(role: string){
        const url = `/users/getUsersByRole/${role}`;
        return getAPI.get(url);
    }

};

export default UserApi;
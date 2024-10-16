import getAPI from "./AxiosClient.tsx";

const AuthApi = {
    login(email: string, password: string) {
        const url = `/auth/login/${email}/${password}`;
        return getAPI.post(url)
    },

    // register(UserData : UserDTO) {
    //     const url = '/auth/register';
    //     return axiosClient.post(url);
    // }

};

export default AuthApi;
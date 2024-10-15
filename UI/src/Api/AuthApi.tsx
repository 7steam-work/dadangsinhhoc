import axiosClient from "./AxiosClient.tsx";

const AuthApi = {
    login(email: string, password: string) {
        console.log('Email: ' + email + "|  pw:" + password)
        const url = `/auth/login/${email}/${password}`;
        return axiosClient.post(url)
    },

    // register(UserData : UserDTO) {
    //     const url = '/auth/register';
    //     return axiosClient.post(url);
    // }

};

export default AuthApi;
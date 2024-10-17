import getAPI from "./AxiosClient.tsx";

const BoApi = {
    getAllBo() {
        const url = '/bo/getAllBo';
        return getAPI.get(url)
    },

    getBoById(id: string) {
        const url = `/bo/findBoById/${id}`
        return getAPI.get(url)
    },

    createBo(data: {}) {
        const url = '/bo/createBo'
        return getAPI.post(url, data)
    },

    updateBo(id: string, data: {}) {
        const url = `/bo/updateBo/${id}`
        return getAPI.put(url, data)
    },

    deleteBo(id: string) {
        const url = `/bo/deleteBo/${id}`
        return getAPI.delete(url);
    }

};

export default BoApi;
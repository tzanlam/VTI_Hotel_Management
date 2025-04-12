import { message } from "antd";
import axios from "axios";

const mainUrl = axios.create({
    baseURL: "http://localhost:8082/",
    timeout: 5000,
    headers: {
        "Content-type": "application/json"
    }
});

mainUrl.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem("token")
        if(token){
            config.headers.Authorization = `Bearer ${token}`
        }
        return config
    },
    function(error){
        return Promise.reject(error)
});

mainUrl.interceptors.response.use(
    (response) => {
        return response;
    },
    (error) => {
        if (!error.response) {
            message.error("Xảy ra lỗi không xác định");
        }
        return Promise.reject(error);
    }
);

export default mainUrl;
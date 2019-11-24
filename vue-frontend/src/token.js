import axios from 'axios'
import LocalStorageService from "./LocalStorageService";

const localStorageService = LocalStorageService.getService();

/*export default axios.create({
    baseURL: "http://localhost:8080"
});*/

axios.interceptors.request.use(
    config => {
        const token = localStorageService.getAccessToken();
        if (token) {
            config.headers['Authorization'] = 'Bearer ' + token;
        }
        return config;
    },
    error => {
        Promise.reject(error)
    });

axios.interceptors.response.use((response) => {
    return response
}, function(error) {
    const originalRequest = error.config;

    console.log("-->TOKEN EXPIRED<--");

    if (error.response.status === 401 && originalRequest.url.endsWith('/refresh')) {
        console.log("-->DELETE TOKEN<--");
        localStorageService.clearToken();
        // this.$router.push('/login');
        // this.$router.go('/login');
        return Promise.reject(error);
    }

    if (error.response.status === 401 && !originalRequest._retry) {
        console.log("-->TRY REFRESH<--");

        originalRequest._retry = true;
        const accessToken = localStorageService.getAccessToken();
        return axios.post('http://localhost:8080/refresh', {
                "accessToken": accessToken
            })
            .then(res => {
                if (res.status === 200) {
                    console.log("-->REFRESH SUCCESS<--");
                    localStorageService.setToken(res.data);
                    axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorageService.getAccessToken();
                    return axios(originalRequest);
                }
            })
    }
    return Promise.reject(error);
});
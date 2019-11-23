import axios from 'axios'
import LocalStorageService from "./LocalStorageService";

const localStorageService = LocalStorageService.getService();

axios.interceptors.request.use(
    config => {
        const token = localStorageService.getAccessToken();
        if (token) {
            config.headers['Authorization'] = 'Bearer ' + token;
        }
        // config.headers['Content-Type'] = 'application/json';
        console.log(config);
        return config;
    },
    error => {
        Promise.reject(error)
    });

axios.interceptors.response.use((response) => {
    console.log("response=> " + JSON.stringify(response.data))
    return response
}, function(error) {
    const originalRequest = error.config;

    if (error.response.status === 401 && originalRequest.endsWith('/refresh')) {
        this.$router.push('/login');
        return Promise.reject(error);
    }

    if (error.response.status === 401 && !originalRequest._retry) {

        originalRequest._retry = true;
        const accessToken = localStorageService.getAccessToken();
        return axios.post('/refresh', {
                "accessToken": accessToken
            })
            .then(res => {
                if (res.status === 201) {
                    localStorageService.setToken(res.data);
                    axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorageService.getAccessToken();
                    return axios(originalRequest);
                }
            })
    }
    return Promise.reject(error);
});
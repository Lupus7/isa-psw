import axios from 'axios'
import LocalStorageService from "./LocalStorageService";
import router from './routes'

const localStorageService = LocalStorageService.getService();
var vm = this;

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
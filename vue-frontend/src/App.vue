<template>
  <div id="app">
      <NavBar/>
      <router-view/>
  </div>
</template>

<script>
import axios from 'axios'
import LocalStorageService from './LocalStorageService'
import NavBar from './components/NavBar'

const localStorageService = LocalStorageService.getService();

export default {
    name: 'app',
    components: {
        NavBar
    },
    methods: {
        refreshToken() {
            setInterval(() =>{
                const accessToken = localStorageService.getAccessToken();
                axios.post('http://localhost:8080/refresh', {
                    "accessToken": accessToken
                })
                .then(res => {
                    if (res.status === 200) {
                        localStorageService.setToken(res.data);
                        axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorageService.getAccessToken();
                    }
            })
            },240000);
        }
    },
    created() {
        this.refreshToken();
    }
}
</script>

<style>
@import url(https://unpkg.com/bootstrap@4.1.0/dist/css/bootstrap.min.css);
</style>

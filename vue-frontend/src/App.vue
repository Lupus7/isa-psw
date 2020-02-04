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

import Vue from 'vue'
import VueGoogleCharts from 'vue-google-charts'

Vue.use(VueGoogleCharts)

const localStorageService = LocalStorageService.getService();

export default {
    name: 'app',
    components: {
        NavBar
    },
    methods: {
        refreshTokenCont() {
            setTimeout(() => {  
                this.refreshToken()
            }, 2000)
            setInterval(() =>{
                this.refreshToken()
            },240000);
        },
        refreshToken() {
            const accessToken = localStorageService.getAccessToken();
            var vm = this;

            axios.post('http://localhost:8080/refresh', {
                "accessToken": accessToken
            })
            .then(res => {
                if (res.status >= 200 && res.status <= 299) {
                    localStorageService.setToken(res.data);
                    axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorageService.getAccessToken();
                } else if (res.status >= 400) {
                    localStorageService.clearToken();
                    vm.$router.push('/login');
                }
            })
        }
    },
    created() {
        const url = window.location.pathname;
        if (!(url.endsWith('/login') || url.endsWith('/register'))) {
            this.refreshTokenCont();       
        }

    }
}
</script>

<style>
@import url(https://unpkg.com/bootstrap@4.1.0/dist/css/bootstrap.min.css);
</style>

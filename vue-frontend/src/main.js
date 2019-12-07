import Vue from 'vue'
import axios from 'axios'
import App from './App.vue'
import router from './routes'
import "./toastr"
import "./token"

Vue.config.productionTip = false
axios.defaults.baseURL = "http://localhost:8080"

new Vue({
    router,
    render: h => h(App),
}).$mount('#app')
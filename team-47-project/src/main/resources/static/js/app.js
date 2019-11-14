const Login = { template: '<login></login>' }
const Register = { template: '<register></register>' }
const Home = { template: '<home></home>' }
const router = new VueRouter({
    mode: 'hash',
    routes: [
        { path: '/login', component: Login },
        { path: '/register', component: Register },
        { path: '/', component: Home }
    ]
});

let app = new Vue({
    router,
    el: '#root',
    methods: {
        mounted: function() {
            alert("Heloo")
        }
    }
});
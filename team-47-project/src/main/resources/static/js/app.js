const Login = {template:'<login></login>'}
const Register={template:'<register></register>'}
const router = new VueRouter({
    mode: 'hash',
    routes:[
        {path: '/login', component: Login},
        {path: '/register', component: Register}
    ]
});

let app = new Vue({
    router,
    el:'#root',
    methods:{
        mounted:function(){
            alert("Heloo")
        }
    }
});

new Vue({
    el:"#nav",
    template:`<nav class="navbar navbar-dark bg-dark">
        <a class="navbar-brand" v-on:click="homepage">Pocetna Stranica</a>
        <a class="navbar-brand" v-on:click="profile" href="#">Profile</a>
        <a class="navbar-brand" v-on:click="login" href="#">Login</a>
        <a class="navbar-brand" v-on:click="register" href="#">Register</a>
        <label></label>
    </nav>`,
    methods:{
        homepage: function(){
            router.push("/")
        },

        profile: function(){
            router.push("/profile")
        },
        login: function(){
            router.push("/login")
        },
        register: function(){
            router.push("/register")
        }
    }
})
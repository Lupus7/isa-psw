<template>
    <nav class="navbar navbar-dark bg-dark">
        <span> <button id="buttLg" class="btn-info" disabled> </button> </span>
        <a class="navbar-brand" v-on:click="homepage">Home</a>
        <a class="navbar-brand" v-on:click="profile" href="#">Profile</a>
        <a class="navbar-brand" v-on:click="login" href="#" id="log">Login</a>
        <a class="navbar-brand" v-on:click="register" href="#" id="reg">Register</a>
        <a class="navbar-brand" v-on:click="logout" href="#" id="logo"></a>
        <label></label>
    </nav>
</template>

<script>
export default {
    methods:{
        shouldChange: function(route) {
            return this.$router.history.current.fullPath !== route
        },
        homepage: function(){
            const route = "/"
            if(this.shouldChange(route))
                this.$router.push(route)
        },

        profile: function(){
            const route = "/profile"
            if(this.shouldChange(route))
                this.$router.push(route)
        },
        login: function(){
            const route = "/login"
            if(this.shouldChange(route))
                this.$router.push(route)
        },
        register: function(){
            const route = "/register"
            if(this.shouldChange(route))
                this.$router.push(route)
        },
        logout: function(){
            
            document.getElementById("log").innerHTML="Login";
            document.getElementById("reg").innerHTML="Register";
            document.getElementById("logo").innerHTML="";
            document.getElementById("buttLg").innerHTML="";
            
            const route = "/"
            if(this.shouldChange(route))
                this.$router.push(route)
        } 
    }
}
</script>
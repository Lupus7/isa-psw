<template>
    <nav class="navbar navbar-dark bg-dark">
        <button type="button" class="btn btn-primary" :style="userButton" disabled >
            {{ userEmail }}
        </button>
        <a class="navbar-brand" v-on:click="homepage">Home</a>
        <a class="navbar-brand" v-on:click="profile" href="#">Profile</a>
        <a class="navbar-brand" v-on:click="login" href="#" id="log">Log in</a>
        <a class="navbar-brand" v-on:click="register" href="#" id="reg">Register</a>
        <a class="navbar-brand" v-on:click="logout" href="#" id="logo">Log out</a>
        <label></label>
    </nav>
</template>

<script>
export default {

    data(){
        return{
            userButton : "visibility:hidden",
            userEmail : ""
           

        }
        
    },
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
            
            
            
            const route = "/"
            if(this.shouldChange(route))
                this.$router.push(route)
        } 
    }
}
</script>
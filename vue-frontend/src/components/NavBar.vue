<template>
    <nav class="navbar navbar-dark bg-dark">
        <button type="button" class="btn btn-primary" :style="userButton" disabled >
            {{ userEmail }}
        </button>
        <a class="navbar-brand" v-on:click="homepage" href="#">Home</a>
        <a class="navbar-brand" v-on:click="profile" href="#" :style="userButton">Profile</a>
        <a class="navbar-brand" v-on:click="login" href="#"  :style="guestButton">Log in</a>
        <a class="navbar-brand" v-on:click="register" href="#"  :style="guestButton">Register</a>
        <a class="navbar-brand" v-on:click="logout" href="#"  :style="userButton">Log out</a>
        <label></label>
    </nav>
</template>

<script>
import axios from 'axios'
import jwt_decode from 'jwt-decode'
export default {

    data(){
        return{
            userButton : "visibility:hidden",
            guestButton: "visibility:visible",
            userEmail : "",
            role:'',
            doctor: false,
            patient: false,

        }
        
    },
    created(){       
        this.refresh()     
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
        patientprofile:function(){
            this.$router.push("/patientProfile")
        },

        profile: function(){
            const route = "/profile"
            if (this.role == "ROLE_PATIENT")
                this.$router.push("/patientProfile")
            
            else{
                this.shouldChange(route)
                this.$router.push(route)
            }
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
                
            axios.get('http://localhost:8080/logout').then(response=>{
               
               
                localStorage.setItem("user",JSON.stringify(response.data));
                this.guestButton = "visibility:visible";
                this.userButton=  "visibility:hidden";
                this.userEmail="";
                             
            })
           
            
        },
        returnToken(){
            let user = JSON.parse(localStorage.getItem('user'));

            if (user && user.accessToken) {
                console.log(user.accessToken)
                return { Authorization: 'Bearer ' + user.accessToken };
            } else {
                return {};
            }
        },

        refresh(){
            
            axios.post('http://localhost:8080/refresh',
                null,{ 
                    headers: this.returnToken()
                }).then(response=>{})
               
         
            if(localStorage.getItem("user") != null){
                const token = jwt_decode(localStorage.getItem("user"));
                console.log(token)
                this.role = token.roles
                this.userEmail = token.sub;
                this.userButton = "visibility:visible";
                this.guestButton=  "visibility:hidden";
                console.log(this.userEmail);
            }else{
                this.guestButton = "visibility:visible";
                this.userButton=  "visibility:hidden";
                this.userEmail="";
            }
        }
        
    }
}
</script>

<style >


</style>
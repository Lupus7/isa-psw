<template>
    <div>
    <nav class="navbar navbar-dark bg-dark">
        <button v-if=" this.userEmail !='' " type="button" class="btn btn-primary"  disabled >
            {{ userEmail }}
        </button>
        <a v-if=" this.firstLogin === false" class="navbar-brand" v-on:click="homepage" href="#">Home</a>
        <a v-if=" this.firstLogin === false && this.userEmail !='' " class="navbar-brand" v-on:click="profile" href="#" >Profile</a>
        <a v-if=" this.userEmail ==='' " class="navbar-brand" v-on:click="login" href="#"  >Log in</a>
        <a v-if=" this.userEmail ==='' "  class="navbar-brand" v-on:click="register" href="#"  >Register</a>
        <a v-if=" this.userEmail !=''  " class="navbar-brand" v-on:click="logout" href="#"  >Log out</a>
       
    </nav>

    <div>
        <password-change v-if="this.firstLogin === true && this.userEmail != '' "> PasswordChange </password-change>
    </div>

    </div>
</template>

<script>
import axios from 'axios'
import jwt_decode from 'jwt-decode'
import LocalStorageService from "../LocalStorageService";
import PasswordChange from './PasswordChange.vue'

export default {

    data(){
        return{
            userEmail : "",
            role:'',
            firstLogin: false,

        }
        
        
    },
    components: {
        PasswordChange
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
            else if(this.role === "ROLE_DOCTOR" || this.role === "ROLE_NURSE")
                 this.$router.push("/userProfile")
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
            
            this.firstLogin = false;
            LocalStorageService.getService().clearToken();
            this.$router.push("/login")
            this.refresh();          
            
        },
        

        refresh(){
            
           const lss = LocalStorageService.getService();

            if(lss.getAccessToken() != undefined && lss.getAccessToken() != null){
                
                const token = jwt_decode(lss.getAccessToken());
                this.role = token.roles 
                this.userEmail = token.sub; 

                // znaci ako je firstLogin true prebacivanje na formu za promenu
                axios.get('http://localhost:8080/user/firstLogin').then(response => {                    
                    this.firstLogin=response.data;
                });       
                          
            }else
                 this.userEmail="";
            
        },
        
        
    }
}
</script>

<style >

.nav{
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif

}

</style>
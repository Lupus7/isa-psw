<template>
    <nav class="navbar navbar-dark bg-dark">
        <button v-if=" this.userEmail !='' " type="button" class="btn btn-primary"  disabled >
            {{ userEmail }}
        </button>
        <a class="navbar-brand" v-on:click="homepage" href="#">Home</a>
        <a v-if=" this.userEmail !='' " class="navbar-brand" v-on:click="profile" href="#" >Profile</a>
        <a v-if=" this.userEmail ==='' " class="navbar-brand" v-on:click="login" href="#"  >Log in</a>
        <a v-if=" this.userEmail ==='' "  class="navbar-brand" v-on:click="register" href="#"  >Register</a>
        <a v-if=" this.userEmail !='' " class="navbar-brand" v-on:click="logout" href="#"  >Log out</a>
        <label></label>
    </nav>
</template>

<script>
import axios from 'axios'
import jwt_decode from 'jwt-decode'
import LocalStorageService from "../LocalStorageService";
export default {

    data(){
        return{
            userEmail : "",
            role:'',
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
                
            }else
                 this.userEmail="";
            
        }
        
    }
}
</script>

<style >


</style>
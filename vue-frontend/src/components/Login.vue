<template>

    <div class="container"  >
         <div class="modal-header">
            <h3>Login</h3>
        </div>
            
            <div class="panel-body">
                <br>
                <form accept-charset="UTF-8" role="form">
                <fieldset>
                    <div class="form-group">
                        <input class="form-control" placeholder="Email.." name="email" v-model="email" type="text">
                    </div>
                    <div class="form-group">
                        <input class="form-control"  id="password" placeholder="Password..." name="password" v-model="password" type="password">
                    </div>
                    
                    <center> <input class="btn btn-lg btn-success btn-block" @click.prevent="validation" type="submit" value="Login"> </center>
                </fieldset>
                </form>
                <hr/>
                <center><h4>OR</h4></center>
                <input class="btn btn-lg btn-primary btn-block" type="submit" v-on:click="validation($event)" value="Register">
            </div>

      
	</div>
</template>

<script>
import axios from 'axios'
import LocalStorageService from "../LocalStorageService";
import {funToastr} from "../toastr.js"
import { async } from 'q';
const localStorageService = LocalStorageService.getService();

export default {
    data: function(){
        return{
            email:"",
            password:"",
            
        }
    },
    methods:{
        register: function(){
            this.$router.push("/register")
        },
        validation: async function(e) {
            e.preventDefault()
            if((this.email=="") || (this.password=="")){
                alert("Please fill out all the fileds")
                return
            }
         
            axios.post('http://localhost:8080/login', {
                        "username": this.email,
                        "password": this.password

                }).then(response=>{
                    console.log("---LLLLLL"+response.data)
                    if (response.data){
                        const lss = LocalStorageService.getService();
                        lss.setToken(response.data);
                        funToastr("s","Redirecting to Home Page!","Login!");
                    }else {
                        funToastr("d","Bad credentials!","Login!");
                        return;
                    }
                  
                   
                }).finally(()=>{ 

                     setTimeout(() =>{                    
                 
                        this.$router.push("/");       
                        this.$router.go("/");
                             
                    },1600);

                });           
                   
               
        },
        
    },
    
}
</script>

<style scoped>


 .container{
    margin-top: 5vh;
    max-width: 25vw;
    padding: 40px;
    background: #f1f1f1;
    border-radius: 10px;
    align-self: center;
    border: 1px dotted grey;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif

 }

.form {
    display: block !important;
    align-items: stretch;
    padding-top: 20px;
      
 }
.input {
    display: block;
    padding: 15px 10px;
    margin-bottom: 10px;
    width: 100%;
    border: 1px solid #ddd;
}

.panel-heading{
    width: 100%;
}

.modal-header{

  border-bottom: 2px solid #2c2c2c;
  width: 100%;
  padding: 15px;
  font-size: 20px;
  font-size: 3vh
}

</style>
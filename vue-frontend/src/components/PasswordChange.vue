<template>
    <div class= "container">

         <div class="modal-header">
             <h3>Password Change</h3>
        </div>
            
            <div class="panel-body">
                <br>
                <form accept-charset="UTF-8" role="form">
                    <div v-if=" this.firstLogin === false" class="control-group">
                        <label for="current_password" class="control-label">Current Password:</label>
                         <div class="form-group">
                            <input class="form-control"  id="passwordCurr" placeholder="Current Password..." type="password" v-model="passwordCurr">
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="new_password" class="control-label">New Password:</label>
                        <div class="form-group">
                            <input class="form-control"  id="passwordNew" placeholder="New Password..."  type="password" v-model="passwordNew">
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="confirm_password" class="control-label">Confirm Password:</label>
                        <div class="form-group">
                            <input class="form-control"  id="passwordConf" placeholder="Confirm Password..."  type="password" v-model="passwordConf">
                        </div>
                        
                    </div>
                    <br>
                    <div class="modal-footer">
                        <button class="btn btn-primary" v-on:click="confirm($event)">Confirm</button>
                        <button v-if=" this.firstLogin === false" class="btn btn-secondary" v-on:click="close($event)">Close</button>
                     </div>      
                </form>
                          
            </div>

       
</div>
    
</template>

<script>
import axios from 'axios'
import jwt_decode from 'jwt-decode'
import {funToastr} from "../toastr.js"
import LocalStorageService from "../LocalStorageService";

    export default{
        data(){
            return{
                passwordCurr : "",
                passwordNew : "",
                passwordConf : "",
                email : "",
                firstLogin : false,

            }

        },
        created(){
            this.getFLogin();
        },

        methods:{

            confirm(e){
                e.preventDefault();
                
                if(this.firstLogin === true){

                    axios.post('http://localhost:8080/user/updatePasswordFLogin', {
                        "passwordNew" : this.passwordNew,
                        "passwordConf" : this.passwordConf,
                        "email" : this.email,
                        "passwordCurr" : "Nothing",
                    }).then(response=>{
                        if(response.status == 200)

                            if (response.data){
                                const lss = LocalStorageService.getService();
                                lss.setToken(response.data);
                                funToastr("s","You have changed your password successfuly!","Password Change!");
                            }

                        else
                            funToastr("d",response.data,"Password Change!");

                        this.firstLogin = false;

                    }).finally(()=>{ 

                    setTimeout(() =>{                    
                 
                        this.$router.push("/");       
                        this.$router.go("/");
                             
                    },1700);

                });  


                }else{

                    axios.post('http://localhost:8080/user/updatePassword', {
                        "passwordCurr" : this.passwordCurr,
                        "passwordNew" : this.passwordNew,
                        "passwordConf" : this.passwordConf,
                        "email" : this.email,
                    }).then(response=>{
                        if(response.status == 200)
                            if (response.data){
                                const lss = LocalStorageService.getService();
                                lss.setToken(response.data);
                                funToastr("s","You have changed your password successfuly!","Password Change!");
                            }
                        else
                            funToastr("d",response.data,"Password Change!");

                    }).finally(()=>{ 

                        setTimeout(() =>{                    
                    
                            this.$router.push("/");       
                            this.$router.go("/");
                                
                        },1700);

                    });  
 
                }



            },
            close(e){
                e.preventDefault();

                let role = "";

                const lss = LocalStorageService.getService();

                if(lss.getAccessToken() != undefined && lss.getAccessToken() != null){          
                    const token = jwt_decode(lss.getAccessToken());           
                    role = token.roles;  
                }

                if(role == "ROLE_CCADMIN")
                    this.$router.push("/profile");
                else
                    this.$router.push("/userProfile");
            },
            getFLogin(){
            
                const lss = LocalStorageService.getService();

                if(lss.getAccessToken() != undefined && lss.getAccessToken() != null){
                    
                    const token = jwt_decode(lss.getAccessToken());
                    this.email = token.sub; 

                    axios.get('http://localhost:8080/user/firstLogin').then(response => {                    
                        this.firstLogin=response.data;
                    });       
                            
                }else
                    this.email="";
                
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

  border-bottom: 2px solid #5f5f5f;
  width: 100%;
  padding: 15px;
  font-size: 20px;
  font-size: 3vh

}

.modal-footer{

  border-top: 2px solid #5f5f5f;
  width: 100%;
  font-size: 20px;
  font-size: 3vh
}

</style>
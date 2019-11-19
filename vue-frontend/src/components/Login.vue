<template>
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Login via site</h3>
                    </div>
                    <div class="panel-body">
                        <form accept-charset="UTF-8" role="form">
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="Email.." name="email" v-model="email" type="text">
                            </div>
                            <div class="form-group">
                                <input class="form-control"  id="password" placeholder="Password" name="password" v-model="password" type="password">
                            </div>
                            
                            <input class="btn btn-lg btn-success btn-block" @click.prevent="validation" type="submit" value="Login">
                        </fieldset>
                        </form>
                        <hr/>
                        <center><h4>OR</h4></center>
                        <input class="btn btn-lg btn-facebook btn-block" type="submit" v-on:click="validation($event)" value="Register">
                    </div>
                </div>
            </div>
        </div>
	</div>
</template>

<script>
import axios from 'axios'

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
        validation: function(e) {
            //let email = document.getElementById("email").val
            e.preventDefault()
            if((this.email=="") || (this.password=="")){
                alert("Please fill out all the fileds")
                return
            }
         
            axios
                .post('http://localhost:8080/login', {
                        "username": this.email,
                        "password": this.password
                    })
                .then(response=>{
                    if (response.data.accessToken){
                        localStorage.setItem("user",JSON.stringify(response.data));
                       
                    }
                                        
                    this.$router.go("/")
                })
                /*.catch(error=>function() {
                    alert("Neuspesno");
                    return
                });*/
        }
    },
    
}
</script>
<template>
    <div class= "container">
        <div class="col-md-9">
                <div class="card">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-12">
                                <h4>Your Profile</h4>
                                <hr>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <form>
                                <div class="form-group row">
                                    <label for="username" class="col-4 col-form-label">First Name</label> 
                                    <div class="col-8">
                                    <input class="form-control here" type="text" id="fN"  > 
                                       
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="name" class="col-4 col-form-label">Last Name</label> 
                                    <div class="col-8">
                                    <input  class="form-control here" type="text"  id="nN">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-4 col-form-label">Address</label> 
                                    <div class="col-8">
                                    <input  class="form-control here" type="text"  id="add" >
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label  class="col-4 col-form-label">City</label> 
                                    <div class="col-8">
                                    <input  class="form-control here"  type="text"  id="cy">
                                    </div>
                                </div>
                                 <div class="form-group row">
                                    <label class="col-4 col-form-label">State</label> 
                                    <div class="col-8">
                                    <input  class="form-control here"  type="text"  id="st" >
                                    </div>
                                </div>
                               <div class="form-group row">
                                    <label for="text" class="col-4 col-form-label">Telephone</label> 
                                    <div class="col-8">
                                    <input  class="form-control here" type="text"  id="tp" >
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-4 col-form-label">Unique Number</label> 
                                    <div class="col-8">
                                    <input  class="form-control here" type="text"  id="un" >
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label  class="col-4 col-form-label">Specialization</label> 
                                    <div class="col-8">
                                    <input  class="form-control here" type="text"  id="spec" >
                                    </div>
                                </div>     

                                <div class="modal-footer">
                                    
                                <button name="submit" type="submit" class="btn btn-primary" v-on:click="update" >Update My Profile</button>
                                <button name="submit" type="submit" class="btn btn-secondary" v-on:click="cancel" >Cancel</button>

                                
                                    
                                </div>
                                   
                                
                                </form>
                            </div>
                        </div>
                        
                    </div>
             </div>
        </div>
    </div>
                
    
</template>

<script>
import axios from 'axios'
import {returnToken} from '../token.js'
export default {
     data(){
        return{
            user : [],
            firstName: "",
            lastName: "",
            address: "",
            city: "",
            state: "",
            telephone: "",
            uniqueNum: "",
            specialization: "",
            email: "",
            events:[]

        }
        
    },
     methods:{
        getDoctor(){
            axios.get('http://localhost:8080/doctor/getInfo',{ 
                headers: returnToken()
            }).then(response => { this.user = response.data;
                this.firstName = this.user.firstName;
                this.lastName= this.user.lastName;  
                this.address = this.user.address;
                this.city = this.user.city;
                this.state= this.user.state;
                this.telephone = this.user.telephone;
                this.uniqueNum= this.user.uniqueNum;
                this.specialization= this.user.specialization; 
                this.email = this.user.email; 
                

                document.getElementById("fN").value = this.firstName;
                document.getElementById("nN").value = this.lastName;
                document.getElementById("add").value = this.address;
                document.getElementById("cy").value = this.city;
                document.getElementById("st").value = this.state;
                document.getElementById("tp").value = this.telephone;
                document.getElementById("un").value = this.uniqueNum;
                document.getElementById("spec").value = this.specialization;

            })
            

           
        },
        cancel(event){
            this.$router.push("/doctorProfile");
        },
        update(event){

                this.firstName = document.getElementById("fN").value;
                this.lastName = document.getElementById("nN").value;
                this.address = document.getElementById("add").value;
                this.city = document.getElementById("cy").value;
                this.state = document.getElementById("st").value;
                this.telephone = document.getElementById("tp").value;
                this.uniqueNum = document.getElementById("un").value;
                this.specialization = document.getElementById("spec").value;

               if(this.firstName==="" || this.LastName==="" || this.address==="" ||this.city==="" || this.state===""  || this.telephone===""  || this.uniqueNum===""  || this.specialization==="" ){
                    alert("Fill all the fields");
                    return;
			    }   

            axios.post('http://localhost:8080/doctor/updateDoctorInfo',{
                "firstName" : this.firstName,
                "lastName" : this.lastName,
                "email" : this.email,
                "address":this.address,
                "city":this.city,
                "state" : this.state,
                "telephone":this.telephone,
                "uniqueNum" : this.uniqueNum,
                "specialization" : this.specialization
            }).then(response => { 
                
             })

            this.$router.push("/doctorProfile");
           
        },

        
       

    },
    created(){
      
        this.getDoctor( );
    },
  
}
</script>

<style scoped>

</style>
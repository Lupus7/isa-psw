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
                                <div v-if=" this.role === 'ROLE_DOCTOR'"  class="form-group row">
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
import {funToastr} from "../toastr.js"
import jwt_decode from 'jwt-decode'
import LocalStorageService from "../LocalStorageService";
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
            role : ""

        }
        
    },
     methods:{
        getDoctor(){
            axios.get('http://localhost:8080/doctor/getInfo').then(response => { 
                this.user = response.data;
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

        getNurse(){
            axios.get('http://localhost:8080/nurse/getInfo').then(response => { 
                this.user = response.data;
                this.firstName = this.user.firstName;
                this.lastName= this.user.lastName;  
                this.address = this.user.address;
                this.city = this.user.city;
                this.state= this.user.state;
                this.telephone = this.user.telephone;
                this.uniqueNum= this.user.uniqueNum;
                this.email = this.user.email;              

                document.getElementById("fN").value = this.firstName;
                document.getElementById("nN").value = this.lastName;
                document.getElementById("add").value = this.address;
                document.getElementById("cy").value = this.city;
                document.getElementById("st").value = this.state;
                document.getElementById("tp").value = this.telephone;
                document.getElementById("un").value = this.uniqueNum;

            })
            

           
        },

        getCCAdmin(){
            axios.get('/cca/info').then(response => { 
                this.user = response.data;
                this.firstName = this.user.firstName;
                this.lastName= this.user.lastName;  
                this.address = this.user.address;
                this.city = this.user.city;
                this.state= this.user.state;
                this.telephone = this.user.telephone;
                this.uniqueNum= this.user.uniqueNum;
                this.email = this.user.email;              

                document.getElementById("fN").value = this.firstName;
                document.getElementById("nN").value = this.lastName;
                document.getElementById("add").value = this.address;
                document.getElementById("cy").value = this.city;
                document.getElementById("st").value = this.state;
                document.getElementById("tp").value = this.telephone;
                document.getElementById("un").value = this.uniqueNum;

            })
            

           
        },

         getCAdmin(){
            axios.get('http://localhost:8080/ca/getInfo').then(response => { 
                this.user = response.data;
                this.firstName = this.user.firstName;
                this.lastName= this.user.lastName;  
                this.address = this.user.address;
                this.city = this.user.city;
                this.state= this.user.state;
                this.telephone = this.user.telephone;
                this.uniqueNum= this.user.uniqueNum;
                this.email = this.user.email;              

                document.getElementById("fN").value = this.firstName;
                document.getElementById("nN").value = this.lastName;
                document.getElementById("add").value = this.address;
                document.getElementById("cy").value = this.city;
                document.getElementById("st").value = this.state;
                document.getElementById("tp").value = this.telephone;
                document.getElementById("un").value = this.uniqueNum;

            })
            

           
        },

        cancel(e){
            e.preventDefault()
            
            if(this.role == "ROLE_CCADMIN")
                this.$router.push("/profile");
            else if(this.role == "ROLE_CADMIN")
                this.$router.push("/caProfile");
            else
                this.$router.push("/userProfile");
        },
        
        update(event){
            // u zavisnosti od role menjaj
            event.preventDefault();
            this.firstName = document.getElementById("fN").value;
            this.lastName = document.getElementById("nN").value;
            this.address = document.getElementById("add").value;
            this.city = document.getElementById("cy").value;
            this.state = document.getElementById("st").value;
            this.telephone = document.getElementById("tp").value;
            this.uniqueNum = document.getElementById("un").value;

            if(this.role === "ROLE_DOCTOR"){
                this.specialization = document.getElementById("spec").value;
                if(this.firstName==="" || this.LastName==="" || this.address==="" ||this.city==="" || this.state===""  || this.telephone===""  || this.uniqueNum===""  || this.specialization==="" ){
                    funToastr("w","Fields must not be empty!","Empty fields!");
                    return;
			    }else{

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
                        
                    }).finally(()=>{

                        funToastr("s","Data successfuly updated!","Data Updated!");
                            setTimeout(() =>{
                            this.$router.push("/userProfile");
                        },1500);
           

                    })
                    
                } 
            }else if(this.role === "ROLE_NURSE"){
                if(this.firstName==="" || this.LastName==="" || this.address==="" ||this.city==="" || this.state===""  || this.telephone===""  || this.uniqueNum==="" ){
                    funToastr("w","Fields must not be empty!","Empty fields!");
                    return;
                }
                
                axios.post('http://localhost:8080/nurse/updateNurseInfo',{
                        "firstName" : this.firstName,
                        "lastName" : this.lastName,
                        "email" : this.email,
                        "address":this.address,
                        "city":this.city,
                        "state" : this.state,
                        "telephone":this.telephone,
                        "uniqueNum" : this.uniqueNum,
                     
                    }).then(response => { 
                        
                    }).finally(()=>{

                        funToastr("s","Data successfuly updated!","Data Updated!");
                            setTimeout(() =>{
                            this.$router.push("/userProfile");
                        },1500);
           

                    })

            }else if(this.role === "ROLE_CCADMIN"){
                if(this.firstName==="" || this.LastName==="" || this.address==="" ||this.city==="" || this.state===""  || this.telephone===""  || this.uniqueNum==="" ){
                    funToastr("w","Fields must not be empty!","Empty fields!");
                    return;
                }
                
                axios.put('/cca/info',{
                        "firstName" : this.firstName,
                        "lastName" : this.lastName,
                        "email" : this.email,
                        "address":this.address,
                        "city":this.city,
                        "state" : this.state,
                        "telephone":this.telephone,
                        "uniqueNum" : this.uniqueNum,
                     
                    }).then(response => { 
                        
                    }).finally(()=>{

                        funToastr("s","Data successfuly updated!","Data Updated!");
                            setTimeout(() =>{
                            this.$router.push("/profile");
                        },1500);
           

                    })

            }else if(this.role === "ROLE_CADMIN"){
                if(this.firstName==="" || this.LastName==="" || this.address==="" ||this.city==="" || this.state===""  || this.telephone===""  || this.uniqueNum==="" ){
                    funToastr("w","Fields must not be empty!","Empty fields!");
                    return;
                }
                
                axios.post('http://localhost:8080/ca/updateInfo',{
                        "firstName" : this.firstName,
                        "lastName" : this.lastName,
                        "email" : this.email,
                        "address":this.address,
                        "city":this.city,
                        "state" : this.state,
                        "telephone":this.telephone,
                        "uniqueNum" : this.uniqueNum,
                     
                    }).then(response => { 
                        
                    }).finally(()=>{

                        funToastr("s","Data successfuly updated!","Data Updated!");
                            setTimeout(() =>{
                            this.$router.push("/caProfile");
                        },1500);
           

                    })

            }


        },

        getRole(){

           const lss = LocalStorageService.getService();

            if(lss.getAccessToken() != undefined && lss.getAccessToken() != null){          
                const token = jwt_decode(lss.getAccessToken());           
                this.role = token.roles;  
            }
            

        },

        
       

    },
    created(){
        this.getRole();
        if(this.role == "ROLE_DOCTOR")
            this.getDoctor();
        else if(this.role == "ROLE_NURSE")
            this.getNurse();
        else if(this.role == "ROLE_CCADMIN")
            this.getCCAdmin();
        else if(this.role == "ROLE_CADMIN")
            this.getCAdmin();
    },
  
}
</script>

<style scoped>

.container{
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif

}

</style>
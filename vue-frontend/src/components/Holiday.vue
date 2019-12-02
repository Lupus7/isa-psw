<template>

 <div class="container">
        <br>
        <div class="row" >
            <div class="col-12">
                <div class="card" style="border-color:black; border-radius:10px">

                    <div class="card-body" >
                        <div class="card-title mb-4">

                                <div class="modal-header" >
                                <h2 class="modal-title" > Holiday/Time off Request </h2>                       
                                </div>
                            <br>
                            <div class="d-flex justify-content-start">                    
                              
                              <div class="jumbobox">
                               
                                <h2 class="d-block" style="font-size: 1.5rem; font-weight: bold;">{{email}}</h2>    
                                <h2 class="d-block" style="font-size: 1.5rem; font-weight: bold; margin-right:-200vh">{{firstName}}</h2>
                                <h2 class="d-block" style="font-size: 1.5rem; font-weight: bold;margin-right:-2200vh">{{lastName}}</h2>
                                <h2 class="d-block" style="font-size: 1.5rem; font-weight: bold">{{role}}</h2>      
                                
                               

                              </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-12">
                                <ul class="nav nav-tabs mb-4" id="myTab" role="tablist">
                                    <li class="nav-item">
                                        <a class="nav-link active" id="basicInfo-tab" data-toggle="tab" href="#basicInfo" role="tab" aria-controls="basicInfo" aria-selected="true">Body</a>
                                    </li>
                                </ul>
                                <div class="tab-content ml-1" id="myTabContent">
                                    <div class="tab-pane fade show active" id="basicInfo" role="tabpanel" aria-labelledby="basicInfo-tab" >
                                
                                    <form>
                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label >Begin:</label>
                                                <input type="date" class="form-control" v-model="beginDate">
                                            </div>
                                                <div class="form-group col-md-6">
                                                <label>End:</label>
                                                <input type="date" class="form-control" v-model="endDate">
                                            </div>
                                        </div>
                                            
                                            <div class="control-group">
                                                <label for="new_password" class="control-label">Type of request:</label>
                                                <div class="form-group">
                                                    <select class="form-control" id="select">
                                                        <option> Holiday </option>
                                                        <option> Time off </option>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="control-group">
                                                <label class="control-label">Reason:</label>
                                                <div class="form-group">
                                                    <textarea rows="6" cols="50" class="form-control" style="resize: none;" v-model="reason"  />
                                                </div>
                                            
                                            </div>

                                            <br>
                                            <div class="modal-footer">
                                                <button class="btn btn-success" @click="submitRq($event)">Submit request</button>
                                                <button class="btn btn-secondary" @click="close($event)">Close</button>
                                            </div>    
                                        
                                    </form>
                                    </div>
                                    <div class="tab-pane fade" id="connectedServices" role="tabpanel" aria-labelledby="ConnectedServices-tab">
                                        Facebook, Google, Twitter Account that are connected to this account
                                    </div>
                                </div>
                            </div>
                        </div>


                    </div>

                </div>
            </div>
        </div>
    </div>

   
    
</template>


<script>
import axios from 'axios'
import jwt_decode from 'jwt-decode'
import LocalStorageService from "../LocalStorageService";

export default {

    

    data() {
        return {
            email:"",
            role: "",
            firstName : "",
            lastName: "",

            beginDate : "",
            endDate : "",
            typeRequest : "",
            reason : ""
            
                   
        }
    },

    props: ["staff"],
    methods:{
       

        submitRq(e){
            e.preventDefault();
            this.typeRequest = document.getElementById("select").value;
            axios.post('http://localhost:8080/medicalStaff/holiday', {
                        "email" : this.staff.email,
                        "beginDate" : this.beginDate,
                        "endDate" : this.endDate,
                        "typeRequest" : this.typeRequest,
                        "reason" : this.reason
                    }).then(response=>{
                        if(response.status == 200)
                            funToastr("s","Request send!","Request!");
                
                        else
                            funToastr("d",response.data,"Request!");

                   

                    }).finally(()=>{ 

                    setTimeout(() =>{                    
                 
                        this.$router.push("/userProfile");       
                        this.$router.go("/userProfile");
                             
                    },1700);

                });  
          
        },
        close(e){
            e.preventDefault();
            this.$router.push("/userProfile");
          
        },

        getRole(){

           const lss = LocalStorageService.getService();

            if(lss.getAccessToken() != undefined && lss.getAccessToken() != null){          
                const token = jwt_decode(lss.getAccessToken()); 
                if(token)  
                    this.role = "Role: "+ token.roles.replace("ROLE_", "");     
            }
            
        },
    },

    created(){
        console.log(this.staff)
        this.getRole();
        this.email ="Email: "+this.staff.email;
        this.firstName = "First Name: "+this.staff.firstName;
        this.lastName = "Last Name: "+this.staff.lastName;
            
    }

    
}
</script>

<style scoped>

 .container{

        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif

    }

.modal-header{

  border-bottom: 2px solid #5f5f5f;
  width: 100%;
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
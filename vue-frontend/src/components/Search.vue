<template>

    
    <div>

    <div>
       <div class="jumbobox" v-if=" !this.hide " >
        <div style=" margin-left:6px; margin-right: 100vh">
            <div class="modal-header" style="margin-left:2px; margin-right: 100vh; ">
                <h3>Search</h3>
            </div>
        </div>
                
                <div class="panel-body" style="margin-left:10px; margin-right: 100vh; " >
                    <br>
                    <form accept-charset="UTF-8" role="form" >

                        <div class="control-group" >
                            <label  class="control-label" style=" font-size: 2vh" >First Name:</label>
                            <div class="form-group">
                                <input class="form-control"  style=" font-size: 2vh; "  placeholder="First name..." v-model="firstName" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label  class="control-label" style=" font-size: 2vh">Last Name:</label>
                            <div class="form-group">
                                <input class="form-control"  style=" font-size: 2vh"  placeholder="Last Name..." v-model="lastName" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" style=" font-size: 2vh" >Patient ID:</label>
                            <div class="form-group">
                                <input class="form-control" style=" font-size: 2vh"  placeholder="Patient ID..." v-model="id" />
                            </div>
                            
                        </div>
                        <br>
                        <div class="modal-footer" style="">
                            <button class="btn btn-success btn-lg" @click="search($event)">Confirm</button>
                             <button class="btn btn-secondary " @click="hideS($event)">Hide search</button>
                        </div>      
                    </form>
                            
                </div>
       </div>
        <br>

        



        <div v-if=" this.patients.length ">

            <div class="wrapper">
        
            <div class="control-group" style="margin-left:8px"> 

            <div style="style=margin-left:2px; margin-right: 100vh">
                <div class="modal-header" style="margin-left:2px; margin-right: 100vh; ">
                    <h3>Filter</h3>
                </div>
            </div>
            <br>
             <div class="panel-body" style="margin-left:10px; margin-right: 100vh; " >
                <div class="control-group">
                    <label  class="control-label" style=" font-size: 2vh"> Filter by:  </label>
                    <div class="form-group">

                        <select id="filterV"  class="btn btn-primary" style="font-size: 2vh" @change="filter($event)" >
                            <option align="justify">-Select option-</option>
                            <option align="justify" >Patient ID</option>
                            <option align="justify" >First Name</option>
                            <option align="justify" >Last Name</option>
                            <option align="justify" >Address</option>
                            <option align="justify" >City</option>
                            <option align="justify" >State</option>
                            <option align="justify" >Telephone</option>
                            <option align="justify" >Unique Number</option>

                        </select>
                    </div>
                            
                </div>

                <div class="control-group" v-if=" this.valueFilter !== '-Select option-' " >
                    <label class="control-label" style=" font-size: 2vh" >{{valueFilter}}:</label>
                    <div class="form-group">
                         <input class="form-control" style=" font-size: 2vh"  placeholder= "..." v-model="filterBy" />
                    </div>
                            
                </div>
             

                 <br>
                <div class="modal-footer">
                    <button class="btn btn-success btn-lg" @click="filterConfirm($event)">Confirm</button>
                </div> 
             </div>     
            
            </div>
            </div>

           <br>

        </div>

            <br>
            <div class="wrapper" style="margin-right: 2vh">

               
                <table class="table table-hover" style=" margin-left:10px;" >
                <thead >

                <tr  class="thead-dark">
                    <th colspan="9" > <center> <h2> Table of Patients </h2> </center> </th>                  
                </tr>
                <tr class="table-secondary">
            
                    <th  align="justify" style="font-size:18px" >Patient ID</th>
                    <th  align="justify"  style="font-size:18px" >First Name</th>
                    <th  align="justify" style="font-size:18px" >Last Name</th>
                    <th  align="justify" style="font-size:18px" >Address</th>
                    <th  align="justify" style="font-size:18px" >City</th>
                    <th  align="justify" style="font-size:18px" >State</th>
                    <th  align="justify" style="font-size:18px" >Telephone</th>
                    <th  align="justify" style="font-size:18px" >Unique Number</th>
                    <th align = "justify" style="font-size:18px"> Patient Profil </th> 
                </tr>
                </thead>

                <template v-if="this.valueFilter!== ''" >
                    <tbody v-for="(patient, key) in this.patients " :key='key'>
                    <tr> 
                        <td  align="justify">{{patient.id}}</td>
                        <td  align="justify">{{patient.firstName}}</td>
                        <td  align="justify">{{patient.lastName}}</td>
                        <td  align="justify">{{patient.address}}</td>
                        <td  align="justify">{{patient.city}}</td>
                        <td  align="justify">{{patient.state}}</td>
                        <td  align="justify">{{patient.telephone}}</td>
                        <td  align="justify">{{patient.uniqueNum}}</td>
                        <td align="justify">
                            <button class="btn btn-primary" @click="showPatientProfile(patient.id)" style="padding: 7px">Patient profile</button>
                            <button class="btn btn-success" @click="startExamination(patient.id)" style="padding: 7px; margin-left: 5px" v-if="role === 'ROLE_DOCTOR'">Start examination</button>
                        </td>
                    </tr>       
                    </tbody>
                </template>
        

            
                </table>
            </div>
        
        <br>

        <div class="modal-footer" v-if=" this.hideB ">
            <button class="btn btn-success " @click="showS($event)">Show search</button>     
        </div>      
    
    </div>

       
</div>
                
    
</template>


<script>
import axios from 'axios'
import PatientProfileVue from './PatientProfile.vue';
import jwt_decode from 'jwt-decode'
import LocalStorageService from "../LocalStorageService"

export default {

     data() {
        return {
            firstName: "",
            lastName: "",
            id: "",
            patients : [],
            hide : false,
            hideB: false,   
            valueFilter: "All",
            filterBy: ""   
        }
    },
    methods:{


        search(e){
            e.preventDefault()
            axios.post('http://localhost:8080/patient/searchPatients', {
                        "firstName":this.firstName,
                        "lastName": this.lastName,
                        "id":this.id
                      
                    }).then(response=>{
                       this.patients = response.data;
                       console.log(this.patients)
                    }).finally(()=>{ 


                    });  

        },

        hideS(e){
            e.preventDefault()
            this.hide = true;
            this.hideB = true;  

        },

        showS(e){
            e.preventDefault()
            this.hide = false; 
            this.hideB = false; 

        },
        filter(e){
            e.preventDefault()
            this.valueFilter = document.getElementById("filterV").value;
            console.log(this.valueFilter)
          
        },
        showPatientProfile(id){
            let url = '/patientProfile/';
            url+=id;
            this.$router.push(url);       
            
        },
        filterConfirm(e){
             e.preventDefault();
             this.valueFilter; // po cemu
             this.filterBy; // vrednost filtera

              axios.post('http://localhost:8080/patient/filterPatients', {
                        "filterBy":this.valueFilter,
                        "valueFilter": this.filterBy,
                                          
                    }).then(response=>{
                       this.patients = response.data;
                       console.log(this.patients)
                    }).finally(()=>{ 
                         this.filterBy = "";

                });  
        },
        startExamination(id) {
            this.$router.push('/examination/' + id);
        },
        getRole(){

           const lss = LocalStorageService.getService();

            if(lss.getAccessToken() != undefined && lss.getAccessToken() != null){          
                const token = jwt_decode(lss.getAccessToken());           
                this.role = token.roles; 
                axios.get('http://localhost:8080/user/firstLogin').then(response => {                    
                    this.firstLogin=response.data;
                });  
            }
            
		},
    },
    mounted(){
        this.getRole()
    }  
}
</script>

<style scoped>

.container{
    margin-top: 5vh;
    max-width: auto;
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
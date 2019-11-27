<template>
    <div>

       <div class="jumbobox" v-if=" !this.hide " >
        <div style="style=margin-left:2px; margin-right: 100vh">
            <div class="modal-header" style="margin-left:2px; margin-right: 100vh; ">
                <h3>Search</h3>
                <img src="http://pluspng.com/img-png/search-button-png-search-button-icon-260.png" style="width: 80px; height: 60px; background:#f1f1f1; border:none;" class="img-thumbnail" />

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
                        <div class="modal-footer">
                            <button class="btn btn-success btn-lg" @click="search($event)">Confirm</button>
                             <button class="btn btn-secondary " @click="hideS($event)">Hide search</button>
                        </div>      
                    </form>
                            
                </div>
       </div>

        <br>

        



        <div v-if=" this.patients.length ">
        
            <div class="control-group" style="margin-left:8px"> 
            <label  class="control-label" style=" font-size: 2vh"> Filter:  </label>
           
                <select id="filterV"  class="btn btn-primary" style="font-size: 2vh" @change="filter($event)" >
                        <option align="justify" >All</option>
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

           <br>

            <table class="table table-hover" >
            <thead class="thead-dark">
            <tr>
           
                <th v-if=" this.valueFilter=== 'All' || this.valueFilter=== 'Patient ID' " align="justify" >Patient ID</th>
                <th v-if=" this.valueFilter=== 'All' || this.valueFilter=== 'First Name' " align="justify" >First Name</th>
                <th v-if=" this.valueFilter=== 'All' || this.valueFilter=== 'Last Name' "  align="justify" >Last Name</th>
                <th v-if=" this.valueFilter=== 'All' || this.valueFilter=== 'Address' " align="justify" >Address</th>
                <th v-if=" this.valueFilter=== 'All' || this.valueFilter=== 'City' " align="justify" >City</th>
                <th v-if=" this.valueFilter=== 'All' || this.valueFilter=== 'State' " align="justify" >State</th>
                <th v-if=" this.valueFilter=== 'All' || this.valueFilter=== 'Telephone' " align="justify" >Telephone</th>
                <th v-if=" this.valueFilter=== 'All' || this.valueFilter=== 'Unique Number' " align="justify" >Unique Number</th>
                <th align = "justify"> Patient Profil </th> 
            </tr>
            </thead>

            <template v-if="this.valueFilter=== 'All'" >
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
                    <td align="justify"> <button class="btn btn-primary " @click="showPatientProfile(patient.id)" style="padding:7px">Patient profile</button> </td>
                </tr>       
                </tbody>
            </template>

            <template v-if="this.valueFilter=== 'Patient ID'" >
                <tbody v-for="(patient, key) in this.patients " :key='key'>
                <tr> 
                    <td  align="justify">{{patient.id}}</td>
                    <td align="justify" > <button class="btn btn-primary " @click="showPatientProfile(patient.id)" style="padding:7px">Patient profile</button> </td>
                </tr>       
                </tbody>
            </template>

             <template v-if="this.valueFilter=== 'First Name'" >
                <tbody v-for="(patient, key) in this.patients " :key='key'>
                <tr> 
                    <td  align="justify">{{patient.firstName}}</td>
                    <td align="justify"> <button class="btn btn-primary " @click="showPatientProfile(patient.id)" style="padding:7px">Patient profile</button> </td>
                </tr>       
                </tbody>
            </template>

             <template v-if="this.valueFilter=== 'Last Name'" >
                <tbody v-for="(patient, key) in this.patients " :key='key'>
                <tr> 
                    <td  align="justify">{{patient.lastName}}</td>
                    <td align="justify"> <button class="btn btn-primary " @click="showPatientProfile(patient.id)" style="padding:7px">Patient profile</button> </td>
                </tr>       
                </tbody>
            </template>

             <template v-if="this.valueFilter=== 'Address'" >
                <tbody v-for="(patient, key) in this.patients " :key='key'>
                <tr> 
                    <td  align="justify">{{patient.address}}</td>
                    <td align="justify"> <button class="btn btn-primary " @click="showPatientProfile(patient.id)" style="padding:7px">Patient profile</button> </td>
                </tr>
                </tbody>
            </template>

             <template v-if="this.valueFilter=== 'City'" >
                <tbody v-for="(patient, key) in this.patients " :key='key'>
                <tr> 
                    <td  align="justify">{{patient.city}}</td>
                    <td align="justify"> <button class="btn btn-primary " @click="showPatientProfile(patient.id)" style="padding:7px">Patient profile</button> </td> 
                </tr>       
                </tbody>
            </template>

             <template v-if="this.valueFilter=== 'State'" >
                <tbody v-for="(patient, key) in this.patients " :key='key'>
                <tr> 
                    <td  align="justify">{{patient.state}}</td>
                    <td align="justify"> <button class="btn btn-primary " @click="showPatientProfile(patient.id)" style="padding:7px">Patient profile</button> </td>
                </tr>       
                </tbody>
            </template>

            <template v-if="this.valueFilter=== 'Telephone'" >
                <tbody v-for="(patient, key) in this.patients " :key='key'>
                <tr> 
                    <td  align="justify">{{patient.telephone}}</td>
                    <td align="justify"> <button class="btn btn-primary " @click="showPatientProfile(patient.id)" style="padding:7px">Patient profile</button> </td>
                </tr>       
                </tbody>
            </template>

            <template v-if="this.valueFilter=== 'Unique Number'" >
                <tbody v-for="(patient, key) in this.patients " :key='key'>
                <tr> 
                    <td  align="justify">{{patient.uniqueNum}}</td>
                    <td align="justify"> <button class="btn btn-primary " @click="showPatientProfile(patient.id)" style="padding:7px">Patient profile</button> </td>
                </tr>       
                </tbody>
            </template>
    

            
            </table>
        <br>

        <div class="modal-footer">
            <button v-if=" this.hideB " class="btn btn-success " @click="showS($event)">Show search</button>     
        </div>      
    
    </div>

       
</div>
                
    
</template>


<script>
import axios from 'axios'
import PatientProfileVue from './PatientProfile.vue';
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
            
        }
        

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
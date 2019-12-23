<template>

     <div>
        <div class="panel-body"  >
            
        <table class="table table-striped table-hoover" >
            <thead >
                
                <tr class="thead-dark">
                    <th colspan="8"> <center> <h2> Clinic doctors</h2> </center> </th>                  
                </tr>

                <tr class="table-secondary">
                    <th align="justify"  style="font-size:18px">Id</th>
                    <th align="justify"  style="font-size:18px">Full Name</th>
                    <th align="justify"  style="font-size:18px">Address</th>
                    <th align="justify"  style="font-size:18px">City</th>
                    <th align="justify"  style="font-size:18px">Telephone</th>
                    <th align="justify"  style="font-size:18px">Specialization</th>
                    <th align="justify"  style="font-size:18px">Shift</th>
                    <th> </th>
                </tr>
            </thead>

            <template v-if="doctors != null" >
            <tbody>
                
                <tr v-for="(doctor, index) in doctors" :key="index">
                
                    <td>{{doctor.id}}</td>
                    <td>{{doctor.firstName}} {{doctor.lastName}}</td>
                    <td>{{doctor.address}}</td>
                    <td>{{doctor.city}}</td>
                    <td>{{doctor.telephone}}</td>
                    <td>{{doctor.specialization}}</td>
                    <td>{{doctor.shift}}</td>
                  
                    <td style="max-width: 80px">
                        <button type="button" class="btn btn-danger" @click="remove(doctor)" style="padding-right:32px;padding-left:32px; font-size:17px">Remove</button>
                    </td>


                </tr>
            </tbody>
            </template>
        </table>
        <div>
            <div class="modal-footer">
                 <button type="button" class="btn btn-success" @click="showAdd()" style="padding-right:32px;padding-left:32px; font-size:18px">Add New Doctor</button>
            </div>
        </div>

        
        <br>

          <div class="wrapper" v-if=" show " >
            <div style=" padding-left:2vh; padding-right:125vh">
                <div class="modal-header" style="margin-left:2px; margin-right: 100vh; ">
                    <h3>Add New Doctor</h3>
                </div>
            </div>
                    
                <div class="panel-body" style="padding-left:2vh; padding-right: 125vh; " >
                    <br>
                    <form accept-charset="UTF-8" role="form"  >

                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label  class="control-label" style=" font-size: 2vh" >First Name:</label>
                               <input class="form-control"  style=" font-size: 2vh; "  placeholder="First Name..." v-model="nameFF" />
                            </div>
                            <div class="form-group col-md-6">
                                <label  class="control-label" style=" font-size: 2vh">Last Name:</label>
                                <input class="form-control"  style=" font-size: 2vh"  placeholder="Last Name..." v-model="nameLF" />
                            </div>
                        </div>

                        <div class="control-group" >
                            <label  class="control-label" style=" font-size: 2vh" >Email:</label>
                            <div class="form-group">
                                <input class="form-control"  style=" font-size: 2vh; "  placeholder="Email..." type="email" v-model="emailF" />
                            </div>
                        </div>

                         <div class="form-row">
                            <div class="form-group col-md-6">
                                <label  class="control-label" style=" font-size: 2vh">Address:</label>
                                <input class="form-control"  style=" font-size: 2vh"  placeholder="Address..." v-model="addressF" />
                            </div>
                            <div class="form-group col-md-6">
                                <label  class="control-label" style=" font-size: 2vh" >Telephone:</label>
                                <input class="form-control"  style=" font-size: 2vh; "  placeholder="Telephone..." v-model="telephoneF" />
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label  class="control-label" style=" font-size: 2vh">City:</label>
                                <input class="form-control"  style=" font-size: 2vh; "  placeholder="State..." v-model="cityF" />
                            </div>
                            <div class="form-group col-md-6">
                                <label  class="control-label" style=" font-size: 2vh" >State:</label>
                                <input class="form-control"  style=" font-size: 2vh; "  placeholder="State..." v-model="stateF" />
                            </div>
                        </div>

                        <div class="control-group" >
                            <label  class="control-label" style=" font-size: 2vh" >Specialization:</label>
                            <div class="form-group">
                                <input class="form-control"  style=" font-size: 2vh; "  placeholder="Specialization..." v-model="specializationF" />
                            </div>
                        </div>

                        
                        <div class="form-row">
                            <div class="form-group col-md-8">
                                <label  class="control-label" style=" font-size: 2vh">Unique Number:</label>
                                <input class="form-control"  style=" font-size: 2vh"  placeholder="Unique Number..." v-model="uniqueNumF" />
                            </div>
                            <div class="form-group col-md-4">
                                <label  class="control-label" style=" font-size: 2vh">Shift:</label>
                                <input class="form-control"  style=" font-size: 2vh"  placeholder="Shift..." v-model="shiftF" />
                            </div>
                        </div>
                      
                        <br>
                        <div class="modal-footer" >
                            <button class="btn btn-success " style="font-size:20px" @click="add($event)">Add</button>
                            <button class="btn btn-danger " @click="cancel($event)">Cancel</button>
                        </div>      
                    </form>
                                
                </div>

                 <br>
        </div>




        <br>

        <div class="jumbobox"  >
            <div style=" margin-left:6px; margin-right: 100vh">
                <div class="modal-header" style="margin-left:2px; margin-right: 100vh; ">
                    <h3>Search</h3>
                </div>
            </div>
                    
                <div class="panel-body" style="margin-left:8px; margin-right: 100vh; " >
                    <br>
                    <form accept-charset="UTF-8" role="form"  >

                        <div class="control-group" >
                            <label  class="control-label" style=" font-size: 2vh" >First Name:</label>
                            <div class="form-group">
                                <input class="form-control"  style=" font-size: 2vh; "  placeholder="First Name..." v-model="nameFS" />
                            </div>
                        </div>

                        <div class="control-group" >
                            <label  class="control-label" style=" font-size: 2vh" >Last Name:</label>
                            <div class="form-group">
                                <input class="form-control"  style=" font-size: 2vh; "  placeholder="Last Name..." v-model="nameLS" />
                            </div>
                        </div>

                        <div class="control-group">
                            <label  class="control-label" style=" font-size: 2vh">Doctor ID:</label>
                            <div class="form-group">
                                <input class="form-control"  style=" font-size: 2vh"  placeholder="Doctor ID..." v-model="idS" />
                            </div>
                        </div>
                        
                        <br>
                        <div class="modal-footer" >
                            <button class="btn btn-success btn-lg" @click="search($event)">Confirm</button>
                        </div>      
                    </form>
                                
                </div>
        </div>

                            
    </div>
       

</div>
    
</template>

<script>
import axios from 'axios'
import {funToastr} from "../../toastr.js"
export default {
    
      data() {
        return {
            user: [],
            doctors: [],
            nameFF:"",
            nameLF:"",
            emailF:"",
            addressF:"",
            telephoneF:"",
            cityF:"",
            stateF:"",
            specializationF:"",
            uniqueNumF:"",
            shiftF:"",
            show : false,
            idS:"",
            nameFS:"",
            nameLS:""


        }
    },
    methods:{
       
        getDoctors(){
            axios.get('http://localhost:8080/doctor/getDoctors').then(response => { this.doctors = response.data; })
        },
        search(e){
            e.preventDefault();
            axios.post('http://localhost:8080/ca/searchDoctor', { 
                "firstName": this.nameFS,
                "lastName": this.nameLS,
                "id": this.idS,               
                
                }).then(response => { this.doctors = response.data; })
        },
        showAdd(){
            
            this.idF = "";
            this.nameFF = "";
            this.nameLF = "";
            this.emailF = "";
            this.addressF = "";
            this.telephoneF = "";
            this.cityF = "";
            this.stateF = "";
            this.specializationF = "";
            this.uniqueNumF = "";
            this.shiftF = "";
            this.show = true;

        },
        cancel(){
            this.show = false;
        },

        add(e){

            e.preventDefault();
            axios.post('http://localhost:8080/ca/addDoctor', { 
                
                "firstName": this.nameFF,
                "lastName": this.nameLF,
                "email": this.emailF,
                "address": this.addressF,
                "telephone": this.telephoneF,
                "city": this.cityF,
                "state": this.stateF,
                "specialization": this.specializationF,
                "uniqueNum": this.uniqueNumF,
                "shift": this.shiftF,       

             }).then(response => { 
            
                if(response.status == 200){
                    this.getDoctors();
                    this.show = false;
                    funToastr("s","Doctor successfuly added to clinic!","Doctor!");
                }else
                    funToastr("d",response.data,"Doctor!");

            
            })

        },
       
        remove(doctor){
         
            axios.post('http://localhost:8080/ca/removeDoctor', { "id": doctor.id, }).then(response => { 
            
                if(response.status == 200){
                    this.getDoctors();
                    this.show = false;
                    funToastr("s","Doctor successfuly removed from clinic!","Room!");
                }else
                    funToastr("d",response.data,"Doctor!");

            
            })


        },
        
    },
    created() {
        this.getDoctors();
    }

}
</script>

<style scoped>

.modal-header{

  border-bottom: 1px solid #8d8d8d;
  width: 100%;
  font-size: 20px;
  font-size: 3vh

}

.modal-footer{

  border-top: 1px solid #8d8d8d;
  width: 100%;
  font-size: 20px;
  font-size: 3vh
}


</style>
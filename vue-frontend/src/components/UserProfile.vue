<template>

    

   <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="card">

                    <div class="card-body">
                        <div class="card-title mb-4">
                            <div class="d-flex justify-content-start">
                                <div class="image-container">
                                    <img v-if=" this.role === 'ROLE_DOCTOR'"  src="../../public/assets/doctor.jpg" style="width: 150px; height: 150px margin-top: -2vh " class="img-thumbnail" />
                                    <img v-if=" this.role === 'ROLE_NURSE'"  src="../../public/assets/nurse.png" style="width: 150px; height: 150px margin-top: -2vh " class="img-thumbnail" />
                                </div>
                               
                                <div class="userData ml-3">
                                    <h2 class="d-block" style="font-size: 1.5rem; font-weight: bold">{{user.email}}</h2>
                                    <h4 v-if=" this.role === 'ROLE_DOCTOR' || this.role === 'ROLE_PATIENT' " class="d-block" style="font-size: 1.5rem; font-weight: bold">Doctor</h4>
                                    <h4 v-else-if=" this.role === 'ROLE_NURSE'" class="d-block" style="font-size: 1.5rem; font-weight: bold">Nurse</h4>
                                    <h6 class="d-block" style="font-size: 1rem; font-weight: bold"> {{this.holiday}} </h6>
                                    <div class="middle" v-if="this.role === 'ROLE_NURSE' || this.role === 'ROLE_DOCTOR' ">
                                        <button v-if="this.holiday = 'Not On holiday' " type="button" class="btn btn-warning btn-block " @click="holidayReq" style=" border-radius: 6px;  color: black; padding:5px " >Request holiday/time off</button>
                                    </div>
                                </div>
                                <div class="ml-auto" style="margin-top: -2vh">
                                    <div class="middle" v-if=" this.role === 'ROLE_DOCTOR' || this.role === 'ROLE_NURSE'">
                                        <br>
                                        <button  type="button" class="btn btn-success btn-block " @click="changeD" style=" border-radius: 6px; padding:8px ">Change data </button>
                                    </div>
                                    <div class="middle" v-if=" this.role === 'ROLE_DOCTOR' || this.role === 'ROLE_NURSE' ">
                                        <br>
                                        <button  type="button" class="btn btn-dark btn-block " @click="changePassword" style=" border-radius: 6px;  color: white; padding:8px" >Change password </button>
                                    </div>
                                    <div class="middle" v-if=" this.role === 'ROLE_PATIENT' ">
                                        <br>
                                        <button  type="button" class="btn btn-dark btn-block " @click="sendExaminationRequest(user.id)" style=" border-radius: 6px;  color: white; padding:8px" >Send request for procedure </button>
                                    </div>
                                    <div class="middle" v-if="this.role === 'ROLE_NURSE' || this.role === 'ROLE_DOCTOR' ">
                                        <br>
                                        <button type="button" class="btn btn-primary btn-block " @click="search" style=" border-radius: 6px;  color: white; padding:8px " >Search patients </button>
                                    </div>
                                   
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-12">
                                <ul class="nav nav-tabs mb-4" id="myTab" role="tablist">
                                    <li class="nav-item">
                                        <a class="nav-link active" id="basicInfo-tab" data-toggle="tab" href="#basicInfo" role="tab" aria-controls="basicInfo" aria-selected="true">Basic Info</a>
                                    </li>
                                </ul>
                                <div class="tab-content ml-1" id="myTabContent">
                                    <div class="tab-pane fade show active" id="basicInfo" role="tabpanel" aria-labelledby="basicInfo-tab">
                                        

                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">Full Name</label>
                                            </div>
                                            <div class="col-md-8 col-6">
                                                {{user.firstName +" "+ user.lastName}}
                                            </div>
                                        </div>
                                        <hr />

                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">Address</label>
                                            </div>
                                            <div class="col-md-8 col-6">
                                               {{user.address}}
                                            </div>
                                        </div>
                                        <hr />
                                        
                                        
                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">City</label>
                                            </div>
                                            <div class="col-md-8 col-6">
                                                 {{user.city}}
                                            </div>
                                        </div>
                                        <hr />
                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">State</label>
                                            </div>
                                            <div class="col-md-8 col-6">
                                                 {{user.state}}
                                            </div>
                                        </div>
                                        <hr />
                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">Telephone</label>
                                            </div>
                                            <div class="col-md-8 col-6">
                                                 {{user.telephone}}
                                            </div>
                                        </div>
                                        <hr />
                                         <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">Unique Number</label>
                                            </div>
                                            <div class="col-md-8 col-6">
                                                 {{user.uniqueNum}}
                                            </div>
                                        </div>
                                        <hr />
                                         <div v-if=" this.role === 'ROLE_DOCTOR' || this.role === 'ROLE_PATIENT' " class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">Specialization</label>
                                            </div>
                                            <div class="col-md-8 col-6">
                                                 {{user.specialization}}
                                            </div>
                                        </div>
                                         <hr />
                                        <div v-if=" this.role === 'ROLE_DOCTOR' || this.role === 'ROLE_PATIENT' " class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">Work Time</label>
                                            </div>
                                            <div class="col-md-8 col-6">
                                                 {{workTime}}
                                            </div>
                                        </div>
                                       

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

             <div class="row" v-if="this.role === 'ROLE_PATIENT' && this.user.email">
            <Calendar v-bind:doctor="this.user.email" v-bind:date="this.wantedDate"></Calendar>
        </div>


        </div>

        

    </div>

    
</template>

<script>
import axios from 'axios'
import Calendar from './Calendar'
import jwt_decode from 'jwt-decode'
import {funToastr} from "../toastr.js"
import LocalStorageService from "../LocalStorageService";

export default {


   data() {
        return {
            user : [],
            holiday : "",
            role: "",
            workTime: "",
            idDoc: '',
            wantedDate: '',
                   
        }
    },
    props:{
        id:String
    },
    components:{
        Calendar
    },
    methods:{
        getDoctor(){
            axios.get('http://localhost:8080/doctor/getInfo').then(response => { 
                this.user = response.data;
                if(this.user.shift === 1)
                    this.workTime = "06:00 - 14:00";
                else if(this.user.shift === 2)
                    this.workTime = "14:00 - 22:00";
             })
        },
        getDoctorId(){
            if(this.id.includes("T")){
                let s = this.id.split("T")
                this.idDoc=s[0]
                this.wantedDate=s[1]
            }else{
                this.idDoc=this.id
            }

            let url = 'http://localhost:8080/doctor/';
            url += this.idDoc
            axios.get(url).then(response => { 
                this.user = response.data;
                if(this.user.shift === 1)
                    this.workTime = "06:00 - 14:00";
                else if(this.user.shift === 2)
                    this.workTime = "14:00 - 22:00";
                    console.log('doktor: ' + this.user.email + '   datum : ' + this.wantedDate)
                
            })
            .catch(error=>console.log(error))
        },

        getNurse(){
            axios.get('http://localhost:8080/nurse/getInfo').then(response => { this.user = response.data; })
        },

        changeD(e){
            e.preventDefault()
            this.$router.push("/changeData");
        },

        changePassword(e){
            e.preventDefault()
            this.$router.push("/passwordChange");
        },

        search(e){
            e.preventDefault()
            this.$router.push("/search");
        },
        
        getRole(){

           const lss = LocalStorageService.getService();

            if(lss.getAccessToken() != undefined && lss.getAccessToken() != null){          
                const token = jwt_decode(lss.getAccessToken());           
                this.role = token.roles;  
            }
            
        },
        holidayReq(e){
            e.preventDefault();
            this.$router.push({name: 'Holiday', params: {staff:this.user}});
   
        },
        sendExaminationRequest(id){
          
            let url = "/patient/requests/" + id +"/"+this.user.specialization
            axios
            .post(url)
            .then(response=>{
               funToastr("s","Successfuly appointed Examination!");
               this.$router.push("/")
            })
            .catch(error=>{
                console.log(error)
            })
        }
       

    },
    created(){
       
        this.getRole();

        if(this.role === "ROLE_DOCTOR"){
            this.getDoctor();

        }else if(this.role === "ROLE_NURSE")
            this.getNurse();
        else if(this.role === "ROLE_PATIENT")
            this.getDoctorId();
        if(this.user.onVacation === true)
            this.holiday = "On holiday";
        else
            this.holiday = "Not On holiday";
     
    }


}
</script>

<style scoped>


    .container{
        margin-top: 5vh;
        max-width: auto;
        padding: 40px;
        background: #f1f1f1;
        border-radius: 8px;
        align-self: center;
        border: 1px dotted grey;
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif

    }

   

</style>
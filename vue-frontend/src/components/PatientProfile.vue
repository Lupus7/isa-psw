<script>
import jwt_decode from 'jwt-decode'
import axios from 'axios';

import LocalStorageService from "../LocalStorageService";
export default {
    data() {
        return {
            userEmail : "",  
            role: ''  ,
            user:{},
            rows: []
        }
    },

    props:{
        id:{
            type: String
        }

    },
    methods:{
		getPatient(){
            axios.get('http://localhost:8080/patient/getInfo').then(response => { this.user = response.data;})
        },

        getPatientId(){
            let url = 'http://localhost:8080/patient/';
            url += this.id
            axios.get(url).then(response => { this.user = response.data;})
        },
		checkMedicalFile(){
            this.$router.push({name:'MedicalFile', params: {medstaff:this.user,patient:this.user}});

		},
		getRole(){

           const lss = LocalStorageService.getService();

            if(lss.getAccessToken() != undefined && lss.getAccessToken() != null){          
                const token = jwt_decode(lss.getAccessToken());           
                this.role = token.roles;  
            }
            
		},
		changeD(){

		},
    },
    created(){
		this.getRole()
		if(this.role === "ROLE_PATIENT")
            this.getPatient()
        if(this.role === "ROLE_DOCTOR" || this.role === "ROLE_NURSE")
            this.getPatientId()
		
       /* const token = jwt_decode(localStorage.getItem("user"));
        this.userEmail = token.sub
        this.role = token.roles
        axios
        .get('http://localhost:8080/patient/getUser')
        .then(response=>{
            this.user = response.data
        })
        */
        
    }
}



</script>

<template>
     <div class="container">

        <div class="row">
            <div class="col-12">
                <div class="card">

                    <div class="card-body">
                        <div class="card-title mb-4">
                            <div class="d-flex justify-content-start">
                                <div class="image-container">
                                    <img class="editable img-responsive" style="width: 150px; height: 150px; margin-bottom: 5px" id="avatar2" src="../../public/assets/patient.png">
                                    <div v-if="this.role === 'ROLE_PATIENT'" class="middle">
                                        <button type="button" class="btn btn-secondary btn-block" v-on:click="changeD">Change data </button>
                                    </div>
                                     <div v-if="this.role === 'ROLE_DOCTOR' || this.role === 'ROLE_NURSE'" class="middle">
                                        <button type="button" class="btn btn btn-block" style="background:indigo; color:white" v-on:click="checkMedicalFile()">Medical File </button>
                                    </div>
                                </div>
                                <div class="userData ml-3">
                                    <h2 class="d-block" style="font-size: 1.5rem; font-weight: bold">{{user.email}}</h2>
                                    <h4  class="d-block" style="font-size: 1.5rem; font-weight: bold">Patient</h4>
                                 

                                </div>
                                <div class="ml-auto">
                                    <input type="button" class="btn btn-primary d-none" id="btnDiscard" value="Discard Changes" />
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

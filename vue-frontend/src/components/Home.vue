<template>
    <div>
        <password-change v-if="this.firstLogin === true  "> PasswordChange </password-change>
        <patientHome v-else-if="this.role=='ROLE_PATIENT' && this.firstLogin === false " />
        <NurseHome v-else-if="this.role=='ROLE_NURSE' && this.firstLogin === false" />
        <DoctorHome v-else-if="this.role=='ROLE_DOCTOR' && this.firstLogin === false"/>
        <div class="container" v-else-if="this.role=='ROLE_CADMIN' && this.firstLogin === false">
            <Calendar mode="room"/>
        </div>

    </div>
</template>

<script>

import patientHome from './PatientHome'
import jwt_decode from 'jwt-decode'
import LocalStorageService from "../LocalStorageService"
import NurseHome from './NurseHome'
import DoctorHome from './DoctorHome'
import Calendar from './Calendar'
import PasswordChange from './PasswordChange'
import axios from 'axios'


export default {
    data(){
        return{
            role: [],
            firstLogin: ""
        }
    },
    methods:{
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
    },
    components:{
        PasswordChange,
        patientHome,
        NurseHome,
        DoctorHome,
        Calendar
    }

}


</script>
<template>
    <div>
        <patientHome v-if="this.role=='ROLE_PATIENT' " />
        <NurseHome v-if="this.role=='ROLE_NURSE'" />
    </div>
</template>

<script>

import patientHome from './PatientHome'
import jwt_decode from 'jwt-decode'
import LocalStorageService from "../LocalStorageService"
import NurseHome from './NurseHome'

export default {
    data(){
        return{
            role: []
        }
    },
    methods:{
        getRole(){

           const lss = LocalStorageService.getService();

            if(lss.getAccessToken() != undefined && lss.getAccessToken() != null){          
                const token = jwt_decode(lss.getAccessToken());           
                this.role = token.roles; 
            }
            
		},
    },
    mounted(){
        this.getRole()
    },
    components:{
        patientHome,
        NurseHome
    }

}


</script>
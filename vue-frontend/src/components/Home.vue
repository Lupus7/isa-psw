<template>
    <div>
        <patientHome v-if="this.role=='ROLE_PATIENT' " />
    </div>
</template>

<script>

import patientHome from './PatientHome'
import jwt_decode from 'jwt-decode'
import LocalStorageService from "../LocalStorageService";
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
                alert(this.role) 
            }
            
		},
    },
    mounted(){
        this.getRole()
    },
    components:{
        patientHome
    }

}


</script>
<template>
    <div class="container">
        <div class="row">

            <table class="table table-hover" style=" margin-left:10px;" >
                <thead >

                <tr  class="thead-dark">
                    <th colspan="3" > <center> <h2> Medical File </h2> </center> </th> 
                    <th colspan="3"> <center> <h3> Patient: {{patient.firstName+" "+patient.lastName}} </h3> </center> </th>                  
                </tr>

                <tr class="table-secondary">
            
                    <th type="link" align="justify" style="font-size:18px"  >Diagnosis Name</th>
                    <th type="link" align="justify"  style="font-size:18px" >Doctor</th>
                    <th type="link"  align="justify" style="font-size:18px" >Date</th>
                    <th type="link"  align="justify" style="font-size:18px" >Diagnosis description</th>
                </tr>
                </thead>

                <template v-if="canWatch" >
                    <tbody v-for="(file, key) in this.medFile " :key='key'>
                    <tr> 
                        <td  align="justify">{{file.diagnosisName}}</td>
                        <td  align="justify">{{file.name}}</td>
                        <td  align="justify"></td>
                        <td  align="justify">{{file.diagnosisDesc}}</td>
                    </tr>       
                    </tbody>
                </template>
        

            
                </table>

        </div>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data(){
        return{
            canWatch:true,
            medFile:[],
        }
    },
    props:{
        patient:{
            type: Object
        },
        medstaff:{
            type: Object
        }
    },
    methods:{
        canWatchMedicalFile(){

        },
        getMedFile(){
            let url = 'http://localhost:8080/patient/getMedFile/';
            url += this.patient.medicalFile.id;
            axios.get(url).then(response => { 
                this.medFile = response.data; 
               
            })
        }
    },
    created(){
        this.getMedFile();
    }
}
</script>

<style scoped>

</style>
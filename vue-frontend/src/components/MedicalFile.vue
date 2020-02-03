<template>
    <div class="container">
        <div class="row">

            <table class="table table-hover" style=" margin-left:10px;" >
                <thead >

                <tr  class="thead-dark">
                    <th colspan="2" > <center> <h2> Medical File </h2> </center> </th> 
                    <th colspan="2"> <center> <h3> Patient: {{patient.firstName+" "+patient.lastName}} </h3> </center> </th>                  
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
                        <td  align="justify">{{file.doctorName}}</td>
                        <td  align="justify" v-on="convertDate(file.date)">{{dateConv}}</td>
                        <td  align="justify">{{file.diagnosisDesc}}</td>
                    </tr>       
                    </tbody>
                </template>
        

            
                </table>

        </div>
        <br>
        <div v v-if="this.medstaff==='examination'" class="modal-footer">
            <button class="btn btn-outline-primary" v-on:click="goBack()">Go back to Examination</button>
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
            dateConv:''
        }
    },
    props:{
        patient:{
            type: Object
        },
        medstaff:{
            type: String
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
        },
        convertDate(d){
            let dat = new Date(d);      
            let s = dat.toLocaleString().split(",");
            let s1 = s[0].split("/");
            let day = s1[1]
            let month = s1[0]
            let year = s1[2]
            if(day.length === 1)
                day = '0'+day.toString()
            if(month.length === 1)
                month = '0'+month.toString()
            this.dateConv = day+"."+month+"."+year
        },
        goBack(){
            this.$router.push({name:'Examination', params: {id:""+this.patient.id}});

        }
    },
    created(){
        this.getMedFile();
    }
}
</script>

<style scoped>

</style>
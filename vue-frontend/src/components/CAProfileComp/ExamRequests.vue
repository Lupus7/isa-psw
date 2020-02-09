<template>
    <div>
        <div class="panel-body"  >
            
        <table class="table table-striped table-hoover" >
            <thead >
                
                <tr class="thead-dark">
                    <th colspan="8"> <center> <h2> Examination Requests </h2> </center> </th>                  
                </tr>

                <tr class="table-secondary">
                    <th align="justify"  style="font-size:18px">Id</th>
                    <th align="justify"  style="font-size:18px">Examination Type</th>
                    <th align="justify"  style="font-size:18px">Doctor</th>
                    <th align="justify"  style="font-size:18px">Patient</th>
                    <th align="justify"  style="font-size:18px">Date</th>
                    <th> </th>
                </tr>
            </thead>

            <template v-if="examins != null" >
            <tbody>
                
                <tr v-for="(ex, index) in examins" :key="index">
                
                    <td>{{ex.id}}</td>
                    <td>{{ex.examinationtype.name}}</td>
                    <td>{{ex.doctor.firstName}} {{ex.doctor.lastName}} </td>
                    <td>{{ex.patient.firstName}} {{ex.patient.lastName}} </td>
                    <td v-on="dateConvert(ex.date)">{{dateConv}}</td>

                    <td style="max-width: 80px">
                        <button type="button" class="btn btn-primary" @click="arrangeRoom(ex)" style="padding-right:32px;padding-left:32px; font-size:17px">Arrange Room</button>
                    </td>


                </tr>
            </tbody>
            </template>
        </table>

        <table class="table table-striped table-hoover" >
            <thead >
                
                <tr class="thead-dark">
                    <th colspan="8"> <center> <h2> Operation Requests </h2> </center> </th>                  
                </tr>

                <tr class="table-secondary">
                    <th align="justify"  style="font-size:18px">Id</th>
                    <th align="justify"  style="font-size:18px">Operation Type</th>
                    <th align="justify"  style="font-size:18px">Patient</th>
                    <th align="justify"  style="font-size:18px">Date</th>
                    <th> </th>
                </tr>
            </thead>

            <template v-if="operations != null && operations.length !== 0" >
            <tbody>
                
                <tr v-for="(ex, index) in operations" :key="index">
                
                    <td>{{ex.id}}</td>
                    <td>{{ex.operationtype.name}}</td>
                    <td>{{ex.patient.firstName}} {{ex.patient.lastName}} </td>
                    <td v-on="dateConvert(ex.date)">{{dateConv}}</td>

                    <td style="max-width: 80px">
                        <button type="button" class="btn btn-primary" @click="arrangeRoom(ex)" style="padding-right:32px;padding-left:32px; font-size:17px">Arrange Room</button>
                    </td>


                </tr>
            </tbody>
            </template>
        </table>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
import {funToastr} from "../../toastr.js"
export default {
      data() {
        return {
           examins:[],
           operations:[],
           dateConv:"",
           
        }
    },
    methods:{
        getNextExamins(){
            axios.get('http://localhost:8080/ca/getNextExamins').then(response => { this.examins = response.data; })
        },
        getNextOperations(){
            axios.get('http://localhost:8080/ca/getNextOperations').then(response => { this.operations = response.data; })
        },
        dateConvert(d){
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
        arrangeRoom(ex){
            this.$router.push({name: 'Rooms', params: {examReq: ex }});
            
        }
    },
    created(){
        this.getNextExamins();
        this.getNextOperations();
    }
}
</script>

<style scoped>

</style>
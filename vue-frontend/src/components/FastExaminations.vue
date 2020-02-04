<template>
<div>
   
    <br>
        <br>
         <div v-if="examinations.length ==0">
        <h3>No fast examinations yet!</h3>
    </div>
    <table class="table table-sm table-dark" v-if="examinations.length>0">
         
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Type</th>
      <th scope="col">Clinic</th>
      <th scope="col">Doctor</th>
      <th scope="col">Specialization</th>
      <th scope="col">Time</th>
      <th scope="col">Examination Type</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="e in examinations" :key="e.id">
      <th scope="row">{{e.id}}</th>
      <td>{{e.type}}</td>
      <td>{{e.clinic}}</td>
      <td>{{e.doctor}}</td>
      <td>{{e.doctorSpec}}</td>
      <td>{{e.date}}</td>
      <td>{{e.examinationType}}</td>
      <td><button type="button" class="btn btn-primary" @click="arrange(e.id)">Arrange</button></td>
    </tr>
  </tbody>
</table>
</div>
</template>

<script>
import axios from 'axios';
import jwt_decode from 'jwt-decode'
import {funToastr} from "../toastr.js"
import LocalStorageService from "../LocalStorageService";

export default {
    data(){
        return {
            examinations : {},

        }
    },
    methods:{
        arrange(id){
            let url = "/patient/fastExams/" + id
            console.log(url)
            
            axios
            .post(url)
            .then(response=>{
                funToastr("s","Successfuly appointed examination!","Appoint!");
                this.$router.push("/")
            })
            
        }
    },
    created(){
        console.log("Hello")
        axios
        .get("/patient/getFastExaminations")
        .then(response=>{
            this.examinations = response.data
            for(var i=0;i<this.examinations.length;i++){
              let c=this.examinations[i]
              //  alert(c.type)
                var myDate = new Date( c.date );
                //alert(myDate.toUTCString());
                var mytime=myDate.toUTCString()
                this.examinations[i].date = mytime
            }
            console.log("Successfuly")
        })
        .catch(error => {
            console.log(error);
        })
    }
}
</script>
<style scoped>

</style>
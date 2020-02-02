<template>
    <div class="row-12">

        <div class="col-3"/>
        <div class="col-6">

                
                <div class="container" >
                    <div class="modal-header">
                        <h3> Define Fast Examination </h3>
                    </div>
                        
                <div class="panel-body">
                    <br>
                    <form accept-charset="UTF-8" role="form">
                    

                        <div class="form-row">
                            
                            <div class="form-group col-md-4">
                               <label >Room:</label>
                                 <div class="form-group">
                                     <select class="form-control" v-model="roomForm" @change="check()">
                                        <option v-for="(r, index) in this.rooms" :key="index" :value="r" > {{r.name}} </option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group col-md-1"/>

                           <div class="form-group col-md-4">
                                <label>Doctor:</label>
                                     <select class="form-control" v-model="doctorForm" @change="getExamTypes()" :disabled="boolDoctor" >
                                         <option v-for="(d, index) in this.doctors" :key="index" :value="d"> {{d.firstName+" "+d.lastName}} </option>
                                    </select>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-4">
                               <label >Time:</label>
                                 <div class="form-group">
                                     <select class="form-control" v-model="timeForm" :disabled="boolTime">
                                        <option v-for="(ap, index) in this.freeApps" :key="index"> {{ap}} </option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group col-md-1"/>
                           <div class="form-group col-md-4">
                                <label>Date:</label>
                                <div class="form-group">
                                    <input class="form-control" type="date" v-model="dateForm" @change="check()"/>
                                </div>
                            </div>
                        </div>

                        <div class="form-row">
                       
                            <div class="form-group col-md-4">
                               <label >Examination Type:</label>
                                 <div class="form-group">
                                     <select class="form-control" v-model="examTypeForm" :disabled="boolTime" >
                                        <option v-for="(ex, index) in this.examTypes" :key="index"  :value="ex" > {{ex.name}} </option>
                                    </select>
                                </div>
                            </div>

                        </div>

                        <div class="modal-footer">
                            <div class="row">
                                <div class="col" >
                                    <button  class="btn btn-success btn-block " style="width:150px; height:42px" @click="arrangeFastExamin()"> Arrange </button>
                                </div> 
                                <div class="col"> 
                                    <button class="btn btn-danger btn-block" style="width:120px;height:42px" @click="back()"> Back</button> 
                                </div>
                            </div>
                        </div>
                        
                    </form>
                  
                    
                </div>
            </div>
        </div>
        <div class="col-3"/>

    
    </div>

    
</template>

<script>
import axios from 'axios'
import {funToastr} from "../../toastr.js"
export default {
   
    data() {
        return {
            rooms:[],
            doctors:[],
            examTypes:[],
            roomForm:"",
            doctorForm:"",
            examTypeForm:"",
            timeForm:"",
            dateForm:"",
            freeAp:[
                "06:00-07:00","07:00-08:00","08:00-09:00","09:00-10:00","10:00-11:00","11:00-12:00",
                "12:00-13:00","13:00-14:00","14:00-15:00","15:00-16:00","16:00-17:00","17:00-18:00",
                "18:00-19:00","19:00-20:00","20:00-21:00","21:00-22:00"
            ],
            freeApps:[],
            boolDoctor:true,
            boolTime:true
        }
        
    },
    methods:{
        
        changeToInt(){

            if(this.timeForm === "06:00-07:00"){
                this.timeForm = 6;
            }else if(this.timeForm === "07:00-08:00"){
                this.timeForm = 7;
            }else if(this.timeForm === "08:00-09:00"){
                this.timeForm = 8;
            }else if(this.timeForm === "09:00-10:00"){
                this.timeForm = 9;
            }else if(this.timeForm === "10:00-11:00"){
                this.timeForm = 10;
            }else if(this.timeForm === "11:00-12:00"){
                this.timeForm = 11;
            }else if(this.timeForm === "12:00-13:00"){
                this.timeForm = 12;
            }else if(this.timeForm === "13:00-14:00"){
                this.timeForm = 13;
            }else if(this.timeForm === "14:00-15:00"){
                this.timeForm = 14;
            }else if(this.timeForm === "15:00-16:00"){
                this.timeForm = 15;
            }else if(this.timeForm === "16:00-17:00"){
                this.timeForm = 16;
            }else if(this.timeForm === "17:00-18:00"){
                this.timeForm = 17;
            }else if(this.timeForm === "18:00-19:00"){
                this.timeForm = 18;
            }else if(this.timeForm === "19:00-20:00"){
                this.timeForm = 19;
            }else if(this.timeForm === "20:00-21:00"){
                this.timeForm = 20;
            }else if(this.timeForm === "21:00-22:00"){
                this.timeForm = 21;
            }

        },
         changeToString(int){
            if(int === 6)
                return "06:00-07:00"
            else if(int === 7)
                return "07:00-08:00"
            else if(int === 8)
                return "08:00-09:00"
            else if(int === 9)
                return "09:00-10:00"
            else if(int === 10)
                return "10:00-11:00"    
            else if(int === 11)
                return "11:00-12:00"
            else if(int === 12)
                return "12:00-13:00"
            else if(int === 13)
                return "13:00-14:00"
            else if(int === 14)
                return "14:00-15:00"
            else if(int === 15)
                return "15:00-16:00"
            else if(int === 16)
                return "16:00-17:00"
            else if(int === 17)
                return "17:00-18:00"    
            else if(int === 18)
                return "18:00-19:00"
            else if(int === 19)
                return "19:00-20:00"
            else if(int === 20)
                return "20:00-21:00"
            else if(int === 21)
                return "21:00-22:00"
           
          

        },
        getClinicRooms(){
             let url = 'http://localhost:8080/ca/getClinicRooms';
             axios.get(url).then(response => { 
                this.rooms = response.data;               
             })
        },
        getClinicDoctors(){
            let url = 'http://localhost:8080/ca/getClinicDoctors';
            axios.get(url).then(response => { 
                this.doctors = response.data;               
             })
        },
        getExamTypes(){
            if(this.doctorForm !== ''){
                this.boolTime = false;

                this.freeApps = []
                if(this.dateForm !=='' && this.roomForm !=='' ){
                   if(this.doctorForm.shift == 1){

                    for( var j = 0; j <= 7; j++){ 
                        this.freeApps.push(this.freeAp[j])                       
                    }

                    }else if(this.doctorForm.shift == 2){

                        for( var i = 7; i <= 15; i++){ 
                            this.freeApps.push(this.freeAp[i])                
                        }
                    }

                    this.getFreeDate();
                }
                

                console.log(this.doctorForm.specialization)
                let url = 'http://localhost:8080/ca/getExaminationTypes/';
                url += this.doctorForm.specialization;
                axios.get(url).then(response => { 
                    this.examTypes = response.data;
               
             })
            }
            

        },
        getFreeDate(){
            let takenAp = []
             for(let td of this.roomForm.takenDates){
                let dat = new Date(td.date);      
                let s = dat.toLocaleString().split(",");
                let s1 = s[0].split("/");
                let day = s1[1]
                let month = s1[0]
                let year = s1[2]
                if(day.length === 1)
                    day = '0'+day.toString()
                if(month.length === 1)
                    month = '0'+month.toString()
                let dateTD = year+"-"+month+"-"+day
                
                if(dateTD === this.dateForm){

                    takenAp.push(this.changeToString(td.time));
                }
            }

            for(let t of takenAp){
               for( var i = 0; i < this.freeApps.length; i++){ 
                    if ( t === this.freeApps[i]) {
                        this.freeApps.splice(i, 1); 
                        i--;
                    }
                }
            }

        },
        check(){
            if(this.roomForm !=='' && this.dateForm !==''){
                this.boolDoctor = false;
            }
            if(!this.boolDoctor){
                this.timeForm = '';
                this.examTypeForm = '';
                this.getExamTypes();
            }
        },
      

        arrangeFastExamin(){

            this.changeToInt();


            axios.post('http://localhost:8080/ca/defineFastExamin', {
                "examTypeId": this.examTypeForm.id,
                "doctorId": this.doctorForm.id,
                "date": this.dateForm,
                "time": this.timeForm,
                "roomId": this.roomForm.id,
            }).then(response => { 
                 if(response.status == 200){
                        funToastr("s",response.data,"Fast Examination!");
                    }else {
                        funToastr("d","Error!","Fast Examination!");
                        return;
                    }
             }).finally(()=>{ 

                     setTimeout(() =>{                    
                 
                        this.$router.push("/CAProfile");       
                        this.$router.go("/CAProfile");
                             
                    },1600);

                });   

        },
        back(){
            this.$router.push("/caProfile");
        },
        


        
    },
    
    created(){
      this.getClinicDoctors();
      this.getClinicRooms();
    }
}
</script>

<style scoped>

.modal-header{

  border-bottom: 1px solid #444444;
  width: 100%;
  padding: 15px;
  font-size: 20px;
  font-size: 3vh
}

.modal-footer{

  border-top: 1px solid #444444;
  width: 100%;
  padding: 15px;
  font-size: 20px;
  font-size: 3vh
}
</style>
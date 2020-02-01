<template>
    <div>
        <div class="row">
            <div class="col-8">
                <Calendar mode="room"/>
            </div>
            <div class="col">

                
                <div class="container"  >
                    <div class="modal-header">
                        <h3> Arrange Examination </h3>
                    </div>
                        
                <div class="panel-body">
                    <br>
                    <form accept-charset="UTF-8" role="form">
                    
                         <div class="form-row">
                            <div class="form-group col-md-6">
                                <label >Room Name:</label>
                                <textarea class="form-control" v-text="this.room.name" disabled rows="1" style="resize:none"/>
                            </div>
                                <div class="form-group col-md-6">
                                <label>Examination Type:</label>
                                <textarea class="form-control" v-text="this.examReqs.examinationtype.name" disabled rows="1" style="resize:none"/>
                            </div>
                        </div>

                         <div class="form-row">
                            <div class="form-group col-md-6">
                                <label >Doctor:</label>
                                <textarea class="form-control" v-text="this.examReqs.doctor.firstName +' '+this.examReqs.doctor.lastName" disabled rows="1" style="resize:none"/>
                            </div>
                                <div class="form-group col-md-6">
                                <label>Date of Examination:</label>
                                 <textarea v-if="this.dateSearch != ''  "  class="form-control" v-text="this.date" disabled rows="1" style="resize:none"/>
                                 <textarea v-else class="form-control" v-text="this.date" disabled rows="1" style="resize:none"/>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-6">
                               <label >Time:</label>
                                 <div class="form-group">
                                    <textarea class="form-control" v-text="changeToString(this.examReqs.pickedtime)"  disabled rows="1" style="resize:none"/>
                                </div>
                            </div>
                           <div class="form-group col-md-6">
                                <label>Select another Doctor:</label>
                                     <select class="form-control" v-model="newDoctor">
                                        <option v-for="(doc, index) in this.doctors" :key="index"> {{doc.firstName}} {{doc.lastName}} </option>
                                    </select>
                            </div>
                        </div>


                         <div class="form-row">
                            <div class="form-group col-md-6">
                               <label >Free Appointments:</label>
                                 <div class="form-group">
                                    <select class="form-control" v-model="chosenAppForm">
                                        <option v-for="(ap, index) in this.freeAp" :key="index"> {{ap}} </option>
                                    </select>
                                </div>
                            </div>
                                
                        </div>

                        
                        
                        
                        <center> <button class="btn btn-lg btn-success btn-block"  @click="arrangeRoom()" > Arrange </button> </center>
                        <br>
                        <center> <button class="btn btn-lg btn-danger btn-block"  @click="backRooms()" > Back to Rooms </button> </center>
                   
                    </form>
                    <hr/>
                    
                </div>

      
	</div>

            </div>  

        </div>

    </div>
</template>

<script>
import Calendar from '../Calendar'
import axios from 'axios'
import {funToastr} from "../../toastr.js"

export default {
    data() {
        return {
            date:"",
            freeAp:[
                "06:00-07:00","07:00-08:00","08:00-09:00","09:00-10:00","10:00-11:00","11:00-12:00",
                "12:00-13:00","13:00-14:00","14:00-15:00","15:00-16:00","16:00-17:00","17:00-18:00",
                "18:00-19:00","19:00-20:00","20:00-21:00","21:00-22:00"
            ],
            takenAp:[],
            chosenAppForm:"",
            doctors:[],
            newDoctor:'',
            timeID:""
        }
        
    },
    props:["room","dateSearch","examReqs"],
    components:{
        Calendar
    },
    methods:{
        getFreeDates(){
            for(let td of this.room.takenDates){
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
                let dateTD = day+"."+month+"."+year
                
                console.log(dateTD)
                console.log(this.date)
                if(dateTD === this.date){

                    this.takenAp.push(this.changeToString(td.time));
                }
            }

            for(let t of this.takenAp){
               console.log(t)
               for( var i = 0; i < this.freeAp.length; i++){ 
                    if ( t === this.freeAp[i]) {
                        this.freeAp.splice(i, 1); 
                        i--;
                    }
                }
            }
              
        },
        arrangeRoom(){
            let newD = this.date.split(".")
            let newDate = newD[0]+"/"+newD[1]+"/"+newD[2] 
            let idDoctor = 'none';
            for( var i = 0; i < this.doctors.length; i++){ 
                if ( this.newDoctor === (this.doctors[i].firstName+' '+this.doctors[i].lastName)) {
                    idDoctor = this.doctors[i].id;
                    break;
                }
            }

            this.changeToInt();
            axios.post('http://localhost:8080/ca/arrangeExamination', {
                "idNextProcedure": this.examReqs.id,
                "idRoom": this.room.id,
                "date": newDate,
                "time": this.timeID,
                "idDoctorNew": idDoctor,
            }).then(response => { 
                 if(response.status == 200){
                        funToastr("s",response.data,"Room Arrange!");
                    }else {
                        funToastr("d","Error!","Room Arrange!");
                        return;
                    }
             }).finally(()=>{ 

                     setTimeout(() =>{                    
                 
                        this.$router.push("/CAProfile");       
                        this.$router.go("/CAProfile");
                             
                    },1600);

                });   
        },
        backRooms(){
            this.$router.push({name: 'Rooms', params: {examReq: this.examReqs }});

        },
        getClinicDoctors(){
            let url = 'http://localhost:8080/ca/getClinicDoctors/';
            url += this.examReqs.doctor.specialization;
            console.log(this.examReqs.doctor.specialization)
            axios.get(url).then(response => { 
                this.doctors = response.data; 
                 for( var i = 0; i < this.doctors.length; i++){ 
                    if ( this.examReqs.doctor.id === this.doctors[i].id) {
                        this.doctors.splice(i, 1); 
                        i--;
                    }
                }
               
             })
        },
        changeToInt(){

            if(this.chosenAppForm === "06:00-07:00"){
                this.timeID = 6;
            }else if(this.chosenAppForm === "07:00-08:00"){
                this.timeID = 7;
            }else if(this.chosenAppForm === "08:00-09:00"){
                this.timeID = 8;
            }else if(this.chosenAppForm === "09:00-10:00"){
                this.timeID = 9;
            }else if(this.chosenAppForm === "10:00-11:00"){
                this.timeID = 10;
            }else if(this.chosenAppForm === "11:00-12:00"){
                this.timeID = 11;
            }else if(this.chosenAppForm === "12:00-13:00"){
                this.timeID = 12;
            }else if(this.chosenAppForm === "13:00-14:00"){
                this.timeID = 13;
            }else if(this.chosenAppForm === "14:00-15:00"){
                this.timeID = 14;
            }else if(this.chosenAppForm === "15:00-16:00"){
                this.timeID = 15;
            }else if(this.chosenAppForm === "16:00-17:00"){
                this.timeID = 16;
            }else if(this.chosenAppForm === "17:00-18:00"){
                this.timeID = 17;
            }else if(this.chosenAppForm === "18:00-19:00"){
                this.timeID = 18;
            }else if(this.chosenAppForm === "19:00-20:00"){
                this.timeID = 19;
            }else if(this.chosenAppForm === "20:00-21:00"){
                this.timeID = 20;
            }else if(this.chosenAppForm === "21:00-22:00"){
                this.timeID = 21;
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


        
    },
    
    created(){
        // ako je dateSearch null => uzima se datum iz examReq
        if(this.dateSearch === "none"){
            let dat = new Date(this.examReqs.date);      
            let s = dat.toLocaleString().split(",");
            let s1 = s[0].split("/");
            let day = s1[1]
            let month = s1[0]
            let year = s1[2]
            if(day.length === 1)
                day = '0'+day.toString()
            if(month.length === 1)
                month = '0'+month.toString()
            this.date = day+"."+month+"."+year
        }else{  
            let s = this.dateSearch.toLocaleString().split("/");
            this.date = s[0]+"."+s[1]+"."+s[2]
        }
        

        this.getFreeDates();
        this.getClinicDoctors();
    }
}
</script>

<style scoped>

</style>
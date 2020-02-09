<template>
    <div class="container shadow-lg">
        <div v-if="patient !== null" class="examin-header">
            <h3> Name: {{patient.firstName}} {{patient.lastName}} </h3>
            <h4> Social security number: {{patient.uniqueNum}} </h4>
        </div>
        <div v-else>
            <h2> NO INFO, CONTACT THE ADMINISTRATOR! </h2>
        </div>
        <div class="form-group">
            <label>Report:</label>
            <textarea class="form-control" rows="5" placeholder="Enter examination report..." style="max-height: 124px; resize: none;" v-model="examDesc"></textarea>
        </div>
            <!-- TODO: NEW EXAMINATION-OPERATION FORM -->
            
            <div>
                <br>
                <br>
                <div class="modal-header" style=" border-bottom: 2px dashed #5f5f5f; width: 100%;padding: 5px;font-size: 20px;font-size: 3vh" >
                    <label style="width:auto;font-size:25px">Arrange next procedure: </label>
                </div>
                    
                    <div class="panel-body" >
                        <br>
                        <form accept-charset="UTF-8" role="form" >
                            <div class="control-group" style="padding-left:20px; padding-right:700px">
                                <label class="control-label">Type of procedure:</label>
                                <div class="form-group">
                                    <select class="form-control" v-model="procedure">
                                        <option> Examination </option>
                                        <option> Operation </option>
                                    </select>
                                </div>
                            </div>

                            <div v-if="procedure==='Examination'" class="control-group" style="padding-left:20px; padding-right:700px">
                                <label class="control-label">Type of Examination:</label>
                                <div class="form-group">
                                    <select class="form-control" v-model="type">
                                        <option v-for="(ext, index) in examTypes" :key="index" :value="ext"> {{ext.name}}</option>
                                    </select>
                                </div>
                            </div>
                            
                            <div v-if="procedure==='Operation'" class="control-group" style="padding-left:20px; padding-right:700px">
                                <label class="control-label">Type of Operation:</label>
                                <div class="form-group">
                                    <select class="form-control" v-model="type">
                                        <option v-for="(opt, index) in operTypes" :key="index" :value="opt"> {{opt.name}}</option>
                                    </select>
                                </div>
                            </div>

                            <div class="control-group" style="padding-left:20px; padding-right:700px">
                                <label class="control-label" >Date:</label>
                                <div class="form-group">
                                    <input class="form-control" type ="date" v-model="date" />
                                </div>
                            </div>

                             <div class="control-group" style="padding-left:20px; padding-right:700px">
                                <label class="control-label" >Time:</label>
                                <div class="form-group">
                                     <select class="form-control" v-model="timeF">
                                        <option v-for="(ap, index) in this.freeApps" :key="index"> {{ap}} </option>
                                    </select>
                                </div>
                            </div>
                            <br>
                            <div class="modal-footer" style=" border-top: 2px dashed #5f5f5f;" >
                            </div>      
                        </form>
                                
                    </div>
            </div>

        <br>
        <br>
        <br>

        <div class="modal-footer">
            <button class="btn btn-outline-success" v-on:click="confirm()">Confirm and end examination</button>
            <button class="btn btn-outline-primary" v-on:click="seeMedicalFile()">See Medical File</button>
        </div> 
    </div>
</template>

<script>
import axios from 'axios'

export default {
    data() {
        return {
            doctorArr: [],
            patient: null,
            examDesc: "",

            procedure :"",
            date: "",
            type:"",
            examTypes:"",
            operTypes:"",
            timeF:'',
            freeApps:[],
          
        }
    },
    props: {
        id: {
            type: String
        }
    },
    methods: {

        getExamTypes(){
            axios.get('/ca/getNextExmType').then(response => { this.examTypes = response.data; })
        },
        getOperTypes(){
            axios.get('/ca/getOperTypes').then(response => { this.operTypes = response.data; })
        },
        getPatiendData() {
            axios
                .get('/patient/' + this.id)
                .then(response => { 
                    this.patient = response.data;
                })
        },
        getDoctorData() {
            axios
                .get('/doctor/getInfo')
                .then(response => { 
                    this.doctorArr = response.data;
                    this.setTime(this.doctorArr.shift);
                })
        },
        confirm() {
            let reqData = { 
                patientId: this.patient.id,
                desc: this.examDesc,
                diag: {id: 0, name: "OPERATION", desc:"", date: this.date},
                prescs: [],
                procedure: this.procedure,
                date: this.date,
                idType: this.type.id,
                pickedtime: this.changeToInt(),
            }

            axios
                .post('/patient/examination', reqData)
                .then(response => { 
                    this.$router.push("/")
                    this.$router.go("/")
                })
        },

        changeToInt(){

            if(this.timeF === "06:00-07:00"){
               return 6;
            }else if(this.timeF  === "07:00-08:00"){
                return 7;
            }else if(this.timeF === "08:00-09:00"){
                return 8;
            }else if(this.timeF === "09:00-10:00"){
                return 9;
            }else if(this.timeF === "10:00-11:00"){
                return 10;
            }else if(this.timeF === "11:00-12:00"){
                return 11;
            }else if(this.timeF === "12:00-13:00"){
                return 12;
            }else if(this.timeF === "13:00-14:00"){
                return 13;
            }else if(this.timeF === "14:00-15:00"){
                return 14;
            }else if(this.timeF === "15:00-16:00"){
                return 15;
            }else if(this.timeF === "16:00-17:00"){
                return 16;
            }else if(this.timeF === "17:00-18:00"){
                return 17;
            }else if(this.timeF === "18:00-19:00"){
                return 18;
            }else if(this.timeF === "19:00-20:00"){
                return 19;
            }else if(this.timeF === "20:00-21:00"){
                return 20;
            }else if(this.timeF === "21:00-22:00"){
                return 21;
            }

        },
        setTime(shift){
            let freeAp=[
                "06:00-07:00","07:00-08:00","08:00-09:00","09:00-10:00","10:00-11:00","11:00-12:00",
                "12:00-13:00","13:00-14:00","14:00-15:00","15:00-16:00","16:00-17:00","17:00-18:00",
                "18:00-19:00","19:00-20:00","20:00-21:00","21:00-22:00"
            ];

            if(shift == 1){

                for( var j = 0; j <= 7; j++){ 
                    this.freeApps.push(freeAp[j])                       
                }

            }else if(shift == 2){

                    for( var i = 8; i <= 15; i++){ 
                        this.freeApps.push(freeAp[i])                
                    }
            }

        },
        seeMedicalFile(){
            this.$router.push({name:'MedicalFile', params: {medstaff:"examination",patient:this.patient}});
        }
       
    },
    

    created() {
        this.getDoctorData();
        this.getPatiendData();
        this.getExamTypes();
        this.getOperTypes();

    }
}
</script>

<style scoped>
    .container {
        border-style: solid;
        border-width: 3px;
        border-color: gray;
        border-top-style: none;
        border-bottom-style: none;
        border-radius: 20px;
        margin-top: 20px;
        padding-top: 20px;
        padding-bottom: 20px;
        padding-left: 30px;
        padding-right: 30px;
    }
    .examin-header {
        border-bottom-width: 2px;
        border-bottom-color: gray;
        border-bottom-style: solid;
        padding-bottom: 10px;
        margin-bottom: 10px;
    }
    .modal-footer {
        border-top-width: 1px;
        border-top-color: gray;
        border-top-style: solid;
        margin-top: 10px;
        padding-right: 0px;
    }
    .separator {
        border-bottom-width: 2px;
        border-bottom-color: gray;
        border-bottom-style: dashed;
        margin-top: 20px;
        margin-bottom: 20px;
    }
</style>
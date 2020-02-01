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
        <div class="form-group">
            <label>Diagnosis:</label>
            <select class="form-control" id="sel" v-model="selDiag">
                <option value=0>--- Select a diagnosis ---</option>
                <option v-for="(row, index) in diagSel" :key="index" v-bind:value=row.id>{{row.name}}</option>
            </select>
            <label style="margin-top: 10px" v-if="selDiag != 0">Description:</label>
            <p v-if="selDiag != 0"><em>{{diagDesc}}</em></p>
        </div>
        <div class="separator"> </div>
        <label>Prescription:</label>
        <div class="input-group">
            <select class="form-control" id="sel" v-model="selPres">
                <option value=0>--- Select a prescription ---</option>
                <option v-for="(row, index) in presSel" :key="index" v-bind:value=row.id>{{row.name}}</option>
            </select>
            <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="button" v-on:click="addPres()">Add prescription</button>
            </div>
        </div>
        <table class="table table-striped" style="margin-top: 10px" v-if="prescs.length > 0">
            <thead class="thead-dark">
                <tr>
                    <th>Med. ID</th>
                    <th>Name</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(row, index) in prescs" :key="index">
                    <td>{{row.id}}</td>
                    <td>{{row.name}}</td>
                </tr>
            </tbody>
        </table>
        <div class="separator"> </div>
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
        </div> 
    </div>
</template>

<script>
import axios from 'axios'

export default {
    data() {
        return {
            selDiag: 0,
            selPres: 0,
            diagDesc: "",
            patient: null,
            diagSel: [],
            presSel: [],
            prescs: [],
            diag: null,
            examDesc: "",

            procedure :"",
            date: "",
            type:"",
            examTypes:"",
            timeF:'',
            freeApps:[
                "06:00-07:00","07:00-08:00","08:00-09:00","09:00-10:00","10:00-11:00","11:00-12:00",
                "12:00-13:00","13:00-14:00","14:00-15:00","15:00-16:00","16:00-17:00","17:00-18:00",
                "18:00-19:00","19:00-20:00","20:00-21:00","21:00-22:00"
            ],
        }
    },
    props: {
        id: {
            type: String
        }
    },
    methods: {

        getExamTypes(){
            axios.get('http://localhost:8080/ca/getNextExmType').then(response => { this.examTypes = response.data; })
        },

        getPatiendData() {
            axios
                .get('http://localhost:8080/patient/' + this.id)
                .then(response => { 
                    this.patient = response.data;
                })
        },
        
        getCodebookData() {
            axios
                .get('http://localhost:8080/codebook/diagnosis')
                .then(response => { 
                    this.diagSel = response.data;
                })

            axios
                .get('http://localhost:8080/codebook/prescription')
                .then(response => { 
                    this.presSel = response.data;
                })
        },
        addPres() {
            let BreakException = {};

            try{
                this.presSel.forEach(pres => {
                    if (pres.id == this.selPres) {
                        this.prescs.push(pres)
                        throw BreakException
                    }
                })
            } catch(e) {
                return
            }
        },
        confirm() {
            
            if(this.procedure !== 'Examination')
                this.type = ''
            else{
                this.type = this.type.id
                console.log(this.type)
            }

            let reqData = { 
                patientId: this.patient.id,
                desc: this.examDesc,
                diag: this.diag,
                prescs: this.prescs,
                procedure: this.procedure,
                date: this.date,
                idType: this.type,
                pickedtime: this.changeToInt(),
            }
            axios
                .post('http://localhost:8080/patient/examination', reqData)
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
       
    },
    

    created() {
        this.getPatiendData()
        this.getCodebookData()
        this.getExamTypes()
    },
    watch: {
        selDiag: {
            handler(val) {
                let BreakException = {};

                try{
                    this.diagSel.forEach(diag => {
                        if (diag.id == val) {
                            this.diagDesc = diag.desc
                            this.diag = diag
                            throw BreakException
                        }
                    })
                } catch(e) {
                    return
                }
            },
            deep: true
        }
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
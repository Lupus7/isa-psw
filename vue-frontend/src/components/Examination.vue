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
            <label>Arrange another examination or operation:</label>
            <!-- TODO: NEW EXAMINATION-OPERATION FORM -->
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
            examDesc: ""
        }
    },
    props: {
        id: {
            type: String
        }
    },
    methods: {
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
            let reqData = { 
                patientId: this.patient.id,
                desc: this.examDesc,
                diag: this.diag,
                prescs: this.prescs
            }
            axios
                .post('http://localhost:8080/patient/examination', reqData)
                .then(response => { 
                    this.$router.push("/")
                    this.$router.go("/")
                })
        } 
    },
    created() {
        this.getPatiendData()
        this.getCodebookData()
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
        border-top-width: 2px;
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
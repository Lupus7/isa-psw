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
            <textarea class="form-control" rows="5" placeholder="Enter examination report..." style="max-height: 124px; resize: none;"></textarea>
        </div>
        <div class="form-group">
            <label>Diagnosis:</label>
            <select class="form-control" id="sel">
                <option value=0>--- Select a diagnosis ---</option>
                <!--option v-for="(row, index) in rows" :key="index" v-bind:value=index+1>{{row.name}}</option-->
            </select>
            <label style="margin-top: 10px" v-if="selDiag !== 0">Description:</label>
            <p v-if="selDiag !== 0"><em>{{diagDesc}}</em></p>
        </div>
        <div class="separator"> </div>
        <label>Prescription:</label>
        <div class="input-group">
            <select class="form-control" id="sel">
                <option value=0>--- Select a prescription ---</option>
                <!--option v-for="(row, index) in rows" :key="index" v-bind:value=index+1>{{row.name}}</option-->
            </select>
            <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="button">Add prescription</button>
            </div>
        </div>
        <table class="table table-striped" style="margin-top: 10px"> <!-- v-if="..." -->
            <thead class="thead-dark">
                <tr>
                    <th>Med. ID</th>
                    <th>Name</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(row, index) in rows" :key="index">
                    <td>{{row.id}}</td>
                    <td>{{row.name}}</td>
                </tr>
            </tbody>
        </table>
        <div class="separator"> </div>
            <label>Arrange another examination or operation:</label>
            <!-- TODO: NEW EXAMINATION-OPERATION FORM -->
        <div class="modal-footer">
            <button class="btn btn-outline-success">Confirm and end examination</button>
        </div> 
    </div>
</template>

<script>
import axios from 'axios'

export default {
    data() {
        return {
            selDiag: 0,
            diagDesc: "",
            patient: null
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
        }
    },
    created() {
        this.getPatiendData();
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
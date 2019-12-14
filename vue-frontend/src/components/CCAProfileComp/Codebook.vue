<template>
    <div class="row">
        <div class=" col-8">
            <table class="table table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Diagnosis</th>
                        <th>Description</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(row, index) in diagnosisEdit" :key="index">
                        <td>{{row.id}}</td>
                        <td>
                            <input type="text" class="form-control" placeholder="Enter a name" v-model="row.name">
                        </td>
                        <td>
                            <textarea class="form-control" rows="5" placeholder="Enter a description..." style="height: 38px; min-height: 38px; max-height: 124px;" v-model="row.description"></textarea>
                            </td>
                        <td>
                            <button class="btn btn-outline-danger" v-if="row.name == diagnosis[index].name && row.description == diagnosis[index].description" v-on:click="deleteD(row.id)">Delete</button>
                            <div class="btn-group btn-group-toggle" data-toggle="buttons" style="padding: 0px; margin: 0px" v-else>
                                <label class="btn btn-outline-success">
                                    <input type="radio" name="options" autocomplete="off" v-on:click="saveD(index)"> Save
                                </label>
                                <label class="btn btn-outline-danger">
                                    <input type="radio" name="options" autocomplete="off" v-on:click="resetD(index)"> X
                                </label>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <input type="text" class="form-control" placeholder="Enter a name" v-model="newDiagnosis.name">
                        </td>
                        <td>
                            <textarea class="form-control" rows="5" placeholder="Enter a description..." style="height: 38px; min-height: 38px; max-height: 124px;" v-model="newDiagnosis.description"></textarea>
                            </td>
                        <td>
                            <button class="btn btn-outline-primary" v-on:click="saveD(null)">Add</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="col-4">
            <table class="table table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Prescription</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(row, index) in prescriptionEdit" :key="index">
                        <td>{{row.id}}</td>
                        <td>
                            <input type="text" class="form-control" placeholder="Enter a name" v-model="row.name">
                        </td>
                        <td>
                            <button class="btn btn-outline-danger" v-if="row.name == prescription[index].name" v-on:click="deleteP(row.id)">Delete</button>
                            <div class="btn-group btn-group-toggle" data-toggle="buttons" style="padding: 0px; margin: 0px" v-else>
                                <label class="btn btn-outline-success">
                                    <input type="radio" name="options" autocomplete="off" v-on:click="saveP(index)"> Save
                                </label>
                                <label class="btn btn-outline-danger">
                                    <input type="radio" name="options" autocomplete="off" v-on:click="resetP(index)"> X
                                </label>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <input type="text" class="form-control" placeholder="Enter a name" v-model="newPrescription.name">
                        </td>
                        <td>
                            <button class="btn btn-outline-primary" v-on:click="saveP(null)">Add</button>
                        </td>
                    </tr>
                </tbody>
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
            diagnosis: [],
            diagnosisEdit: [],
            prescription: [],
            prescriptionEdit: [],
            newDiagnosis: {
                id: null,
                name: "",
                description: ""
            },
            newPrescription: { 
                id: null,
                name: ""
            }
        }
    },
    methods: {
        getCodebookData() {
            axios
                .get('/codebook/diagnosis')
                .then(response => { 
                    this.diagnosis = response.data
                    this.diagnosisEdit = (JSON.parse(JSON.stringify(response.data)))
                })

            axios
                .get('/codebook/prescription')
                .then(response => { 
                    this.prescription = response.data
                    this.prescriptionEdit = (JSON.parse(JSON.stringify(response.data)))
                })
        },
        resetD(index) {
            this.diagnosisEdit[index].name = this.diagnosis[index].name
            this.diagnosisEdit[index].description = this.diagnosis[index].description
        },
        resetP(index) {
            this.prescriptionEdit[index].name = this.prescription[index].name
        },
        saveD(index) {
            const diag = (index == null) ? this.newDiagnosis : this.diagnosisEdit[index]
            var vm = this
            axios
                .post('/codebook/diagnosis', diag)
                .finally(function() {
                    vm.getCodebookData();
                    if (index == null) {
                        vm.newDiagnosis.name = ""
                        vm.newDiagnosis.description = ""
                    }
                })
        },
        saveP(index) {
            const pres = (index == null) ? this.newPrescription : this.prescriptionEdit[index]
            var vm = this
            axios
                .post('/codebook/prescription', pres)
                .finally(function() {
                    vm.getCodebookData();
                    if (index == null)
                        vm.newPrescription.name = ""
                })
        },
        deleteD(index) {
            var vm = this
            axios
                .delete('/codebook/diagnosis/' + index)
                .catch(error => {
                    funToastr("d", "Diagnosis can't be deleted. It is already in use!");
                })
                .finally(response => {
                    vm.getCodebookData();
                })
        },
        deleteP(index) {
            var vm = this
            axios
                .delete('/codebook/prescription/' + index)
                .catch(error => {
                    funToastr("d", "Prescription can't be deleted. It is already in use!");
                })
                .finally(function() {
                    vm.getCodebookData();
                })
        }
    },
    created() {
        this.getCodebookData()
    }
}
</script>

<style scoped>
    
</style>
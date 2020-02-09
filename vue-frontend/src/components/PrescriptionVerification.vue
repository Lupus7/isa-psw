<template>
    <div class="container">
        <h4> Prescriptions: </h4>
        <div class="separator"></div>
        <div v-if="prescriptions.length > 0">
            <div v-for="(pres, index) in prescriptions" :key="index">
                <div class="row">
                    <div class="col-10">
                        <h5>Doctor <b><i>{{pres.doctorName}}</i></b> prescribed <b><i>{{pres.prescription.name}}</i></b> to <b><i><a :href="'#/patientProfile/' + pres.patientId">{{pres.patientName}}</a></i></b></h5>
                    </div>
                    <div class="col-2">
                        <button class="btn btn-outline-success float-right" @click="verify(pres.id)"> Verify </button>
                    </div>
                </div>
                <div class="separator"></div>
            </div>
        </div>
        <div v-else>
            <h3> NO NEW PRESCRIPTIONS </h3>
        </div>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    data() {
        return {
            prescriptions: []
        }
    },
    methods: {
        getPrescriptions() {
            axios
                .get('/nurse/prescription')
                .then(response => {
                    this.prescriptions = response.data
                })
        },
        verify(id) {
            axios
                .post(`/nurse/prescription/${id}`, {}, { crossdomain: true })
                .then(this.prescriptions=this.prescriptions.filter(item=>item.id!=id))
        }
    },
    created() {
        this.getPrescriptions()
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
    .separator {
        border-bottom-width: 2px;
        border-bottom-color: gray;
        border-bottom-style: dashed;
        margin-top: 20px;
        margin-bottom: 20px;
    }
    a,
    a:hover {
        color: inherit;
    }
</style>
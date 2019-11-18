<template>
    <div>
        <table class="table table-striped">
            <thead class="thead-dark">
                <tr>
                    <th>Name</th>
                    <th>E-Mail</th>
                    <th>Phone Number</th>
                    <th>Unique Number</th>
                    <th>Adress</th>
                    <th>City</th>
                    <th>Country</th>
                    <th>Accept?</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(row, index) in rows" :key="index">
                    <td>{{row.firstName}} {{row.lastName}}</td>
                    <td>{{row.email}}</td>
                    <td>{{row.telephone}}</td>
                    <td>{{row.uniqueNum}}</td>
                    <td>{{row.address}}</td>
                    <td>{{row.city}}</td>
                    <td>{{row.state}}</td>
                    <td style="max-width: 115px">
                        <button type="button" class="btn btn-success" v-on:click="accept(row.email, index)">Accept</button>
                        <button type="button" class="btn btn-danger" v-on:click="reject(row.email, index)">Reject</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    data() {
        return {    
            rows: []
        }
    },
    methods: { 
        getReqList() {
            axios
                .get('http://localhost:8080/cca/request-list')
                .then(response => { this.rows = response.data })
        },
        accept(email, index) {
            let url = 'http://localhost:8080/cca/request-list/accept/' + email
            axios.post(url)
            this.rows.splice(index, 1)
        },
        reject(email, index) {
            let url = 'http://localhost:8080/cca/request-list/reject/' + email
            axios.post(url)
            this.rows.splice(index, 1)
        }
    },
    created() {
        this.getReqList()
    }
}
</script>

<style scoped>
    div {
        max-width: 1200px;
        margin: 10px
    }
    button {
        margin-left: 5px
    }
</style>
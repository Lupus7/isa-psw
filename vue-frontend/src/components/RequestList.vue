<template>
    <table>
        <thead>
            <tr>
                <th>Name</th>
                <th>E-Mail</th>
                <th>Phone Number</th>
                <th>Unique Number</th>
                <th>Adress</th>
                <th>City</th>
                <th>Country</th>
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
            </tr>
        </tbody>
    </table>
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
                .get('http://localhost:8080/request-list')
                .then(response => { this.rows = response.data })
        }
    },
    created() {
        this.getReqList()
    }
}
</script>

<style scoped>
    table {
    font-family: 'Open Sans', sans-serif;
    width: 750px;
    border-collapse: collapse;
    border: 3px solid #44475C;
    margin: 10px 10px 0 10px;
    }

    table th {
    text-transform: uppercase;
    text-align: left;
    background: #44475C;
    color: #FFF;
    padding: 8px;
    min-width: 30px;
    }

    table td {
    text-align: left;
    padding: 8px;
    border-right: 2px solid #7D82A8;
    }

    table td:last-child {
    border-right: none;
    }

    table tbody tr:nth-child(2n) td {
    background: #D4D8F9;
    }
</style>
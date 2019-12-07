<template>
    <div>
        <div class="form-group" :style="expVisible">
            <label for="exampleFormControlTextarea3">Explanation for rejecting <b>{{selectedUser}}</b>:</label>
            <textarea class="form-control" id="exampleFormControlTextarea3" rows="7" v-model="explanation"></textarea>
            <button type="button" class="btn btn-danger" v-on:click="reject(selectedUser, selectedIndex)" style="margin-top: 5px; margin-left:45%; margin-right:auto">Reject</button>
        </div>
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
                    <td style="max-width: 150px">
                        <button type="button" class="btn btn-success" v-on:click="accept(row.email, index)">Accept</button>
                        <button type="button" class="btn btn-danger" v-on:click="fillReject(row.email, index)">Reject</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import axios from 'axios'
import {returnToken} from '../../token.js'

export default {
    data() {
        return {    
            rows: [],
            show: true,
            selectedUser: "",
            explanation: "",
            expVisible: "visibility:hidden; height: 0px",
            selectedIndex: 0
        }
    },
    methods: { 
        getReqList() {
            axios
                .get('/cca/requests')
                .then(response => { this.rows = response.data })
        },
        accept(email, index) {
            axios.put('/cca/request/' + this.rows[index].id)
            this.rows.splice(index, 1)
            this.fillReject("", index)
        },
        reject(email, index) {
            axios.delete('/cca/request/' + this.rows[index].id, this.explanation)
            this.rows.splice(index, 1)
            this.selectedUser = ""
        },
        fillReject(email, index) {
            if (email.localeCompare(this.selectedUser) != 0) {
                this.selectedUser = email
                this.explanation = ""
                this.selectedIndex = index
            }
        }
    },
    created() {
        this.getReqList()
    },
    watch: {
        selectedUser: {
            handler(val) {
                if (val == "")
                    this.expVisible = "visibility:hidden; height: 0px"
                else
                    this.expVisible = "visibility:visible; height: 300px"
            },
            deep: true
        }
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
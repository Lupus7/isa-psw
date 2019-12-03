<template>
    <div class="wrapper" style="padding:20px" >
        
    <div :style="expVisible">
        <div class="modal-header" >
              <h1 style="width:auto;font-size:45px"><span class="badge badge-light">Explanation for rejecting <b>{{selectedUser}}</b>:</span> </h1>
        </div>
            
            <div class="panel-body" >
                <br>
                <form accept-charset="UTF-8" role="form" >
                    <div class="control-group">
                        <label class="control-label" style="font-size:20px; ">Reason:</label>
                         <div class="form-group">
                            <textarea class="form-control" style="resize: none; " rows="7" cols="80" v-model="explanation"></textarea>
                        </div>
                    </div>
                    <br>
                    <div class="modal-footer">
                        <button class="btn btn-danger" @click="reject(selectedUser, selectedIndex)" style="font-size:20px; width:auto">Reject</button>
                     </div>      
                </form>
                          
            </div>
    </div>

        <table class="table table-striped table-hoover" >
            <thead >
                
                <tr class="thead-dark">
                    <th colspan="8"> <center> <h2> Holiday/Time off requests </h2> </center> </th>                  
                </tr>

                <tr class="table-secondary">
                    <th align="justify"  style="font-size:18px">Name</th>
                    <th align="justify"  style="font-size:18px">E-Mail</th>
                    <th align="justify"  style="font-size:18px">Phone Number</th>
                    <th align="justify"  style="font-size:18px">Unique Number</th>
                    <th align="justify"  style="font-size:18px">Adress</th>
                    <th align="justify"  style="font-size:18px">City</th>
                    <th align="justify"  style="font-size:18px">Country</th>
                    <th align="justify"  style="font-size:18px">Accept?</th>
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
                        <button type="button" class="btn btn-success" @click="accept(row.email, index)">Accept</button>
                        <button type="button" class="btn btn-danger" @click="fillReject(row.email, index)">Reject</button>
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
            axios.get('http://localhost:8080/ca/request-list').then(response => { this.rows = response.data })
        },
        accept(email, index) {
            let url = 'http://localhost:8080/ca/request-list/accept'
            axios.post(url, {"mail": email})
            this.rows.splice(index, 1)
            this.fillReject("", index)
        },
        reject(email, index) {
            let url = 'http://localhost:8080/ca/request-list/reject'
            axios.post(url, { "mail": email, "expl": this.explanation })
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

.wrapper{
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif
}

.modal-header{

  border-bottom: 2px solid #5f5f5f;
  width: 100%;
  padding: 15px;
  font-size: 20px;
  font-size: 3vh

}

.modal-footer{

  border-top: 2px solid #5f5f5f;
  width: 100%;
  font-size: 20px;
  font-size: 3vh
}

</style>
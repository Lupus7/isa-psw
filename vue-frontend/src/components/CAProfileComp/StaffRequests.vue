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
                    <th align="justify"  style="font-size:18px">Employee</th>
                    <th align="justify"  style="font-size:18px">Email</th>
                    <th align="justify"  style="font-size:18px">Request Type</th>
                    <th align="justify"  style="font-size:18px">Begin Date</th>
                    <th align="justify"  style="font-size:18px">End Date</th>
                    <th align="justify"  style="font-size:18px">Reason</th>
                    <th> </th>
                    <th> </th>
                </tr>
            </thead>
            <tbody v-if="rows != null">
                <tr v-for="(row, index) in rows" :key="index">
                    <td>{{row.staff.firstName}} {{row.staff.lastName}}</td>
                    <td>{{row.staff.email}}</td>
                    <td>{{row.type}}</td>
                    <td v-on="dateB(row.beginDate)">{{dateBeg}}</td>
                    <td v-on="dateE(row.endDate)">{{dateEnd}}</td>
                    <td> <textarea class="form-control" v-text=" row.reason"> </textarea> </td>
                    <td style="max-width: 60px">
                        <button type="button" class="btn btn-success" @click="accept(row.staff.email,row.id,row.beginDate,row.endDate,row.type, index)">Accept</button>
                    </td>
                    <td style="max-width: 60px">
                        <button type="button" class="btn btn-danger" @click="fillReject(row.staff.email, row.id, row.type, index)">Reject</button>
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
            selectedId: "",
            selectedType: "",
            explanation: "",
            expVisible: "visibility:hidden; height: 0px",
            selectedIndex: 0,
            dateBeg: "",
            dateEnd:""
        }
    },
    methods: { 
        getReqList() {
            axios.get('http://localhost:8080/ca/request-list').then(response => { this.rows = response.data; })
        },
        accept(email,id,beginD,endD,type,index) {
            let url = 'http://localhost:8080/ca/request-list/accept'
            axios.post(url, {"mail": email, "id":id , "dateB":beginD,"dateE":endD,"type":type })
            this.rows.splice(index, 1)
            this.fillReject("", index)
        },
        reject(email, id, type, index) {
            let url = 'http://localhost:8080/ca/request-list/reject'
            axios.post(url, { "mail": email, "expl": this.explanation, "id": this.selectedId,"type":this.selectedType })
            this.rows.splice(index, 1)
            this.selectedUser = ""
        },
        fillReject(email,id,type, index) {
            if (email.localeCompare(this.selectedUser) != 0) {
                this.selectedUser = email
                this.explanation = ""
                this.selectedIndex = index
                this.selectedId = id;
                this.selectedType = type;
            }
        },
        dateB(d){
            let date = new Date(d);       
            let s = date.toLocaleDateString().split("/");
            this.dateBeg = s[1]+"."+s[0]+"."+s[2];
          
        },
        dateE(d){
            let date = new Date(d);       
            let s = date.toLocaleDateString().split("/");
            this.dateEnd = s[1]+"."+s[0]+"."+s[2];
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
                    this.expVisible = "visibility:visible;"
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
<template>
<div style="display: flex">
    <div class="panel-body">
        <form id="forma">
                <fieldset>
            <div class="form-group">
                <input type="text" class="form-control" id="firstname" aria-describedby="emailHelp" placeholder="Enter First Name..." required>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="lastname" aria-describedby="emailHelp" placeholder="Enter Last Name..." required>
            </div>
            <div class="form-group">
                <input type="password" id="password" class="form-control" placeholder="Password..." required>
            </div>
            <div class="form-group">
                <input type="password" id="password1" class="form-control" placeholder="Retype Password..." required>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Enter Email" required>
            </div>
            <div class="form-group">
                <input type="text" id="adress" class="form-control" required aria-describedby="emailHelp" placeholder="Enter Adress" >
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="city" required aria-describedby="emailHelp" placeholder="Enter City" >
            </div>	
            <div class="form-group">
                <input type="text" class="form-control" id="state" required placeholder="Enter State">
            </div>
            
            <div class="form-group">
                <input type="text" class="form-control" id="phone" required aria-describedby="emailHelp" placeholder="Enter Phone">
            </div>
            
            <div class="form-group">
                <input type="number" class="form-control" id="uniqnum" required aria-describedby="emailHelp" placeholder="Enter Unique Number">
            </div>
            
            <div class="form-group">
                <select class="form-control" id="sel">
                    <option value=0>--- Select a clinic ---</option>
                    <option v-for="(row, index) in rows" :key="index" v-bind:value=index+1>{{row.name}}</option>
                </select>
            </div>

            <p id="error" hidden="true"></p>
            <p id="success" hidden="true"></p>
            <button type="submit" v-on:click="addAdmin" class="btn btn-lg btn-primary btn-block">Add admin</button>
            </fieldset>
        </form>
    </div>
    <div class="add-clinic">
        <div class="reg-clinic row">
            <div class="col" style="margin-left: 5px">
                <div class="form-group">
                    <label>Name:</label>
                    <input type="text" class="form-control" placeholder="Enter a name">
                </div>
                <div class="form-group">
                    <label>Address:</label>
                    <input type="text" class="form-control" placeholder="Enter an address">
                </div>
            </div>
            <div class="form-group col">
                <label>Description:</label>
                <textarea class="form-control" rows="5" placeholder="Enter a description..." style="max-height: 124px; resize: none"></textarea>
            </div>
        </div>
        <div class="clinic-list" style="max-height: 500px; overflow: overlay; margin-right: -15px">
            <div v-for="(row, index) in rows" :key="index" style="margin-right: 15px">
            <div class="row clinic-item" v-bind:id="'clinic' + index">
            <details class="detailsX" style="margin: 5px; width: 100%">
                <summary>
                    {{row.name}} @ {{row.address}}
                </summary>
                <div style="margin: 5px;" class="content">
                    <label>Description:</label>
                    <div class="div-desc" style="background: white"> {{row.description}} </div>
                    <div v-if="row.admin.length > 0">
                        <br><label>Admins:</label><br>
                    </div>
                    <button class="btn btn-outline-primary" v-for="(admin, i) in row.admin" :key="i" type="button" style="margin-right: 5px">
                        {{admin}}
                    </button>
                </div>
            </details>
            </div>
            </div>
        </div>
    </div>
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
    methods:{
        addAdmin(event) {
            event.preventDefault();
            let firstname=document.getElementById("firstname").value;
            let lastname=document.getElementById("lastname").value;
            let password=document.getElementById("password").value;
            let password2=document.getElementById("password1").value;
            let email=document.getElementById("email").value;
            
            let adress=document.getElementById("adress").value;
            let city=document.getElementById("city").value;
            let state=document.getElementById("state").value;
            let phone=parseInt(document.getElementById("phone").value);
            let uniquenum=parseInt(document.getElementById("uniqnum").value);
            let clinic=parseInt(document.getElementById("sel").value);
            if (password !== password2){
                alert("Password did not match")
                return
            }
            if(firstname==="" || lastname==="" || password==="" || email==="" || adress==="" || city==="" || state==="" || phone==="" || uniquenum==="" || clinic==0){
				alert("Fill all the fields");
				return;
			}

            console.log(firstname + lastname)
           
            axios
                .post('http://localhost:8080/cca/reg_admin',{
                    "firstName" : firstname,
                    "lastName" : lastname,
                    "password" : password,
                    "email":email,
                    "address":adress,
                    "city":city,
                    "state" : state,
                    "telephone":phone,
                    "uniqueNum" : uniquenum,
                    "clinic" : clinic
                })
                .then(response=>{
                    if (response.status == 204) {
                        alert("This email  already exists");
                        return;
                    }
                })
                .catch(error => {
                    alert("Neuspesno" + error)});
            
            setTimeout(()=>{
                document.getElementById("forma").reset()
                this.loadClinics()
            }, 1500)
        },
        loadClinics() {
            axios
                .get('http://localhost:8080/cca/get_clinics')
                .then(response => { this.rows = response.data; console.log(response.data) })
        }
    },
    created() {
        this.loadClinics()
    }
}
</script>

<style scoped>

.panel-body{
    max-width: 25%;
    min-width: 25%;
    padding: 20px;
    align-self: center;
    margin-left: 30px;
    margin-right: 20px;
    height: 100%;
    border-style: solid;
    border-width: 1px;
    border-radius: 10px;
    background: #f1f1f1;
 }

.form {
    display: block !important;
    align-items: stretch;
    padding-top: 20px;
      
 }
.input {
    display: block;
    padding: 15px 10px;
    margin-bottom: 10px;
    width: 100%;
    border: 1px solid #ddd;
}

.panel-heading{
    width: 100%;
}

.modal-header{
    border-bottom: 2px solid #2c2c2c;
    width: 100%;
    font-size: 20px;
    font-size: 3vh
}

.add-clinic{
    max-width: 65%;
    min-width: 65%;
    align-self: center;
    height: 100%;
    align-self: auto;
}

.reg-clinic{
    border-radius: 10px;
    margin-left: 0px;
    margin-right: 0px;
    border-style: solid;
    border-width: 1px;
    background-color: #fdffff;
    padding-top: 5px;
    margin-bottom: 10px;
    background: #f1f1f1;
}

.clinic-list{
    width: auto;
    margin: 0px;
    height: auto;
}

.clinic-item{
    width: auto;
    margin: 0px;
    border-radius: 10px;
    border-style: dashed;
    border-width: 2px;
    margin-top: 10px;
    background: #f1f1f1;
    border-color: black;
    padding: 3px;
}

.div-desc{
    border-width: 1px; 
    border-style: solid; 
    border-color: black;
    padding: 5px;
}

.detailsX[open] .content {
    animation: sweepin .5s ease-in-out;
}

.detailsX[close] .content {
    animation: sweepout .5s ease-in-out;
}

@keyframes sweepin {
  0% {
    opacity: 0;
    margin-left: -10px
  }
  100% {
    opacity: 1;
    margin-left: 0px
  }
}

@keyframes sweepout {
  0% {
    opacity: 1;
    margin-left: 0px
  }
  100% {
    opacity: 0;
    margin-left: -10px
  }
}
</style>
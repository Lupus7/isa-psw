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
                <input type="text" class="form-control" id="clinic" required aria-describedby="emailHelp" placeholder="Choose a clinic">
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
        <div class="clinic-list overflow-auto">
            <div class="row clinic-item">
                
            </div>
        </div>
    </div>
</div>
</template>

<script>
import axios from 'axios'

export default {
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
            //alert(uloga);
            if (password !== password2){
                alert("Password did not match")
                return
            }
            if(firstname==="" || lastname==="" || password==="" || email==="" || adress==="" || city==="" || state==="" || phone==="" || uniquenum===""){
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
                    "clinic" : "1"
                })
                .then(response=>{
                    if (response.status == 204) {
                        alert("This email  already exists");
                        return;
                    }
                    if(response.status==200){
                        this.$router.go('/profile');
                    }
                })
                .catch(error => {
                    alert("Neuspesno" + error)});
        }
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
    padding-top: 20px;
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
    border-color: #d4d6d6;
    padding-top: 5px;
}

.clinic-list{
    width: auto;
    margin: 0px;
    height: auto;
}

.clinic-item{
    width: auto;
    margin: 0px;
    height: 100px;
    border-radius: 10px;
    border-style: solid;
    border-width: 1px;
    margin-top: 5px;
    background-color: #fdffff;
    border-color: #d4d6d6;
}
</style>
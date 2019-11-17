<template>
    <form id="forma">
		<table>
		<div class="form-group">
            <tr><td><label for="exampleInputEmail1">First name</label></td><td><input type="text" class="form-control" id="firstname" aria-describedby="emailHelp" placeholder="Enter first name" required></td></tr>
		</div>
        <div class="form-group">
            <tr><td><label for="exampleInputEmail1">Last name</label></td><td><input type="text" class="form-control" id="lastname" aria-describedby="emailHelp" placeholder="Enter last name" required></td></tr>
		</div>
		<div class="form-group">
			<tr><td><label for="exampleInputPassword1">Password</label></td><td><input type="password" id="password" class="form-control" placeholder="Password" required></td></tr>
		</div>
        <div class="form-group">
			<tr><td><label for="exampleInputPassword1">Retype Password</label></td><td><input type="password" id="password1" class="form-control" placeholder="Password" required></td></tr>
		</div>
        <div class="form-group">
            <tr><td><label for="exampleInputEmail1">Email</label></td><td><input type="text" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Enter email" required></td></tr>
		</div>
		<div class="form-group">
			<tr><td><label>Adress</label></td><td><input type="text" id="adress" class="form-control" required aria-describedby="emailHelp" placeholder="Enter adress" ></td></tr>
		</div>
        <div class="form-group">
			<tr><td><label>City</label></td><td><input type="text" class="form-control" id="city" required aria-describedby="emailHelp" placeholder="Enter city" ></td></tr>
		</div>	
		<div class="form-group">
			<tr><td><label>State</label></td><td><input type="text" class="form-control" id="state" required></td></tr>
		</div>
		
		<div class="form-group">
			<tr><td><label>Phone number</label></td><td><input type="text" class="form-control" id="phone" required aria-describedby="emailHelp" placeholder="Enter phone"></td></tr>
		</div>
		
		<div class="form-group">
			<tr><td><label>Unique number</label></td><td><input type="number" class="form-control" id="uniqnum" required aria-describedby="emailHelp" placeholder="Enter your unique number"></td></tr>
		</div>
		</table>
	
		<p id="error" hidden="true"></p>
		<p id="success" hidden="true"></p>
       	<button type="submit" v-on:click="register" class="btn btn-primary">Register</button>

	</form>
</template>

<script>
import axios from 'axios'
export default {
    data:function(){
        return{

        }

    },
    methods:{
        register:function(event){
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
                .post('http://localhost:8080/register',{
                    "firstName" : firstname,
                    "lastName" : lastname,
                    "password" : password,
                    "email":email,
                    "address":adress,
                    "city":city,
                    "state" : state,
                    "telephone":phone,
                    "uniqueNum" : uniquenum
                })
                .then(response=>{
                    if (response.status == 204) {
                        alert("This email  already exists");
                        return;
                    }
                    if(response.status==200){
                        alert("Uspesno ste registrovani");
                        this.$router.push('/login');
            
                    }
                })
                .catch(error => {
                    alert("Neuspesno" + error)});

        }
    },
    // mounted: function(){
    //     axios
    //         .get('rest/test')
    //         .then(response => {
    //             alert("Uspesno nakacen");
    //         })
    //         .catch(error => {
    //             alert("Neuspesno");
    //         });
}
</script>
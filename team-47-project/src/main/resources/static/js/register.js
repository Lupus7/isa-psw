Vue.component("register",{
    data:function(){
        return{

        }

    },
    template:`
	<body>
	<form id="forma">
		<table>
		 <div class="form-group">
			<tr><td> <label for="exampleInputEmail1">Korisnicko ime:</label></td><td><input type="text" class="form-control" id="username" aria-describedby="emailHelp" placeholder="Enter username" required></td></tr>
		</div>
		<div class="form-group">
			<tr><td><label for="exampleInputPassword1">Password</label></td><td><input type="password" id="password" class="form-control" placeholder="Password" required></td></tr>
		</div>
		<div class="form-group">
			<tr><td><label>Ime</label></td><td><input type="text" id="ime" class="form-control" required aria-describedby="emailHelp" placeholder="Enter name" ></td></tr>
		</div>	
		<div class="form-group">
			<tr><td><label>Prezime</label></td><td><input type="text" class="form-control" id="prezime" required></td></tr>
		</div>
		
		<div class="form-group">
			<tr><td><label>Kontakt telefon</label></td><td><input type="text" class="form-control" id="kontakt" required aria-describedby="emailHelp" placeholder="Enter phone"></td></tr>
		</div>
		<div class="form-group">
			<tr><td><label>Grad</label></td><td><input type="text" class="form-control" id="grad" required aria-describedby="emailHelp" placeholder="Enter city"></td></tr>
		</div>
		<div class="form-group">
			<tr><td><label>Email adresa</label></td><td><input type="text" class="form-control" id="email" required aria-describedby="emailHelp" placeholder="Enter email"></td></tr>
		</div>
		<div class="form-group">
			<tr><td><label>Datum registracije</label></td><td>
				<input type="date" id="datum" class="form-control"
      			 value="2018-07-22"
      			 min="2018-01-01" max="2020-12-31"></td></tr>
			<tr><td><button type="submit" v-on:click="register" class="btn btn-primary">Register</button></td></tr>
		</div>
		</table>
	
		<p id="error" hidden="true"></p>
		<p id="success" hidden="true"></p>
	</form>
	</body>
	
	
	`,
    methods:{
        register:function(event){
            event.preventDefault();
            let username=document.getElementById("username").value;
            let password=document.getElementById("password").value;
            let ime=document.getElementById("ime").value;
            let prezime=document.getElementById("prezime").value;
            let uloga=document.getElementById("uloga").value;
            alert(uloga);
            let kontakt=parseInt(document.getElementById("kontakt").value);
            let grad=document.getElementById("grad").value;
            let email=document.getElementById("email").value;
            let datum=document.getElementById("datum").value;
            if(username==="" || password==="" || ime==="" || prezime==="" || uloga==="" || kontakt==="" || grad==="" || email===""){
                alert("Popunite sva polja");
                return;
            }

            // //alert("STIGLI DOOVDE");
            // axios
            //     .post('rest/register',{
            //         "korisnickoIme" : username,
            //         "lozinka" : password,
            //         "ime" : ime,
            //         "prezime":prezime,
            //         "uloga":uloga,
            //         "kontaktTelefon":kontakt,
            //         "grad" : grad,
            //         "email":email,
            //         "datumRegistracije" : datum
            //     })
            //     .then(response=>{
            //         if (response.status == 204) {
            //             alert("This username is already exists");
            //             return;
            //         }
            //         if(response.status==200){
            //             alert("Uspesno ste registrovani");
            //             router.push('/login');
            //
            //         }
            //     })
            //     .catch(error => {
            //         alert("Neuspesno")});

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
    });



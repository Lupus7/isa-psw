Vue.component("login",{
    data: function(){
        return{
            email:"",
            password:"",
        }
    },
    methods:{
        register: function(){
            router.push("/register")
        },
        validation: function(e) {
            //let email = document.getElementById("email").val
            e.preventDefault()
            if((this.email=="") || (this.password=="")){
                alert("Please fill out all the fileds")
                return
            }
            alert(this.email + this.password)
            let rr = document.getElementById("password").value
            console.log("Pass jeK"+ rr)
            console.log("Email:"+this.email)
            axios
                .post('/login', {
                        "email": this.email,
                        "password": this.password
                    })
                .then(response=>{
                    if (response.data == null){
                        alert['error']("user not found")
                        return
                    }
                    toast("successfully logged in");
                    router.push("/register")
                })
                .catch(error=>{
                    alert("Neuspesno");
                    return
                });
        }
    },

    template:`
    <body>
    <div class="container">
    <div class="row">
    	<div class="col-md-4 col-md-offset-4">
    		<div class="panel panel-default">
			  	<div class="panel-heading">
			    	<h3 class="panel-title">Login via site</h3>
			 	</div>
			  	<div class="panel-body">
			    	<form accept-charset="UTF-8" role="form">
                    <fieldset>
			    	  	<div class="form-group">
			    		    <input class="form-control" placeholder="Email.." name="email" v-model="email" type="text">
			    		</div>
			    		<div class="form-group">
			    			<input class="form-control"  id="password" placeholder="Password" name="password" v-model="password" type="password">
			    		</div>
			    		
			    		<input class="btn btn-lg btn-success btn-block" @click.prevent="validation" type="submit" value="Login">
			    	</fieldset>
			      	</form>
                      <hr/>
                    <center><h4>OR</h4></center>
                    <input class="btn btn-lg btn-facebook btn-block" type="submit" v-on:click="validation($event)" value="Register">
			    </div>
			</div>
		</div>
	</div>
	</div>
    </body>
    `
});


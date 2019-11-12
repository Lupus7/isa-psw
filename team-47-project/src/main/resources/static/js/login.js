Vue.component("login",{
    data: function(){
        return{
            email: "",
            password:"",
        }
    },
    methods:{
        register: function(){
            router.push("/register")
        },
        validation:function () {

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
			    			<input class="form-control" placeholder="Password" name="password" v-model="password" type="password" value="">
			    		</div>
			    		
			    		<input class="btn btn-lg btn-success btn-block" v-on:click="validation" type="submit" value="Login">
			    	</fieldset>
			      	</form>
                      <hr/>
                    <center><h4>OR</h4></center>
                    <input class="btn btn-lg btn-facebook btn-block" type="submit" v-on:click="register" value="Register">
			    </div>
			</div>
		</div>
	</div>
	</div>
    </body>
    `
});


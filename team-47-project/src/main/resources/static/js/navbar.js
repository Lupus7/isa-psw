new Vue({
    el:"#nav",
    template:`<nav class="navbar navbar-dark bg-dark">
        <span> <button id="buttLg" class="btn-info" disabled> </button> </span>
        <a class="navbar-brand" v-on:click="homepage">Home</a>
        <a class="navbar-brand" v-on:click="profile" href="#">Profile</a>
        <a class="navbar-brand" v-on:click="login" href="#" id="log">Login</a>
        <a class="navbar-brand" v-on:click="register" href="#" id="reg">Register</a>
        <a class="navbar-brand" v-on:click="logout" href="#" id="logo"></a>
        <label></label>
    </nav>`,
    methods:{
        homepage: function(){
            router.push("/")
        },

        profile: function(){
            router.push("/profile")
        },
        login: function(){
            router.push("/login")
        },
        register: function(){
            router.push("/register")
        },
        logout: function(){
            
            document.getElementById("log").innerHTML="Login";
            document.getElementById("reg").innerHTML="Register";
            document.getElementById("logo").innerHTML="";
            document.getElementById("buttLg").innerHTML="";
                    
            router.push("/")
        }
      
    }
})
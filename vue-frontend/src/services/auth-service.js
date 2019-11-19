import axios from 'axios'

const API_URL = 'http://localhost:8080/auth/';
class AuthService{
    login(user){
        return axios
        .post(API_URL+"login",{
            email:user.email,
            password:user.password
        })
        .then(this.handleResponse)
        .then(response=>{
            if(response.data.accessToken){
                localStorage.setItem('user',JSON.stringify(response.data));
            }
            return response.data;
        });
    }
    logout(){
        localStorage.removeItem('user')
    }
}
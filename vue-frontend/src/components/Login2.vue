<template>
  <div class="col-md-12">
    <div class="card card-container">
      
      <form name="form" @submit.prevent="handleLogin">
        <div class="form-group">
          <label for="username">Email</label>
          <input
            type="text"
            class="form-control"
            name="username"
            v-model="this.user.email"
          />
        </div>
        <div class="form-group">
          <label for="password">Password</label>
          <input
            type="password"
            class="form-control"
            name="password"
            v-model="this.user.password"
          />
        </div>
        <div class="form-group">
          <button class="btn btn-primary btn-block" :disabled="loading">
            <span class="spinner-border spinner-border-sm" v-show="loading"></span>
            <span>Login</span>
          </button>
        </div>
        <div class="form-group">
          <div class="alert alert-danger" role="alert" v-if="message">{{message}}</div>
        </div>
      </form>
    </div>
  </div>
</template>


<script>
import User from '../models/user';
import { response } from 'express';
import axios from 'axios'

export default {
  name: 'login2',
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    }
  },
  data() {
    return {
      user: new User('', ''),
      loading: false,
      message: '',
    };
  },
  mounted() {
    if (this.loggedIn) {
      this.$router.push('/');
    }
    axios 
    .get('http://localhost:8081/auth/test')
    .then(response=>{
      alert("Uspeo")
    })
    .catch(error=>{
      alert("Nije uspeo")
    })
  },
  methods: {
    
  }
};
</script>
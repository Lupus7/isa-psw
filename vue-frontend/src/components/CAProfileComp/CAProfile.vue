<template>
       <div class="container">
        
        <div class="row">
            <div class="col-12">
                <div class="card">

                    <div class="card-body">
                        <div class="card-title mb-4">
                            <div class="d-flex justify-content-start">
                                <div class="image-container">
                                    <img src="http://ssl.gstatic.com/accounts/ui/avatar_2x.png" id="imgProfile" style="width: 150px; height: 150px; margin-bottom: 5px" class="img-thumbnail" />
                                  
                                </div>
                                <div class="userData ml-3">
                                    <h2 class="d-block" style="font-size: 1.5rem; font-weight: bold">{{user.email}}</h2>
                                    <h4 class="d-block" style="font-size: 1.5rem; font-weight: bold">Clinical Admin</h4>
                                </div>
                               
                            <div class="ml-auto" style="margin-top: -2vh">
                                    <div class="middle">
                                        <br>
                                        <button type="button" class="btn btn-success btn-block "  style=" border-radius: 6px; padding:8px ">Change data </button>
                                    </div>
                                    <div class="middle">
                                        <br>
                                        <button type="button" class="btn btn-dark btn-block "  style=" border-radius: 6px;  color: white; padding:8px" >Change password </button>
                                    </div>
                                    <div class="middle">
                                        <br>
                                        <button type="button" class="btn btn-primary btn-block " @click="showReqs" style=" border-radius: 6px;  color: white; padding:8px " >Staff requests </button>
                                    </div>
                                   
                                </div>

                            </div>
                        </div>

                        <div class="row">
                            <div class="col-12">
                                <ul class="nav nav-tabs mb-4" id="myTab" role="tablist">
                                    <li class="nav-item">
                                        <a class="nav-link active" id="basicInfo-tab" data-toggle="tab"  role="tab" aria-controls="basicInfo" aria-selected="true">Basic Info</a>
                                    </li>                               
                                  
                                </ul>
                                <div class="tab-content ml-1" id="myTabContent">
                                    <div class="tab-pane fade show active" id="basicInfo" role="tabpanel" aria-labelledby="basicInfo-tab">
                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">Full Name</label>
                                            </div>
                                            <div class="col-md-8 col-6">
                                                {{user.firstName +" "+ user.lastName}}
                                            </div>
                                        </div>
                                        <hr />

                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">Address</label>
                                            </div>
                                            <div class="col-md-8 col-6">
                                               {{user.address}}
                                            </div>
                                        </div>
                                        <hr />
                                        
                                        
                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">City</label>
                                            </div>
                                            <div class="col-md-8 col-6">
                                                 {{user.city}}
                                            </div>
                                        </div>
                                        <hr />
                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">State</label>
                                            </div>
                                            <div class="col-md-8 col-6">
                                                 {{user.state}}
                                            </div>
                                        </div>
                                        <hr />
                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">Telephone</label>
                                            </div>
                                            <div class="col-md-8 col-6">
                                                 {{user.telephone}}
                                            </div>
                                        </div>
                                        <hr />
                                         <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">Unique Number</label>
                                            </div>
                                            <div class="col-md-8 col-6">
                                                 {{user.uniqueNum}}
                                            </div>
                                        </div>  
                                    </div>
                                    
                                 
                                 
                                </div>
                            </div>
                        </div>
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
            user: []
        }
    },
    methods:{
        getCA(){
            axios.get('http://localhost:8080/ca/getInfo').then(response => { this.user = response.data; })
        },

        showReqs(e) {
            e.preventDefault()
            this.$router.push("/requestsTable");

        },
        
    },
    created() {
        this.getCA()
    }
}
</script>

<style scoped>

  .container{
        margin-top: 5vh;
        max-width: auto;
        padding: 40px;
        background: #f1f1f1;
        border-radius: 8px;
        align-self: center;
        border: 1px dotted grey;
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif

    }


</style>
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
                                    <div class="middle">
                                        <input type="button" class="btn btn-secondary btn-block" id="btnChangePicture" value="Change data" />
                                        <input type="file" style="display: none;" id="profilePicture" name="file" />
                                    </div>
                                </div>
                                <div class="userData ml-3">
                                    <h2 class="d-block" style="font-size: 1.5rem; font-weight: bold">{{user.email}}</h2>
                                    <h4 class="d-block" style="font-size: 1.5rem; font-weight: bold">Clinical Centre Admin</h4>
                                </div>
                                <div class="ml-auto">
                                    <input type="button" class="btn btn-primary d-none" id="btnDiscard" value="Discard Changes" />
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-12">
                                <ul class="nav nav-tabs mb-4" id="myTab" role="tablist">
                                    <li class="nav-item">
                                        <a class="nav-link active" v-on:click="activateProf()" id="basicInfo-tab" data-toggle="tab" href="#profile/basicInfo" role="tab" aria-controls="basicInfo" aria-selected="true">Basic Info</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" v-on:click="activateList()" id="regReq-tab" data-toggle="tab" href="#profile/regReq" role="tab" aria-controls="regReq">Registration Requests</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" v-on:click="activateAdd()" id="addAdmin-tab" data-toggle="tab" href="#profile/addAdmin" role="tab" aria-controls="addAdmin">Add Administrators / Clinics</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" v-on:click="activateCode()" id="codebook-tab" data-toggle="tab" href="#profile/codebook" role="tab" aria-controls="codebook">Codebook</a>
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
                                    <div class="tab-pane fade show" id="regReq" role="tabpanel" aria-labelledby="regReq-tab">
                                        <ReqListTab/>
                                    </div>
                                    <div class="tab-pane fade show" id="addAdmin" role="tabpanel" aria-labelledby="addAdmin-tab">
                                        <AddAdmins/>
                                    </div> 
                                    <div class="tab-pane fade show" id="codebook" role="tabpanel" aria-labelledby="codebook-tab">
                                        CODEBOOK
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
import ReqListTab from './ReqListTab'
import AddAdmins from './AddAdmins'

export default {
    data() {
        return {
            user: []
        }
    },
    methods: {
        activateProf() {
            document.getElementById("regReq-tab").classList.remove("active");            
            document.getElementById("regReq").classList.remove("active");
            document.getElementById("basicInfo-tab").classList.add("active");
            document.getElementById("basicInfo").classList.add("active");
            document.getElementById("addAdmin-tab").classList.remove("active");            
            document.getElementById("addAdmin").classList.remove("active");
            document.getElementById("codebook-tab").classList.remove("active");            
            document.getElementById("codebook").classList.remove("active");
        },
        activateList() {
            document.getElementById("basicInfo-tab").classList.remove("active");
            document.getElementById("basicInfo").classList.remove("active");
            document.getElementById("regReq-tab").classList.add("active");
            document.getElementById("regReq").classList.add("active");
            document.getElementById("addAdmin-tab").classList.remove("active");            
            document.getElementById("addAdmin").classList.remove("active");
            document.getElementById("codebook-tab").classList.remove("active");            
            document.getElementById("codebook").classList.remove("active");
        },
        activateAdd() {
            document.getElementById("basicInfo-tab").classList.remove("active");
            document.getElementById("basicInfo").classList.remove("active");
            document.getElementById("regReq-tab").classList.remove("active");
            document.getElementById("regReq").classList.remove("active");
            document.getElementById("addAdmin-tab").classList.add("active");            
            document.getElementById("addAdmin").classList.add("active");
            document.getElementById("codebook-tab").classList.remove("active");            
            document.getElementById("codebook").classList.remove("active");
        },
        activateCode() {
            document.getElementById("basicInfo-tab").classList.remove("active");
            document.getElementById("basicInfo").classList.remove("active");
            document.getElementById("regReq-tab").classList.remove("active");
            document.getElementById("regReq").classList.remove("active");
            document.getElementById("addAdmin-tab").classList.remove("active");            
            document.getElementById("addAdmin").classList.remove("active");
            document.getElementById("codebook-tab").classList.add("active");            
            document.getElementById("codebook").classList.add("active");
        },
        getCCA(){
            axios
                .get('http://localhost:8080/cca/getInfo')
                .then(response => { this.user = response.data; })
        }
    },
    components: {
        ReqListTab,
        AddAdmins
    },
    created() {
        this.getCCA()
    }
}
</script>
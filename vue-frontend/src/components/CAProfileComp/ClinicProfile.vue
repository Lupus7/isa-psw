<template>

  <div class="container">
        
        <div class="row">
            <div class="col-12">
                <div class="card">

                    <div class="card-body">
                        <div class="card-title mb-4">
                            <div class="d-flex justify-content-start">
                                <div class="image-container">
                                    <img src="../../../public/assets/clinic.png"  style="width: 150px; height: 150px; margin-bottom: 10px" class="img-thumbnail" />
                                     
                                     <div class="middle">                    
                                        <button type="button" class="btn btn-success btn-block " @click="changeClinicData" style=" border-radius: 6px; padding:5px ; margin-bottom: 10px">Change data </button>
                                    </div>
                                  
                                </div>
                                <div class="userData ml-3">

                                    <h4 class="d-block" style="font-size: 1.5rem; font-weight: bold">Clinic Profile</h4>
                                   
                                </div>
                               
                            <div class="ml-auto" style="margin-top: 0vh " >
                                  
                                    <div class="middle">                                     
                                        <button type="button" class="btn btn-block " @click="rooms" style=" border-radius: 6px;  color: white; padding:10px; background:#ae4102; margin-right:80px" >Rooms </button> 
                                    </div>

                                     <div class="middle">
                                        <button type="button" class="btn btn-block " @click="doctors" style=" border-radius: 6px;  color: white; padding:10px; background:#3964eb; margin-top: 15px;" >Doctors </button>
                                    </div>
                                   
                                </div>

                            </div>
                        </div>

                        <div class="row">
                            <div class="col-12">
                                
                                <ul class="nav nav-tabs mb-4" role="tablist">
                                    <li class="nav-item">
                                        <a class="nav-link  active"   role="tab" aria-controls="basicInfo" aria-selected="true">Basic Info</a>
                                    </li> 

                                                                                                           
                                </ul>
                                <div class="tab-content ml-1" >
                                    <div class="tab-pane fade show active"  role="tabpanel" aria-labelledby="basicInfo-tab">
                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">Clinic Name</label>
                                            </div>
                                            <div class="col-md-8 col-6">
                                                {{clinic.name}}
                                            </div>
                                        </div>
                                        <hr />

                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">Address</label>
                                            </div>
                                            <div class="col-md-8 col-6">
                                                {{clinic.address}}
                                            </div>
                                        </div>
                                        <hr />
                                        
                                        
                                        <div class="row">
                                            <div class="col-sm-3 col-md-2 col-5">
                                                <label style="font-weight:bold;">About</label>
                                            </div>
                                            <div class="col-md-8 col-6">
                                                <textarea class="form-control" :disabled=true v-text="clinic.description"> 

                                                </textarea>

                                            </div>
                                        </div>
                                        <hr />
                                       
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
            clinic : [],
           
        }
    },
    methods:{

        getClinic(){
            axios.get('http://localhost:8080/clinic/getInfo').then(response => { this.clinic = response.data; })
        },

        doctors(e){
            e.preventDefault()
            this.$router.push("/caDoctors");
        },
        rooms(e){
             e.preventDefault();
              this.$router.push("/rooms");

        },
        changeClinicData(e){
            e.preventDefault()
            this.$router.push("/changeClinicData");
        },
    },
    created() {
        this.getClinic();
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
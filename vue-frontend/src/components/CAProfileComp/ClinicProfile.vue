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

                                    <div class="middle">
                                        <button type="button" class="btn btn-block " @click="priceList" style=" border-radius: 6px;  color: white; padding:10px; margin-top: 15px; background:teal" >Pricelist </button>
                                    </div>

                                    <div class="middle">
                                        <button type="button" id="examButtoon" class="btn btn-block " @click="examRequest" style=" border-radius: 6px;  color: white; padding:10px; margin-top: 15px; background:indigo "  >Examination requests </button>
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
                                                <textarea class="form-control" rows="4" :disabled=true v-text="clinic.description"> 

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

        <template>
            <yandex-map 
                :coords= pos
                zoom="12"
                style="width: auto; height: 400px;"
                map-type="map"
                >

                <ymap-marker 
                    markerId = "1"
                    marker-type="placemark"
                    :coords= pos
                    :hint-content = clinicAddress
                ></ymap-marker>

            </yandex-map>
        </template>

    </div>


    
    
</template>

<script>
import axios from 'axios'

export default {
    
   data() {
        return {
            clinic : [],
            clinicAddress:'',
            pos:[50,50]
           
        }
    },
    methods:{

        getClinic(){
            axios.get('http://localhost:8080/clinic/getInfo').then(response => { 
                this.clinic = response.data;
                this.clinicAddress = this.clinic.address
                console.log(this.clinicAddress)
                let s=this.clinic.address.split(",");
                console.log(s)
                let adr = s[0]+" "+s[1]+" "+s[2]
                
                let url='https://geocoder.api.here.com/6.2/geocode.json?app_id=aJx1PxrXFwpMDT0M30rJ&app_code=am23BxvdgXkXf2c15NUZgw&searchtext='+adr.replace(/ /g,'+');

                fetch(url,{
                  headers:{}  
                }).then(res => res.json()).then(res => {
                    console.log(res)
                    this.pos = []
                    let lat = res.Response.View[0].Result[0].Location.DisplayPosition.Latitude;
                    let lon = res.Response.View[0].Result[0].Location.DisplayPosition.Longitude;
                    this.pos= [lat,lon]
                })
             })
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
        priceList(e){
            e.preventDefault()
            this.$router.push("/priceList");
        },
        examRequest(e){
            e.preventDefault()
            this.$router.push("/examRequests");
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
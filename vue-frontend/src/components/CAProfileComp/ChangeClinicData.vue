<template>
    <div class= "container">
        <br>
        <div class="col-md-9">
                <div class="card">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-12">
                                <h4>Clinic Info</h4>
                                <hr>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <form>
                                <div class="form-group row">
                                    <label for="username" class="col-4 col-form-label">Clinic Name</label> 
                                    <div class="col-8">
                                    <input class="form-control here" type="text" v-model="name" > 
                                       
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="name" class="col-4 col-form-label">Clinic Address</label> 
                                    <div class="col-8">
                                    <input  class="form-control here" type="text"  v-model="address">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-4 col-form-label">Clinic Descrption</label> 
                                    <div class="col-8">
                                    <textarea class="form-control" rows="6"  v-model="description"></textarea>
                                    </div>

                                </div>
                               
                                <div class="modal-footer">
                                        
                                    <button name="submit" type="submit" class="btn btn-primary" v-on:click="update" >Confirm</button>
                                    <button name="submit" type="submit" class="btn btn-secondary" v-on:click="cancel" >Cancel</button>                              
                                    
                                </div>
                                   
                                
                                </form>
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
     data(){
        return{
            clinic : [],
            name: "",
            address: "",
            description: "",
        }
        
    },
     methods:{

       getClinic(){
            axios.get('http://localhost:8080/clinic/getInfo').then(response => { 
                this.clinic = response.data;
                this.name = this.clinic.name;
                this.address = this.clinic.address;
                this.description = this.clinic.description;
            })
        },

        cancel(){
            this.$router.push("/clinicProfile");
        },
        
        update(){
            axios.post('http://localhost:8080/clinic/updateInfo', { "name": this.name, "address":this.address, "description": this.description }).then(response => { 
                this.cancel();
            })


        },     

    },
    created(){
       this.getClinic();
    },
  
}
</script>

<style scoped>

.container{
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif

}

</style>
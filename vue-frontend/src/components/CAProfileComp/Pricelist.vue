<template>
    <div>
        <div class="panel-body"  >
            
        <table class="table table-striped table-hoover" >
            <thead >
                
                <tr class="thead-dark">
                    <th colspan="8"> <center> <h2> Price list of Examination Types </h2> </center> </th>                  
                </tr>

                <tr class="table-secondary">
                    <th align="justify"  style="font-size:18px">Name</th>
                    <th align="justify"  style="font-size:18px">Price[$]</th>
                    <th> </th>
                </tr>
            </thead>

            <template v-if="examTypes != null" >
            <tbody>
                
                <tr v-for="(exType, index) in examTypes" :key="index">
                
                    <td>{{exType.name}}</td>
                    <td>{{exType.price}}</td>
                  
                    <td style="max-width: 80px">
                        <button type="button" class="btn " @click="showEdit(exType)" style="padding-right:40px;padding-left:40px; font-size:17px;background:#d66e20;color:white">Edit</button>
                    </td>

                </tr>
            </tbody>
            </template>
        </table>

        
        <br>

          <div class="wrapper" v-if=" show "  >
            <div style=" padding-left:2vh; padding-right:175vh">
                <div class="modal-header" style="margin-left:2px; margin-right: 100vh; ">
                    <h3 v-if="editForm"> Edit Examination Type</h3>
                </div>
            </div>
                    
                <div class="panel-body" style="padding-left:2vh; padding-right: 175vh; " >
                    <br>
                    <form accept-charset="UTF-8" role="form"  >

                        <div class="control-group" >
                            <label  class="control-label" style=" font-size: 2vh" >Name of examination type:</label>
                            <div class="form-group">
                                <input class="form-control"  style=" font-size: 2vh; "  placeholder="Name of examination type..." v-model="nameF" />
                            </div>
                        </div>

                        <div class="control-group" >
                            <label  class="control-label" style=" font-size: 2vh" >Specialization:</label>
                            <div class="form-group">
                                <input class="form-control"  style=" font-size: 2vh; "  placeholder="Specialization..." v-model="specializationF" />
                            </div>
                        </div>

                          <div class="control-group" >
                            <label  class="control-label" style=" font-size: 2vh" >Price[$]:</label>
                            <div class="form-group">
                                <input class="form-control"  style=" font-size: 2vh; "  placeholder="Price..." v-model="priceF" />
                            </div>
                        </div>
                        
                        <br>
                        <div class="modal-footer" >
                            <button v-if="addForm" class="btn btn-success " style="font-size:20px" @click="add($event)">Add</button>
                            <button v-if="editForm" class="btn btn-success " style="font-size:20px" @click="edit($event)">Edit</button>
                            <button class="btn btn-danger " @click="cancel($event)">Cancel</button>
                        </div>      
                    </form>
                                
                </div>

                 <br>
        </div>
                            
    </div>
       

</div>
    
</template>

<script>
import axios from 'axios'
import {funToastr} from "../../toastr.js"

export default {
    

     data() {
        return {
            examTypes: [],
            name:"",
            show : false,
            editForm: false,
            nameF: "",
            specializationF:"",
            priceF:'',
            idF:"",
            idEt:""
        }
    },
    methods:{
       
        getExamTypes(){
            axios.get('http://localhost:8080/ca/getExaminTypes').then(response => { this.examTypes = response.data; })
        },
     
        showEdit(exT){
            this.show = true;
            this.editForm = true;
            this.nameF = exT.name;
            this.priceF = exT.price;
            this.specializationF = exT.specialization;
            this.idF = exT.id;
        },
        cancel(){
            this.show = false;
        },

        edit(e){

            e.preventDefault();
            axios.post('http://localhost:8080/ca/editExaminType', { "name": this.nameF, "id":this.idF, 'specialization': this.specializationF, "price":this.priceF }).then(response => { 
                
                if(response.status == 200){
                    this.getExamTypes();
                    this.show = false;
                    this.editForm = false;
                    funToastr("s","Examination type successfuly edited!","Examination type!");

                }else
                    funToastr("d",response.data,"Examination type!");


            })

        },
    },
    created() {
        this.getExamTypes();
    }
    
}
</script>

<style scoped>

.modal-header{

  border-bottom: 1px solid #8d8d8d;
  width: 100%;
  font-size: 20px;
  font-size: 3vh

}

.modal-footer{

  border-top: 1px solid #8d8d8d;
  width: 100%;
  font-size: 20px;
  font-size: 3vh
}

</style>
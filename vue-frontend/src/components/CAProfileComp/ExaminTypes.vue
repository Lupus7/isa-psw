<template>
    <div>
        <div class="panel-body"  >
            
        <table class="table table-striped table-hoover" >
            <thead >
                
                <tr class="thead-dark">
                    <th colspan="8"> <center> <h2> Examination Types </h2> </center> </th>                  
                </tr>

                <tr class="table-secondary">
                    <th align="justify"  style="font-size:18px">Id</th>
                    <th align="justify"  style="font-size:18px">Name</th>
                    <th align="justify"  style="font-size:18px">Specialization</th>
                    <th align="justify"  style="font-size:18px">Price[$]</th>
                    <th> </th>
                    <th> </th>
                </tr>
            </thead>

            <template v-if="examTypes != null" >
            <tbody>
                
                <tr v-for="(exType, index) in examTypes" :key="index">
                
                    <td>{{exType.id}}</td>
                    <td>{{exType.name}}</td>
                    <td>{{exType.specialization}}</td>
                    <td>{{exType.price}}</td>
                  
                    <td style="max-width: 80px">
                        <button type="button" class="btn " @click="showEdit(exType)" style="padding-right:40px;padding-left:40px; font-size:17px;background:#d66e20;color:white">Edit</button>
                    </td>
                    <td style="max-width: 80px">
                        <button type="button" class="btn btn-danger" @click="remove(exType)" style="padding-right:32px;padding-left:32px; font-size:17px">Remove</button>
                    </td>


                </tr>
            </tbody>
            </template>
        </table>
        <div>
            <div class="modal-footer">
                 <button type="button" class="btn btn-success" @click="showAdd()" style="padding-right:32px;padding-left:32px; font-size:18px">New Examination Type</button>
            </div>
        </div>

        
        <br>

          <div class="wrapper" v-if=" show "  >
            <div style=" padding-left:2vh; padding-right:175vh">
                <div class="modal-header" style="margin-left:2px; margin-right: 100vh; ">
                    <h3 v-if="addForm"> New Examination Type</h3>
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




        <br>

        <div class="jumbobox"  >
            <div style=" margin-left:6px; margin-right: 160vh">
                <div class="modal-header" style="margin-left:2px; margin-right: 160vh; ">
                    <h3>Search</h3>
                </div>
            </div>
                    
                <div class="panel-body" style="margin-left:8px; margin-right: 160vh; " >
                    <br>
                    <form accept-charset="UTF-8" role="form"  >

                        <div class="control-group" >
                            <label  class="control-label" style=" font-size: 2vh" >Name of examination type:</label>
                            <div class="form-group">
                                <input class="form-control"  style=" font-size: 2vh; "  placeholder="Name of examination type..." v-model="name" />
                            </div>
                        </div>
                        <div class="control-group" >
                            <label  class="control-label" style=" font-size: 2vh" >ID of examination type:</label>
                            <div class="form-group">
                                <input class="form-control"  style=" font-size: 2vh; "  placeholder="ID of examination type..." v-model="idEt" />
                            </div>
                        </div>
                        
                        <br>
                        <div class="modal-footer" >
                            <button class="btn btn-success btn-lg" @click="search($event)">Confirm</button>
                        </div>      
                    </form>
                                
                </div>
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
            addForm: false,
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
        search(e){
            e.preventDefault();
            axios.post('http://localhost:8080/ca/searchExaminTypes', { "name": this.name, "id": this.idEt }).then(response => { this.examTypes = response.data; })
        },
        showAdd(){
            this.show = true;
            this.addForm = true;
            this.editForm = false;
            this.nameF = "";
            this.priceF = "";
            this.specializationF = "";
            this.idF = ""

        },
        showEdit(exT){
            this.show = true;
            this.editForm = true;
            this.addForm = false;
            this.nameF = exT.name;
            this.priceF = exT.price;
            this.specializationF = exT.specialization;
            this.idF = exT.id;
        },
        cancel(){
            this.show = false;
        },

        add(e){

            e.preventDefault();
            axios.post('http://localhost:8080/ca/addExaminType', { "name": this.nameF,'specialization': this.specializationF, "price":this.priceF }).then(response => { 
            
                if(response.status == 200){
                    this.getExamTypes();
                    this.show = false;
                    this.addForm = false;
                    this.editForm = false;
                    funToastr("s","Examination type successfuly added!","Examination type!");
                }else
                    funToastr("d",response.data,"Examination type!");

            
            })

        },
        edit(e){

            e.preventDefault();
            axios.post('http://localhost:8080/ca/editExaminType', { "name": this.nameF, "id":this.idF, 'specialization': this.specializationF, "price":this.priceF }).then(response => { 
                
                if(response.status == 200){
                    this.getExamTypes();
                    this.show = false;
                    this.addForm = false;
                    this.editForm = false;
                    funToastr("s","Examination type successfuly edited!","Examination type!");

                }else
                    funToastr("d",response.data,"Examination type!");


            })

        },
        remove(exT){

           
            axios.post('http://localhost:8080/ca/removeExaminType', { "id": exT.id, }).then(response => { 
            
                if(response.status == 200){
                    this.getExamTypes();
                    this.show = false;
                    this.addForm = false;
                    this.editForm = false;
                    funToastr("s","Examination type successfuly removed!","Examination type!");
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
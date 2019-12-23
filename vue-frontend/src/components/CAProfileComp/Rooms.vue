<template>
    <div>
        <div class="panel-body"  >
            
        <table class="table table-striped table-hoover" >
            <thead >
                
                <tr class="thead-dark">
                    <th colspan="8"> <center> <h2> Rooms </h2> </center> </th>                  
                </tr>

                <tr class="table-secondary">
                    <th align="justify"  style="font-size:18px">Id</th>
                    <th align="justify"  style="font-size:18px">Name</th>
                    <th align="justify"  style="font-size:18px">Type</th>
                    <th align="justify"  style="font-size:18px">Number</th>
                    <th> </th>
                    <th> </th>
                </tr>
            </thead>

            <template v-if="rooms != null" >
            <tbody>
                
                <tr v-for="(room, index) in rooms" :key="index">
                
                    <td>{{room.id}}</td>
                    <td>{{room.name}}</td>
                    <td>{{room.type}}</td>
                    <td>{{room.number}}</td>
                  
                    <td style="max-width: 80px">
                        <button type="button" class="btn " @click="showEdit(room)" style="padding-right:40px;padding-left:40px; font-size:17px;background:#d66e20;color:white">Edit</button>
                    </td>
                    <td style="max-width: 80px">
                        <button type="button" class="btn btn-danger" @click="remove(room)" style="padding-right:32px;padding-left:32px; font-size:17px">Remove</button>
                    </td>


                </tr>
            </tbody>
            </template>
        </table>
        <div>
            <div class="modal-footer">
                 <button type="button" class="btn btn-success" @click="showAdd()" style="padding-right:32px;padding-left:32px; font-size:18px">Add New Room</button>
            </div>
        </div>

        
        <br>

          <div class="wrapper" v-if=" show "  >
            <div style=" padding-left:2vh; padding-right:175vh">
                <div class="modal-header" style="margin-left:2px; margin-right: 100vh; ">
                    <h3 v-if="addForm"> Add New Room</h3>
                    <h3 v-if="editForm"> Edit Room</h3>
                </div>
            </div>
                    
                <div class="panel-body" style="padding-left:2vh; padding-right: 175vh; " >
                    <br>
                    <form accept-charset="UTF-8" role="form"  >

                        <div class="control-group" >
                            <label  class="control-label" style=" font-size: 2vh" >Name of room:</label>
                            <div class="form-group">
                                <input class="form-control"  style=" font-size: 2vh; "  placeholder="Name of room..." v-model="nameF" />
                            </div>
                        </div>

                         <div class="control-group">
                            <label  class="control-label" style=" font-size: 2vh">Number of room:</label>
                            <div class="form-group">
                                <input class="form-control"  style=" font-size: 2vh"  placeholder="Number..." v-model="numberF" />
                            </div>
                        </div>

                        <div class="control-group">
                            <label  class="control-label" style=" font-size: 2vh">Room type:</label>
                            <div class="form-group">
                                  <select class="form-control" style="font-size: 2vh" v-model="typeF"  >
                                    <option align="justify" >Operation</option>
                                    <option align="justify" >Examination</option>
                                </select>
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
            <div style=" margin-left:6px; margin-right: 100vh">
                <div class="modal-header" style="margin-left:2px; margin-right: 100vh; ">
                    <h3>Search</h3>
                </div>
            </div>
                    
                <div class="panel-body" style="margin-left:8px; margin-right: 100vh; " >
                    <br>
                    <form accept-charset="UTF-8" role="form"  >

                        <div class="control-group" >
                            <label  class="control-label" style=" font-size: 2vh" >Name of room:</label>
                            <div class="form-group">
                                <input class="form-control"  style=" font-size: 2vh; "  placeholder="Name of room..." v-model="name" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label  class="control-label" style=" font-size: 2vh">Number of room:</label>
                            <div class="form-group">
                                <input class="form-control"  style=" font-size: 2vh"  placeholder="Number..." v-model="number" />
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
            user: [],
            rooms: [],
            name:"",
            number:"",
            show : false,
            editForm: false,
            addForm: false,
            nameF: "",
            numberF: "",
            typeF: "",
            idF:""
        }
    },
    methods:{
       
        getRooms(){
            axios.get('http://localhost:8080/room/getRooms').then(response => { this.rooms = response.data; })
        },
        search(e){
            e.preventDefault();
            axios.post('http://localhost:8080/room/search', { "name": this.name, "number": this.number }).then(response => { this.rooms = response.data; })
        },
        showAdd(){
            this.show = true;
            this.addForm = true;
            this.editForm = false;
            this.nameF = "";
            this.numberF = "";
            this.idF = ""
            this.typeF = ""

        },
        showEdit(room){
            this.show = true;
            this.editForm = true;
            this.addForm = false;
            this.nameF = room.name;
            this.numberF = room.number;
            this.idF = room.id;
            this.typeF = room.type;

        },
        cancel(){
            this.show = false;
        },

        add(e){

            e.preventDefault();
            axios.post('http://localhost:8080/room/addRoom', { "name": this.nameF, "number": this.numberF,"type":this.typeF }).then(response => { 
            
                if(response.status == 200){
                    this.getRooms();
                    this.show = false;
                    this.addForm = false;
                    this.editForm = false;
                    funToastr("s","Room successfuly added to clinic!","Room!");
                }else
                    funToastr("d",response.data,"Room!");

            
            })

        },
        edit(e){

            e.preventDefault();
            axios.post('http://localhost:8080/room/editRoom', { "name": this.nameF, "number": this.numberF, "id":this.idF, "type":this.typeF }).then(response => { 
                
                if(response.status == 200){
                    this.getRooms();
                    this.show = false;
                    this.addForm = false;
                    this.editForm = false;
                    funToastr("s","Room successfuly edited!","Room!");
                }else
                    funToastr("d",response.data,"Room!");


            })

        },
        remove(room){

           
            axios.post('http://localhost:8080/room/removeRoom', { "id": room.id, }).then(response => { 
            
                if(response.status == 200){
                    this.getRooms();
                    this.show = false;
                    this.addForm = false;
                    this.editForm = false;
                    funToastr("s","Room successfuly removed from clinic!","Room!");
                }else
                    funToastr("d",response.data,"Room!");

            
            })


        },
        
    },
    created() {
        this.getRooms();
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
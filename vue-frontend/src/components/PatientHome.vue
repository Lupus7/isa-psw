
<script>
import axios from 'axios';
import jwt_decode from 'jwt-decode'

import LocalStorageService from "../LocalStorageService";

export default {
    data(){
        return{
            clinics:{},
            examinations:{},
            medicalFile:{}
        }
    },

    methods:{
        getClinics(){
            axios.get('http://localhost:8080/patient/getAllClinics').then(response => { this.clinics = response.data;}).catch(error=>{console.log(error)})
        },
        search(){
          let type = document.getElementById("type").value
          let date = document.getElementById("date").value
          alert (type + date)
        },
    
        sortTable(n){
            var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
        table = document.getElementById("tabela");
        switching = true;
        // Set the sorting direction to ascending:
        dir = "asc";
        /* Make a loop that will continue until
        no switching has been done: */
        while (switching) {
          // Start by saying: no switching is done:
          switching = false;
          rows = table.rows;
          /* Loop through all table rows (except the
          first, which contains table headers): */
          for (i = 1; i < (rows.length - 1); i++) {
            // Start by saying there should be no switching:
            shouldSwitch = false;
            /* Get the two elements you want to compare,
            one from current row and one from the next: */
            x = rows[i].getElementsByTagName("TD")[n];
            y = rows[i + 1].getElementsByTagName("TD")[n];
            /* Check if the two rows should switch place,
            based on the direction, asc or desc: */
            if (dir == "asc") {
              if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                // If so, mark as a switch and break the loop:
                shouldSwitch = true;
                break;
              }
            } else if (dir == "desc") {
              if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                // If so, mark as a switch and break the loop:
                shouldSwitch = true;
                break;
              }
            }
          }
          if (shouldSwitch) {
            /* If a switch has been marked, make the switch
            and mark that a switch has been done: */
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
            // Each time a switch is done, increase this count by 1:
            switchcount ++;
          } else {
            /* If no switching has been done AND the direction is "asc",
            set the direction to "desc" and run the while loop again. */
            if (switchcount == 0 && dir == "asc") {
              dir = "desc";
              switching = true;
            }
          }
        }
      },

      getExamination(){
        axios.get('http://localhost:8080/patient/getAllExaminations').then(response => { 
          this.examinations = response.data;
            for(var i=0;i<this.examinations.length;i++){
              let c=this.examinations[i]
              //  alert(c.type)
                var myDate = new Date( c.date );
                //alert(myDate.toUTCString());
                var mytime=myDate.toUTCString()
                this.examinations[i].date = mytime
            }
          }).catch(error=>{console.log("ne mere" + error)})
      },
      getMedicalFiles(){
        axios.get('http://localhost:8080/patient/getAllFiles').then(response=>{
          //this.medicalFile = zip(response.data.bolesti,response.data.opisiBolesti);

      this.medicalFile = response.data.bolesti.map(function(e, i) {
      return [e, response.data.opisBolesti[i]];
      });

        }).catch(error=>{console.log(error)})
      }
    },
    
    created(){
        this.getClinics()
        this.getExamination()
        this.getMedicalFiles()
    }
}

</script>

<template>
   <div>
     <form>
       <table>
         <tr>
           <td>
             <input type ="text" placeholder="type of examination" id="type">
           </td>
           <td>
             <input type="text" placeholder="location" id="location">
           </td>
          <td>
             <input type ="date" placeholder="time and date" id="date">
           </td>
           <td>
             <button v-on:click="search">Search</button>
           </td>
         </tr>
       </table>
     </form>
    <table class="table" id="tabela">
   <thead>
    <tr>
      <th scope="col">Clinics</th>
      <th scope="col" v-on:click="sortTable(0)">Name</th>
      <th scope="col" v-on:click="sortTable(1)">Address</th>
      <th scope="col">Description</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="c in clinics" :key="c.id">
      <th scope="row">{{c.id}}</th>
      <td>{{c.name}}</td>
      <td>{{c.address}}</td>
      <td>{{c.description}}</td>
    </tr>
  </tbody>
    </table>
    <h3>Examinations:</h3>
    <div>
      <table class="table" id="tabela2">
          <thead>
          <tr>
            <th scope="col" v-on:click="sortTable(0)">Type</th>
            <th scope="col" v-on:click="sortTable(1)">Date</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="e in this.examinations" :key="e.id">
          <td>{{e.type}}</td>
          <td>{{e.date}}</td>
        </tr>
      </tbody>
      </table>
    </div>
    <div>
      <h3>Medical File:</h3>
      <table class="table table-dark">
  <thead>
    <tr>
      <th scope="col">Desease</th>
      <th scope="col">Description</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="d in this.medicalFile" :key="d">
      <td>{{d[0]}}</td>
      <td>{{d[1]}}</td>
    </tr>
    </tbody>
      </table>
    </div>
   </div>
</template>

<style scoped>
#myInput {
  background-image: url('/css/searchicon.png'); /* Add a search icon to input */
  background-position: 10px 12px; /* Position the search icon */
  background-repeat: no-repeat; /* Do not repeat the icon image */
  width: 100%; /* Full-width */
  font-size: 16px; /* Increase font-size */
  padding: 12px 20px 12px 40px; /* Add some padding */
  border: 1px solid #ddd; /* Add a grey border */
  margin-bottom: 12px; /* Add some space below the input */
}

#myTable {
  border-collapse: collapse; /* Collapse borders */
  width: 100%; /* Full-width */
  border: 1px solid #ddd; /* Add a grey border */
  font-size: 18px; /* Increase font-size */
}

#myTable th, #myTable td {
  text-align: left; /* Left-align text */
  padding: 12px; /* Add padding */
}

#myTable tr {
  /* Add a bottom border to all table rows */
  border-bottom: 1px solid #ddd;
}

#myTable tr.header, #myTable tr:hover {
  /* Add a grey background color to the table header and on hover */
  background-color: #f1f1f1;
}
</style>
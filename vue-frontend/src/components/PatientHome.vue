

<script>
import axios from 'axios';
import jwt_decode from 'jwt-decode'

import LocalStorageService from "../LocalStorageService";




export default {
    data(){
        return{
            clinics:[],
            examinations:[],
            medicalFile:[],
            doctorSearch: false,
            doctorSearchResult:[],
            clinicSearchResult:[],
            klinikiniDoktori:[],
            prikaziKlinikineDoktore:false,
            pretiso: false,
            rateAClinic: false,
        }
    },

    methods:{
        getClinics(){
            axios.get('http://localhost:8080/patient/getAllClinics').then(response => { this.clinics = response.data;}).catch(error=>{console.log(error)})
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
      goToDoctorProfile(id){
            console.log(id)
            let url = '/userProfile/'
            url += id
            this.$router.push(url)
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
        axios.get('http://localhost:8080/patient/getMedicalFile').then(response=>{
          //this.medicalFile = zip(response.data.bolesti,response.data.opisiBolesti);
        console.log(response);
      this.medicalFile = response.data.dijagnoze.map(function(e, i) {
      return [e, response.data.opisi[i]];
      });

        }).catch(error=>{console.log(error)})
      },
      searchForDoctors(){
      this.doctorSearch=true
      document.getElementById("doctorsearch").removeAttribute("hidden")
      document.getElementById("clinicsearch").setAttribute("hidden","true")
      this.clinicSearchResult = null
    },
    searchForClinics(){
      document.getElementById("clinicsearch").removeAttribute("hidden")
      document.getElementById("doctorsearch").setAttribute("hidden","true")
      //document.getElementById("doctorresult").setAttribute("hidden","true")
      //document.getElementById("clinics").setAttribute("hidden","true")
      
    },

    goSearchDoctor(){
      this.clinicSearchResult = null
      let name = document.getElementById("doctorname").value 
      let surname = document.getElementById("doctorsurname").value
      let specialization = document.getElementById("doctorspecialization").value
      this.pretiso = false
      //let date = document.getElementById("doctordate").value
      //console.log(name+" "+surname + specialization + date)
      axios
      .post("http://localhost:8080/doctor/searchDoctor",{
        "name":name,
        "surname":surname,
        "specialization":specialization,
      }).then(response=>{
        this.doctorSearchResult=response.data
        console.log(this.doctorSearchResult)
        document.getElementById("tabela").setAttribute("hidden","true")
        // document.getElementById("doctorresult").removeAttribute("hidden")
        // document.getElementById("clinics").setAttribute("hidden","true")
      }).catch(error=>{
        console.log(error)
      })
    },
    goSearchClinics(e){
      e.preventDefault()
      this.doctorSearchResult = null
      let examination = document.getElementById("type").value
      let location = document.getElementById("location").value
      let date = document.getElementById("date").value
      let rate = parseFloat(document.getElementById("rate").value)
      console.log(rate)
      console.log(examination + location+" " + date)
      axios 
      .post("http://localhost:8080/clinic/search",{
        "location" : location,
        "examination":examination,
        "date":date,
        "rate":rate,
      }).then(response=>{
        
          console.log(response.data)
          this.clinicSearchResult = response.data
          document.getElementById("tabela").setAttribute("hidden","true") 
          document.getElementById("examinations").setAttribute("hidden","true")       
          document.getElementById("medfilediv").setAttribute("hidden","true")
          //document.getElementById("doctorresult").setAttribute("hidden","true")
          
          document.getElementById("medfilediv").setAttribute("hidden","true")
          
          
          //document.getElementById("clinics").removeAttribute("hidden")
      }).catch(error=>console.log(error))
    },

    seeAvailableDoctors(id){
      console.log(id)
      let url = 'http://localhost:8080/clinic/'+ id +'/getAvailableDoctors'
      console.log(url)
      axios
      .get(url).then(response=>{
        console.log(response.data)
        this.prikaziKlinikineDoktore = true
        this.doctorSearchResult = response.data
      //  document.getElementById("doctorresult").removeAttribute("hidden")
      }).catch(error=>{
        console.log(error)
      })
      
    },
    showClinicRateForm(){
      this.rateAClinic = true
    },
    cancelClinicRate(){
      this.rateAClinic=false
    },
    leaveClinicRate(event,doctor_id){
      event.preventDefault()

      let rate = parseInt(document.getElementById("ClinicRate").value)
      console.log(rate +"   "+doctor_id)
      axios
      .post("clinic/leaveRate",{
        "value" : rate,
        "id": doctor_id,
      })
      .then(response=>{
        console.log('uspesno ocenjena klinika')
        document.getElementById("ClinicRate").setAttribute("hidden","true")
        document.getElementById("blabla").setAttribute("hidden","true")
        this.rateAClinic = false
      }).catch(error=>{console.log("nije uspesno ocenjena")})
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
      <p></p>
     <button class="btn btn-secondary" @click="searchForDoctors()">Search for doctors</button>
     <button class="btn btn-secondary" @click="searchForClinics()">Search for clinics</button>
      <p></p><p></p>
    <div>
      <form id="doctorsearch" hidden>
        <table>
          <tr>
            <td>Search for doctors:</td>
            <td><input type="text" id="doctorname" placeholder="Name"></td>
            <td><input type="text" id="doctorsurname" placeholder="Surname"></td>
            <td><input type="text" id="doctorspecialization" placeholder="Specialization"></td>
            <td><button @click="goSearchDoctor()">Search</button></td>
          </tr>
        </table>
      </form>
      <p></p>
       <form id="clinicsearch" hidden>
       <table>
         <tr>
           <td>Search for clinics</td>
           <td>
             <input type ="text" placeholder="type of examination" id="type">
           </td>
           <td>
             <input type="text" placeholder="location" id="location">
           </td>
           <td>
             <select name="rate" placeholder="wanted or higher" id="rate">
               <option value="0" selected disabled hidden> 
               </option>
               <option value="5">5</option>
               <option value="6">6</option>
               <option value="7">7</option>
               <option value="8">8</option>
               <option value="9">9</option>
               <option value="10">10</option>
             </select>
           </td>
           <td>
             <input type="date" placeholder="location" id="date">
           </td>
           <td>
             <button @click="goSearchClinics($event)">Search</button>
           </td>
         </tr>
       </table>
     </form>
      <p></p>
       <p></p>
    </div>
    <p v-if="clinics == null || clinics.length == 0"> NO SEARCH RESULT </p>
    <table class="table" id="tabela" v-if="clinics != null && clinics.length > 0">
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
    <p v-if="doctorSearchResult == null || doctorSearchResult.length == 0"> NO SEARCH RESULT </p>
    <table class="table" id="doctorresult" v-if="doctorSearchResult != null && doctorSearchResult.length > 0">
      <thead>
        <tr>
          <th scope="col">Id</th>
          <th scope="col">Name</th>
          <th scope="col">Surname</th>
          <th scope="col">Specialization</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="d in doctorSearchResult" :key="d.id">
          <td>{{d.id}}</td>
          <td>{{d.firstName}}</td>
          <td>{{d.lastName}}</td>
          <td>{{d.specialization}}</td>
          <td><button type="button" class="btn btn-primary" @click="goToDoctorProfile(d.id)">Visit profile</button></td>
        </tr>
      </tbody>
    </table>
    <p></p>
    <table id="clinics" v-if="clinicSearchResult != null && clinicSearchResult.length > 0" class="table table-dark">
      <thead>
        <tr>
          <th scope="col">Name</th>
          <th scope="col">Location</th>
          <th scope="col">Average rate</th>
          <th scope="col">Cost of examination</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(res, key) in clinicSearchResult" :key="key">
          <td>{{res.clinic.name}}</td>
          <td>{{res.clinic.address}}</td>
          <td>{{res.clinic.average}}</td>
          <td>{{res.cost}}</td>
          <td><button clas="btn btn-secondary" @click="seeAvailableDoctors(res.clinic.id)">See available doctors</button></td>
        </tr>
      </tbody>
    </table>
    
    <div id="examinations" v-if="examinations != null && examinations.length > 0">
      <h3>Examinations:</h3>
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
          <td><button id="blabla" type="button" @click="showClinicRateForm" class="btn btn-light">Rate a clinic</button></td>
          <td v-if="rateAClinic ==true">
            <select id="ClinicRate">
              <option value="5">5</option>
              <option value="6">6</option>
              <option value="7">7</option>
              <option value="8">8</option>
              <option value="9">9</option>
              <option value="10">10</option>
            </select>
            <label>---></label>
            <button @click="leaveClinicRate($event,e.doctor_id)" type="button" class="btn btn-info">Post</button>
            <button @click="cancelClinicRate($event)" type="button" class="btn btn-danger">Cancel</button>
          </td>

          <td><button type="button" class="btn btn-light">Rate a doctor</button></td>
        </tr>
      </tbody>
      </table>
    </div>
    <div id="medfilediv" v-if="medicalFile != null && medicalFile.length > 0">
      <h3>Medical File:</h3>
      <table class="table table-dark" id="medfile">
  <thead>
    <tr>
      <th scope="col">Examination description</th>
      <th scope="col">Diagnosis</th>
      <th scope="col">Diagnosis description</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="(d, index) in this.medicalFile" :key="index">
      <td>{{d[1]}}</td>
      <td>{{d[0].name}}</td>
      <td>{{d[0].description}}</td>
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
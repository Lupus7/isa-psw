

<script>
import axios from 'axios';
import jwt_decode from 'jwt-decode'
import {funToastr} from "../toastr.js"
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
            rateADoctor: false,
            convertedDate: '',
          
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
            if(this.convertedDate==""){
              var today = new Date();
              var dd = String(today.getDate()).padStart(2, '0');
              var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
              var yyyy = today.getFullYear();

                today = yyyy + '-' + mm + '-' + dd;
                this.convertedDate = today
            }
            console.log('datum :' + this.convertedDate)
            //this.$router.push({name:'UserProfile', params: {brojic:id, dateSearch:this.convertedDate}});
            let temp = id+'T'+this.convertedDate
            let url = '/userProfile/'
            url += temp
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
      let date = document.getElementById("date1").value
      this.convertedDate = date
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
      this.convertedDate = date
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
    leaveClinicRate(event,doctor_id,examination){
      event.preventDefault()

      let rate = parseInt(document.getElementById("ClinicRate").value)
      console.log(rate +"   "+doctor_id)
      axios
      .post("clinic/leaveRate",{
        "value" : rate,
        "id": doctor_id,
        "examination": examination,
      })
      .then(response=>{
       funToastr("s","Successfuly ratted clinuic!","Rate!");
        document.getElementById("ClinicRate").setAttribute("hidden","true")
        document.getElementById("blabla").setAttribute("hidden","true")
        this.rateAClinic = false
      }).catch(error=>{funToastr("w","Unsuccessfully rated clinic!","Rate!");})
    },
    showDoctorRateForm(){
      this.rateADoctor = true
      
    },
    cancelDoctorRate(){
      this.rateADoctor = false
    },
    leaveDoctorRate(e,doctor_id, examination){
      e.preventDefault()
      let rate = parseInt(document.getElementById("DoctorRate").value)
      console.log(rate +"   "+doctor_id)
      axios
      .post("doctor/leaveRate",{
        "value" : rate,
        "id": doctor_id,
        "examination": examination,
      })
      .then(response=>{
        funToastr("s","Successfuly ratted doctor!","Rate!");
        document.getElementById("DoctorRate").setAttribute("hidden","true")
        document.getElementById("blabla1").setAttribute("hidden","true")
        this.rateADoctor = false
      }).catch(error=>{funToastr("w","Unsuccessfully rated doctor!","Rate!");})
    },
    Decline(){
      document.getElementById("card").setAttribute("hidden","true")
    },
    goToFast(){
      console.log("yeah")
      this.$router.push("/fastExams");
    },
  
    
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
      <table cellspacing="20">
      <tr><td><button class="btn btn-secondary" @click="searchForDoctors()">Search for doctors</button></td>
      <td><button class="btn btn-secondary" @click="searchForClinics()">Search for clinics</button></td>
      <td><label></label></td><td><div id="card" class="card" style="width: 30rem;">
        <div class="card-body">
         <h5 class="card-title">Fast appoint examination</h5>
            <p class="card-text">Save your time and efford of searching, just pick some of examinatin we provide to you</p>
           <button  id="smrdljivodugme" class="btn btn-primary" @click="goToFast">Visit</button>
            <a href="#" class="card-link" @click="Decline">Decline</a>
        </div></div></td></tr></table>
    <div>
      <br><br>
      <form id="doctorsearch" hidden>
        <table cellspacing="10">
          <tr>
            <td>Search for doctors:</td>
            <td><input type="text" id="doctorname" placeholder="Name"></td>
            <td><input type="text" id="doctorsurname" placeholder="Surname"></td>
            <td><input type="text" id="doctorspecialization" placeholder="Specialization"></td>
            <td>
             <input type="date" placeholder="location" id="date1">
           </td>
            <td><button @click="goSearchDoctor()">Search</button></td>
          </tr>
        </table>
      </form>
      <p></p>
       <form id="clinicsearch" hidden>
       <table cellspacing="10">
         <tr>
           <td>Search for clinics</td>
           <td>
             <input type ="text" placeholder="type of examination" id="type">
           </td>
           <td>
             <input type="text" placeholder="location" id="location">
           </td>
         </tr>
         <tr>
           <td>
             Rate    
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
          <th scope="col">Rating</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="d in doctorSearchResult" :key="d.id">
          <td>{{d.id}}</td>
          <td>{{d.firstName}}</td>
          <td>{{d.lastName}}</td>
          <td>{{d.specialization}}</td>
          <td>{{d.average}}</td>
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
          <td v-if="e.ratedClinic == false">
            <button id="blabla" type="button"  @click="showClinicRateForm(e.id)" class="btn btn-light">Rate a clinic</button></td>
          <td v-if="e.ratedClinic==false && rateAClinic==true">
            <select id="ClinicRate" v-if="e.ratedClinic == false">
              <option value="5">5</option>
              <option value="6">6</option>
              <option value="7">7</option>
              <option value="8">8</option>
              <option value="9">9</option>
              <option value="10">10</option>
            </select>
          </td>
          <td v-if="e.ratedClinic==false && rateAClinic==true">
            <button @click="leaveClinicRate($event,e.doctor_id, e.examinationID)" type="button" class="btn btn-info">Post</button>
            <button @click="cancelClinicRate($event)" type="button" class="btn btn-danger">Cancel</button>
          </td>

          <td><button type="button" id="blabla1" v-if="e.ratedDoctor==false" @click="showDoctorRateForm" class="btn btn-light">Rate a doctor</button></td>
          <td v-if="e.ratedDoctor==false && rateADoctor==true">
            <select id="DoctorRate">
              <option value="5">5</option>
              <option value="6">6</option>
              <option value="7">7</option>
              <option value="8">8</option>
              <option value="9">9</option>
              <option value="10">10</option>
            </select>
          </td>
          <td v-if="e.ratedDoctor==false && rateADoctor==true">
            <button @click="leaveDoctorRate($event,e.doctor_id, e.examinationID)" type="button" class="btn btn-info">Post</button>
            <button @click="cancelDoctorRate($event)" type="button" class="btn btn-danger">Cancel</button>
          </td>
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
      <th scope="col">Date</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="(d, index) in this.medicalFile" :key="index">
      <td>{{d[1]}}</td>
      <td>{{d[0].name}}</td>
      <td>{{d[0].description}}</td>
      <td>{{d[0].date}}</td>
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
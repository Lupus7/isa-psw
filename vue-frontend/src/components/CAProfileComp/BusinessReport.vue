<template>
    <div class="wrapper">
        <div class="row">
            <div class="col-3"/> 
            <div class="col-6">
                <table class="table table-striped table-hoover">
                    <thead >
                        
                        <tr>
                            <th colspan="6"> <center> <h2>Business Report</h2> </center> </th>                  
                        </tr>

                        <tr>
                            <th colspan="6"><h4 style="color:#ff6666" >Clinic</h4> </th>                  
                        </tr>

                        <tr class="table-secondary">
                            <th  colspan="3" align="justify"  style="font-size:16px;background:#DCDCDC">Clinic Name: {{clinic.clinicName}}</th>
                            <th colspan="3" align="justify"  style="font-size:16px; " >Clinic Rate: {{clinic.clinicRate}} </th>         
                        </tr>

                        <tr>
                            <th colspan="6"><h4 style="color:#ff6666" >Clinic Doctors</h4> </th>                  
                        </tr>

                        <tr class="table-secondary">
                            <th colspan="3"  align="justify"  style="font-size:16px; background:#DCDCDC">Doctor Name</th>
                            <th colspan="3" align="justify"  style="font-size:16px;">Doctor Rate</th>         
                        </tr>
                    </thead>
                    <tbody>

                        <tr style="font-size:14px; background:	#F8F8F8" v-for="(doc, index) in this.clinic.doctors" :key="index">
                    
                        <td colspan="3" >{{doc.name}}</td>
                        <td colspan="3">{{doc.rate}}</td>


                    </tr>

                    </tbody>


                </table>
            </div>
            <div class="col-3"/> 

           
            <br>
           
            

    </div>
    
        <div class="row">
            <div class="col-1"/>
            <div class="col-10">
                <GChart
                        type="ColumnChart"
                        :data="chartDataDay"
                        :options="chartOptions1"
                />
            </div>
             <div class="col-1"/>
        </div>

       <br>

        <div class="row">
                <div class="col-2"/> 
                <div class="form-group col-md-2">
                    <label >From Date:</label>
                        <div class="form-group">
                            <input class="form-control" type="date" v-model="dayF1" />
                        </div>
                </div>
                <div class="form-group col-md-2">
                    <label>To Date:</label>
                    <div class="form-group">
                        <input class="form-control" type="date" v-model="dayF2" />
                    </div>
                </div>
                 
        </div>

        <div class="row">
            <div class="col-2"/> 
            <div class="col-4">
                <div  class="modal-footer">
                    <button @click="getDailyReport()" class="btn btn-primary"> Daily Report </button>
                </div>
            </div> 
        </div>  
     

        <br>
        <div class="row">
            <div class="col-1"/>
            <div class="col-10">
                <GChart
                        type="ColumnChart"
                        :data="chartDataWeek"
                        :options="chartOptions2"
                />
            </div>
             <div class="col-1"/>
        </div>

         
        <div class="row">
                <div class="col-2"/> 
                <div class="form-group col-md-2">
                    <label >Select Month:</label>
                        <div class="form-group">
                            <input class="form-control" type="month" v-model="weekF" />

                        </div>
                </div>
                
                 
        </div>

        <div class="row">
            <div class="col-2"/> 
            <div class="col-4">
                <div  class="modal-footer">
                    <button @click="getWeeklyReport()" class="btn btn-primary"> Weekly Report </button>
                </div>
            </div> 
        </div>  

        <br>
        <div class="row">
            <div class="col-1"/>
            <div class="col-10">
                <GChart
                        type="ColumnChart"
                        :data="chartDataMonth"
                        :options="chartOptions3"
                />
            </div>
             <div class="col-1"/>
        </div>

         
        <div class="row">
                <div class="col-2"/> 
                <div class="form-group col-md-2">
                    <label >From Month:</label>
                        <div class="form-group">
                            <input class="form-control" type="month" v-model="monthF1" />

                        </div>
                </div>
                <div class="form-group col-md-2">
                    <label>To Month:</label>
                    <div class="form-group">
                        <input class="form-control" type="month" v-model="monthF2" />

                    </div>
                </div>
                 
        </div>

        <div class="row">
            <div class="col-2"/> 
            <div class="col-4">
                <div  class="modal-footer">
                    <button @click="getMontlyReport()" class="btn btn-primary"> Monthly Report </button>
                </div>
            </div> 
        </div>  

        <br>

        <div class="row">
            <div class="col-1"/>
            <div class="col-10">
                <GChart
                        type="ColumnChart"
                        :data="chartDataMoney"
                        :options="chartOptionsMoney"
                />
            </div>
             <div class="col-1"/>
        </div>

       <br>

        <div class="row">
                <div class="col-2"/> 
                <div class="form-group col-md-2">
                    <label >From Date:</label>
                        <div class="form-group">
                            <input class="form-control" type="date" v-model="dayMF1" />
                        </div>
                </div>
                <div class="form-group col-md-2">
                    <label>To Date:</label>
                    <div class="form-group">
                        <input class="form-control" type="date" v-model="dayMF2" />
                    </div>
                </div>
                 
        </div>

        <div class="row">
            <div class="col-2"/> 
            <div class="col-4">
                <div  class="modal-footer">
                    <button @click="getIncomeReport()" class="btn btn-primary"> Clinic Income </button>
                </div>
            </div> 
        </div>  
     

        <br>
        


    </div>

    
</template>

<script>
import axios from 'axios'
import { GChart } from 'vue-google-charts'

export default {

    data(){
        return{
            clinic:[],
            day:'',
            month:'',
            chartDataDay: [
                ['Day', 'Procedure'],
                ['0',0],
                
            ],
            chartDataWeek: [
                ['Week', 'Procedure'],
                ['0',0],

            ],

            chartDataMonth: [
                ['Month', 'Procedure'],
                ['0',0],
              
            ],

             chartDataMoney: [
                ['Income[$]', 'Income[$]'],
                ['0', 0],
            ],


            chartOptions1: {
               
                title: 'Performed Procedures[Daily]',
                width: "auto",
                height: 550,
                
            },
            chartOptions2: {
              
                title: 'Performed Procedures[Weekly]',
                width: "auto",
                height: 550,
                
            },
            chartOptions3: {
              
                title: 'Performed Procedures[Monthly]',
                width: "auto",
                height: 550,
                
            },
             chartOptionsMoney: {
              
                title: 'Clinic Income[$]',
                width: "auto",
                height: 550,
                
            },
            dayF1:'',
            dayF2:'',
            weekF:'',
            monthF1:'',
            monthF2:'',
            dayMF1:'',
            dayMF2:'',
        }
    },
    components: {
        GChart
    },
    methods:{
        getReport(){
            axios.get('http://localhost:8080/ca/rates').then(response => { this.clinic = response.data; })
        },
        getDailyReport(){
            axios.post('http://localhost:8080/ca/day', {"day1":this.dayF1, "day2":this.dayF2 } ).then(response => { 
                
                this.chartDataDay = [['Day', 'Procedure']]
                for(let dayRep of response.data){
                    let dat = new Date(dayRep.date);  
                    let s = dat.toLocaleString().split(",");
                    let s1 = s[0].split("/");
                    let day = s1[1]
                    let month = s1[0]
                    let year = s1[2]
                    if(day.length === 1)
                        day = '0'+day.toString()
                    if(month.length === 1)
                        month = '0'+month.toString()
                    let dateR = day+"."+month+"."+year
                    this.chartDataDay.push([dateR,dayRep.procedures])
                }
            })
        },
        getWeeklyReport(){
            axios.post('http://localhost:8080/ca/week', { "week":this.weekF }).then(response => {
                    this.chartDataWeek = [['Week', 'Procedure']];
                    for(let wek of response.data){
                        this.chartDataWeek.push(["Week["+wek.week+"]",wek.procedures])
                    }
                 
                })
        },
        getMontlyReport(){
            axios.post('http://localhost:8080/ca/month', { "month1":this.monthF1, "month2":this.monthF2}).then(response => { 
                this.chartDataMonth =  [['Month', 'Procedure']];
                for(let m of response.data){
                    let s1 = m.month.split("-");
                    this.chartDataMonth.push([s1[1]+"/"+s1[0],m.procedures])
                }
            })
        },
        getIncomeReport(){
            axios.post('http://localhost:8080/ca/income', { "day1":this.dayMF1, "day2":this.dayMF2  }).then(response => { 

                this.chartDataMoney = [['Income[$]', 'Income[$]']]
                for(let dayRep of response.data){
                    let dat = new Date(dayRep.date);  
                    let s = dat.toLocaleString().split(",");
                    let s1 = s[0].split("/");
                    let day = s1[1]
                    let month = s1[0]
                    let year = s1[2]
                    if(day.length === 1)
                        day = '0'+day.toString()
                    if(month.length === 1)
                        month = '0'+month.toString()
                    let dateR = day+"."+month+"."+year
                    this.chartDataMoney.push([dateR,dayRep.procedures])
                }
             })
        },
      
    },
    created(){
        this.getReport();

    }
}
</script>

<style scoped>

</style>
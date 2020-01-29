<template>
    <div>
        <div class="row">
            <div class="col-8">
                <Calendar mode="room"/>
            </div>
            <div class="col">

                
                <div class="container"  >
                    <div class="modal-header">
                        <h3> Arrange Examination </h3>
                    </div>
                        
                <div class="panel-body">
                    <br>
                    <form accept-charset="UTF-8" role="form">
                    <fieldset>
                         <div class="form-row">
                            <div class="form-group col-md-6">
                                <label >Room Name:</label>
                                <textarea class="form-control" v-text="this.room.name" disabled rows="1" style="resize:none"/>
                            </div>
                                <div class="form-group col-md-6">
                                <label>Examination Type:</label>
                                <textarea class="form-control" v-text="this.examReqs.examinationtype.name" disabled rows="1" style="resize:none"/>
                            </div>
                        </div>

                         <div class="form-row">
                            <div class="form-group col-md-6">
                                <label >Doctor:</label>
                                <textarea class="form-control" v-text="this.examReqs.doctor.firstName +' '+this.examReqs.doctor.lastName" disabled rows="1" style="resize:none"/>
                            </div>
                                <div class="form-group col-md-6">
                                <label>Date of Examination:</label>
                                 <textarea v-if="this.dateSearch != ''  "  class="form-control" v-text="this.dateSearch" disabled rows="1" style="resize:none"/>
                                 <textarea v-else class="form-control" v-text="this.date" disabled rows="1" style="resize:none"/>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-12">
                                <label >Free Appointments:</label>
                                 <div class="form-group">
                                    <select class="form-control">
                                        <option v-for="(ap, index) in this.freeAp" :key="index"> {{ap}} </option>
                                    </select>
                                </div>
                            </div>                            
                        </div>
                        
                        <center> <input class="btn btn-lg btn-success btn-block" type="submit" value="Arrange"> </center>
                    </fieldset>
                    </form>
                    <hr/>
                    
                </div>

      
	</div>

            </div>  

        </div>

    </div>
</template>

<script>
import Calendar from '../Calendar'
export default {
    data() {
        return {
            date:"",
            freeAp:[
                "06:00-07:00","07:00-08:00","08:00-09:00","09:00-10:00","10:00-11:00","11:00-12:00",
                "12:00-13:00","13:00-14:00","14:00-15:00","15:00-16:00","16:00-17:00","17:00-18:00",
                "18:00-19:00","19:00-20:00","20:00-21:00","21:00-22:00"
            ],
            takenAp:[]
        }
        
    },
    props:["room","dateSearch","examReqs"],
    components:{
        Calendar
    },
    methods:{
        getFreeDates(){
            for(let td of this.room.takenDates){
                let dat = new Date(td.date);      
                let s = dat.toLocaleString().split(",");
                let s1 = s[0].split("/");
                let dateTD = s1[1]+"."+s1[0]+"."+s1[2]
                if(this.dateSearch != ''){
                    if(this.dateSearch == this.date)
                        this.takenAp.push(td.time);
                }
                else if(dateTD == this.date){
                    this.takenAp.push(td.time);
                }
            }

            for(let t of this.takenAp){
               console.log(t)
               for( var i = 0; i < this.freeAp.length; i++){ 
                    if ( t === this.freeAp[i]) {
                        this.freeAp.splice(i, 1); 
                        i--;
                    }
                }
            }
              
        },
    },
    created(){
        // ako je dateSearch null => uzima se datum iz examReq
        let dat = new Date(this.examReqs.date);      
        let s = dat.toLocaleString().split(",");
        let s1 = s[0].split("/");
        this.date = s1[1]+"."+s1[0]+"."+s1[2]

        this.getFreeDates();
    }
}
</script>

<style scoped>

</style>
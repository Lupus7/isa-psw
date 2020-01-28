<template>
    <div>
        <Calendar  doctor = this.doc.email  />
    </div>
</template>

<script>
import Calendar from './Calendar'
import axios from 'axios'
export default {
    data() {
        return {
           doc:[],
        }
        
    },
   
    components:{
        Calendar
    },
    methods:{

        getDoctor(){
            axios.get('http://localhost:8080/doctor/getInfo').then(response => { 
                this.doc = response.data;
             });
        }

    },
    created(){
        this.getDoctor();
    }
}
</script>

<style scoped>

</style>
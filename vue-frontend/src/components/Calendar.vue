<!-- 
    if you want the patient view, add Calendar like this:
        <Calendar doctorId=MAIL date="YYYY-MM-DD">
    example:
        <Calendar doctor="doctor1@email.com" date="2020-01-24"> 
            ===> this will get calendar for doctor with email doctor1@email.com for date [year:2020, month:january, day:24]

    if you want the admin room view, add Calendar like this:
        <Calendar mode="room">
            ===> this will show calendar room view
-->

<template>
    <div style="margin-top: 10px;" v-if="valid == 2">
        <FullCalendar
            v-bind:defaultView="calendarView"
            :schedulerLicenseKey="'GPL-My-Project-Is-Open-Source'"
            :plugins="calendarPlugins"
            :allDaySlot="false"
            :events="calendarEvents"
            :minTime="showTimes[0]"
            :maxTime="showTimes[1]"
            :showNonCurrentDates="false"
            :businessHours="{
                daysOfWeek: [ 1, 2, 3, 4 , 5 ],
                startTime: shift[0],
                endTime: shift[1]
            }"
            :slotLabelFormat="{ 
                hour: 'numeric',
                minute: '2-digit',
                omitZeroMinute: false,
                hour12: false 
            }"
            :buttonText="{
                today: 'Current',
                month: 'Month',
                week: 'Week',
                day: 'Day',
                list: 'Weekly Schedule'
            }"
            :header="calendarHeader"
            :height="'auto'"
            :defaultDate="date"
            @eventClick="handleDateClick"
        />
    </div>
    <div v-else-if="valid == 1">
        Plese wait...
    </div>
    <div v-else> 
        Ooops... Something went wrong!
    </div>
</template>

<script>
import axios from 'axios'
import jwt_decode from 'jwt-decode'
import LocalStorageService from "../LocalStorageService";
import {funToastr} from "../toastr.js"
import FullCalendar from '@fullcalendar/vue'
import timeGridPlugin from '@fullcalendar/timegrid'
import dayGridPlugin from '@fullcalendar/daygrid'
import listPlugin from '@fullcalendar/list';

export default {
    components: {
        FullCalendar 
    },
    props: {
        doctor: String,
        date: String,
        mode: String,
        shiftNumber: String
    },
    data() {
        return {
            valid: 1,
            calendarView: 'timeGridView',
            calendarPlugins: [timeGridPlugin, dayGridPlugin, listPlugin],
            calendarHeader: {
                center: 'title'
            },
            calendarEvents: {},
            showTimes: ['05:00', '15:00'],
            shift: ['06:00', '14:00'],

            role: ""
        }
    },
    methods: {
        getRole() {
            const lss = LocalStorageService.getService()

            if(lss.getAccessToken() != undefined && lss.getAccessToken() != null){          
                const token = jwt_decode(lss.getAccessToken())         
                this.role = token.roles
            }

            return this.role
        },
        handleDateClick(e) {
            if (e.event.title === "Request examination") {
                // REQUEST HANDLER
                console.log(e.event.start)
                console.log(this.doctor)
                axios
                .post("/patient/requestExam",{
                    "date":e.event.start,
                    "doctor":this.doctor,
                })
                .then(response=>{
                    funToastr("s","Successfuly appointed Examination!");
                    this.$router.push("/")
                })
                .catch(error=>{
                    console.log(error)
                })
            }
        }
    },
    view: {
        timeGridWeek: {
            buttonText: {
                today: 'Current'
            }
        }
    },
    created() {
        const role = this.getRole()

        if (role === "ROLE_PATIENT") {
            if (!this.doctor || !this.date)
                this.valid = 0
            else {
                axios
                    .get('/calendar/doc-info/' + this.doctor + "/" + this.date)
                    .then(response => {
                        this.calendarEvents = response.data

                        this.calendarView = 'timeGridDay'
                        this.calendarHeader.left = ''
                        this.calendarHeader.right= ''

                        const shift = this.calendarEvents[0].shift

                        if (shift === 1) {
                            this.showTimes = ['05:00', '15:00']
                            this.shift = ['06:00', '14:00']
                        }
                        else {
                            this.showTimes = ['13:00', '23:00']
                            this.shift = ['14:00', '22:00']
                        }
                        
                        const base = shift === 1 ? 6 : 14;

                        for (let i = 0; i < 8; i++) {
                            let start = (i + base) + ':00'

                            if (start.length === 4)
                                start = '0' + start

                            start = this.date + 'T' + start;

                            let exists = false

                            for (let j = 0; j < this.calendarEvents.length; j++) {
                                if (start == this.calendarEvents[j].start) {
                                    exists = true
                                    break;
                                }
                            }

                            if (!exists) {
                                let end = (i + base + 1) + ':00'

                                if (end.length === 4)
                                    end = '0' + end

                                end = this.date + 'T' + end;

                                this.calendarEvents.push({
                                    "start": start,
                                    "end": end,
                                    "title": "Request examination",
                                    "backgroundColor": "#ff0"
                                })
                            }
                        }

                        this.valid = 2
                    })
            }  
        }
        else if (role === "ROLE_CADMIN") {
            if (this.mode === "room") {
                axios
                    .get('/calendar/room-info')
                    .then(response => {
                        this.calendarEvents = response.data

                        if (!this.calendarEvents || this.calendarEvents.length == 0) {
                            this.valid = 0
                            return;
                        }

                        this.calendarView = 'timeGridWeek'
                        this.calendarHeader.left = 'dayGridMonth,timeGridWeek,timeGridDay'
                        this.calendarHeader.right= 'prev,today,next'
                        this.showTimes = ['05:00', '23:00']
                        this.shift = ['06:00', '22:00']
                        this.valid = 2
                    })
            }
            else {
                this.valid = 0
            }
        }
        else if (role === "ROLE_DOCTOR" || role === "ROLE_NURSE") {
            axios
                .get('/calendar/schedule')
                .then(response => {
                        this.calendarEvents = response.data

                        this.calendarView = 'timeGridWeek'
                        this.calendarHeader.left = 'dayGridMonth,timeGridWeek,timeGridDay, listWeek'
                        this.calendarHeader.right= 'prev,today,next'

                        if (this.calendarEvents[0].shift === 1) {
                            this.showTimes = ['05:00', '15:00']
                            this.shift = ['06:00', '14:00']
                        }
                        else {
                            this.showTimes = ['13:00', '23:00']
                            this.shift = ['14:00', '22:00']
                        }
                        this.valid = 2
                })
        }
        else
            this.valid = 0
    }
}
</script>

<style lang='scss'>
    @import '~@fullcalendar/core/main.css';
    @import '~@fullcalendar/timegrid/main.css';
    @import '~@fullcalendar/daygrid/main.css';
    @import '~@fullcalendar/list/main.css';
</style>
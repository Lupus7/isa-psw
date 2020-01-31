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
            calendarPlugins: [ timeGridPlugin, dayGridPlugin, listPlugin ],
            calendarHeader: {
                center: 'title'
            },
            calendarEvents: [],
            showTimes: ['05:00', '15:00'],
            shift: ['06:00', '14:00'],

            role: ""
        }
    },
    methods: {
        getRole(){
            const lss = LocalStorageService.getService()

            if(lss.getAccessToken() != undefined && lss.getAccessToken() != null){          
                const token = jwt_decode(lss.getAccessToken())         
                this.role = token.roles
            }

            return this.role
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

                        if (!this.calendarEvents || this.calendarEvents.length == 0) {
                            //this.valid = 0
                            return;
                        }

                        this.calendarView = 'timeGridDay'
                        this.calendarHeader.left = ''
                        this.calendarHeader.right= ''

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
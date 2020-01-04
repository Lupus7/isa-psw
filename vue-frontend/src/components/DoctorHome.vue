<template>
    <div class="container" style="margin: auto">
        <div class="card-deck" style="margin-top: 30px">
            <div v-for="(card, index) in cards" :key="index">
            <a v-bind:href=card[3] class="custom-card">
                <div class="card mb-4" style="max-width: 600px; min-width: 600px">
                    <div class="row no-gutters">
                        <div class="col-md-4">
                        <img v-bind:src=card[0] class="card-img" alt="...">
                        </div>
                        <div class="col-md-8">
                            <div class="card-body">
                                <h5 class="card-title">{{card[1]}}</h5>
                                <p class="card-text">{{card[2]}}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </a>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            cards: []
        }
    },
    created() {
        this.cards.push(["../assets/list.png", "Patient List", "Get the list of all the patients.", "#/search"]);
        this.cards.push(["http://ssl.gstatic.com/accounts/ui/avatar_2x.png", "Profile", "Access your profile to view and/or change your personal information.", "#/userProfile"]);
        this.cards.push(["../assets/calendar.png", "Work Calendar", "View all of the appointed examinations and operations. Get data about patients in the appointments", "/"]);
        this.cards.push(["../assets/vacation.png", "Time off", "Make a request for taking a vacation or time off.", "#/holiday"]);
    }
}
</script>

<style scoped>
    a.custom-card,
    a.custom-card:hover {
        color: inherit;
    }

    img {
        width: 200px;
        height: 200px;
    }

    @media only screen and (min-width: 610px) {
        div.container {
            max-width: 610px
        }
    }

    @media only screen and (min-width: 1260px) {
        div.container {
            max-width: 1260px
        }
    }
</style>
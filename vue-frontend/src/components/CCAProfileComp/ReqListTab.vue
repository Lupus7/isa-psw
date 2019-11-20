<template>
    <transition name="component-fade" mode="out-in">
        <component v-bind:is="view"></component>
    </transition>
</template>

<script>
import RequestList from './RequestList'
import ExplanationForm from './ExplanationForm'

export default {
    data() {
        return {
            email: "",
            explanation: "",
            view: RequestList
        }
    },
    components: {
        RequestList,
        ExplanationForm
    }
    // TODO : add transition
}
</script>

<style scoped>
    .component-fade-enter-active, .component-fade-leave-active {
        transition: opacity .3s ease;
    }
    .component-fade-enter, .component-fade-leave-to {
        opacity: 0;
    }
</style>
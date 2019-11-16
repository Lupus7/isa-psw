import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

const router = new Router({
    mode: "history",
    routes: [{
            path: "/",
            name: "Home",
            component: () =>
                import ("./components/RequestList")
        },
        {
            path: "/profile",
            name: "Profile",
            component: () =>
                import ("./components/RequestList")
        },
        {
            path: "/login",
            name: "Login",
            component: () =>
                import ("./components/Login")
        },
        {
            path: "/register",
            name: "Register",
            component: () =>
                import ("./components/Register")
        }
    ]
});

export default router;
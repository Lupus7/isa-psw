import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

const router = new Router({
    mode: "history",
    routes: [{
            path: "/",
            name: "Home",
            component: () =>
                import ("./components/CCAProfileComp/ReqListTab")
        },
        {
            path: "/profile",
            name: "Profile",
            component: () =>
                import ("./components/CCAProfileComp/ReqListTab")
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
        },
        {
            path: "/patientProfile",
            name: "PatientProfile",
            component: () =>
                import ("./components/PatientProfile")
        },
        {
            path: "/doctorProfile",
            name: "DoctorProfile",

            component:()=>
                import("./components/DoctorProfile")
        },
        {
            path:"/changeData",
            name: "ChangeData",
            component:()=>
                import("./components/ChangeData")

  
        }
    ]
});

export default router;
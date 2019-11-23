import Vue from 'vue'
import VueToastr2 from 'vue-toastr-2'
import 'vue-toastr-2/dist/vue-toastr-2.min.css'
 
window.toastr = require('toastr')
 
Vue.use(VueToastr2)
 

const options = window.toastr.options = {
    "closeButton": false,
    "debug": false,
    "newestOnTop": false,
    "progressBar": false,
    "positionClass": "toast-top-right",
    "preventDuplicates": false,
    "onclick": null,
    "showDuration": "3000",
    "hideDuration": "1000",
    "timeOut": "5000",
    "extendedTimeOut": "1000",
    "showEasing": "swing",
    "hideEasing": "linear",
    "showMethod": "fadeIn",
    "hideMethod": "fadeOut"
}

export function funToastr(type,message,title){
    if (type == "s")
        window.toastr.success(message, title, options);
    else if(type == "w")
        window.toastr.warning(message, title, options);
    else if(type == "i")
        window.toastr.info(message, title, options);
    else if(type == "d")
        window.toastr.danger(message, title, options);
      
}

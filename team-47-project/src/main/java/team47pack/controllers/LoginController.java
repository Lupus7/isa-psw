package team47pack.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import team47pack.models.User;
import team47pack.models.dto.LoginRequest;
import team47pack.service.LoginService;

@Controller
public class LoginController {

    @Autowired
    LoginService loginservice;

    @PostMapping(value="/login", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> login(@RequestBody LoginRequest request){
        //ret = );
        User u = loginservice.login(request.getEmail(),request.getPassword());

        String response = "";

        if (u == null)
            response = "null";
        else
            response = "{\"email\":\"" + u.getEmail() + "\"}";

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}

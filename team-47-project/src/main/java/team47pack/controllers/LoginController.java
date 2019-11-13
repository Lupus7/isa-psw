package team47pack.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.catalina.connector.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
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
        ObjectMapper obj = new ObjectMapper();
      //  User u = loginservice.login(request.getEmail(),request.getPassword();
        JSONStringer json = new JSONStringer();
        JSONObject jobj = new JSONObject();
        try {
             jobj = new JSONObject(obj.writeValueAsString(loginservice.login(request.getEmail(),request.getPassword())));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(jobj.toString(),HttpStatus.OK);
    }

}

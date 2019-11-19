package team47pack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import team47pack.models.Patient;
import team47pack.models.User;
import team47pack.models.UserTokenState;
import team47pack.models.dto.RegisterRequest;
import team47pack.security.TokenUtils;
import team47pack.security.auth.JwtAuthenticationRequest;
import team47pack.service.UserService;
import team47pack.service.impl.CustomUserDetailsService;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value="/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthentificationController {
    @Autowired
    TokenUtils tokenUtils;
    @Autowired
    private AuthenticationManager autManager;
    @Autowired
    private CustomUserDetailsService userDetailService;
    @Autowired
    private CustomUserDetailsService userService;

    @Autowired
    private UserService usService;

    @RequestMapping(value="/login",method = POST)
    public ResponseEntity<?>createAuthToken(@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response)throws AuthenticationException, IOException
    {
        final Authentication authentication = autManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));
        System.out.println(authenticationRequest.getUsername()+authenticationRequest.getPassword());
        //Ubaci username + pass u kontext
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //kreiraj token
        User user=(User)authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user.getUsername());
        int expiresIn = tokenUtils.getExpiredIn();

        return ResponseEntity.ok(new UserTokenState(jwt,expiresIn));
    }

    @RequestMapping(method = POST,value = "/signup")
    public ResponseEntity<?>addUser(@RequestBody RegisterRequest regRequest, UriComponentsBuilder ucBuilder){
        User existUser = this.usService.getUserByEmail(regRequest.getEmail());
        if (existUser!=null){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        User user = this.userDetailService.save(new Patient(regRequest));

        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }
    @GetMapping("/test")
    public String test(){
        return "TESTTT;";
    }
}

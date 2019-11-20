package team47pack.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.io.IOException;
import team47pack.models.Authority;
import team47pack.models.User;
import team47pack.models.UserTokenState;
import team47pack.models.dto.RegisterRequest;
import team47pack.security.TokenUtils;
import team47pack.security.auth.JwtAuthenticationRequest;
import team47pack.service.LoginService;
import team47pack.service.impl.CustomUserDetailsService;

@RestController
public class LoginController {

	@Autowired
	LoginService loginservice;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	TokenUtils tokenUtils;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@PostMapping(value = "/register", produces = "application/json", consumes = "application/json")
	public ResponseEntity<String> register(@RequestBody RegisterRequest req) {
		boolean b = loginservice.register(req);
		if (b == true) {
			return ResponseEntity.ok("Successful");
		}
		return ResponseEntity.status(400).body("Invalid information");
	}

	@PostMapping(value = "/login", produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
			HttpServletResponse response) throws AuthenticationException, IOException {

		System.out.println("--------------" + authenticationRequest.getUsername());
		System.out.println("--------------" + authenticationRequest.getPassword());
		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
						authenticationRequest.getPassword()));

		// Ubaci username + password u kontext
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Kreiraj token
		User user = (User) authentication.getPrincipal();
		System.out.println(((Authority)user.getAuthorities().toArray()[0]).getName());
		String jwt = tokenUtils.generateToken(user.getUsername(),((Authority)user.getAuthorities().toArray()[0]).getName());
		int expiresIn = tokenUtils.getExpiredIn();

		// Vrati token kao odgovor na uspesno autentifikaciju
		return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
	}

	@RequestMapping(value = "/refresh", method = RequestMethod.POST)
	public ResponseEntity<?> refreshAuthenticationToken(HttpServletRequest request) {

		System.out.println("--------otvorio-------: "+ tokenUtils.getAuthHeaderFromHeader(request));

		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
		User user = (User) this.userDetailsService.loadUserByUsername(username);

		System.out.println("--------token-------:  "+token);
		System.out.println("--------username-------: "+ username);

		
		if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
			String refreshedToken = tokenUtils.refreshToken(token);
			int expiresIn = tokenUtils.getExpiredIn();

			System.out.println("---------token: " + refreshedToken);
			System.out.println("---------exp: " + expiresIn);

			return ResponseEntity.ok(new UserTokenState(refreshedToken, expiresIn));
		} else {
			UserTokenState userTokenState = new UserTokenState();
			return ResponseEntity.badRequest().body(userTokenState);
		}
	}

	@GetMapping(value = "/logout")
	public ResponseEntity<?> logout() {
		UserTokenState userTokenState = new UserTokenState();
		return ResponseEntity.ok(userTokenState);
	}

	@GetMapping(value = "/test")
	public String test() {
		return "Uspesnoo";
	}

}

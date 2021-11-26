package com.kutlu.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kutlu.hotel.model.AdminUser;
import com.kutlu.hotel.util.TokenManager;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/")
public class AuthController {
	
	@Autowired
	private TokenManager tokenManager;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@PutMapping("login")
	public ResponseEntity<String> login( @RequestBody AdminUser user) {
		System.out.println("testac");		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getMail(), user.getPassword()));
			System.out.println("hata yeri");
			
			//tokenmanager neden çalışmıyor ona bak daha sonra frontta gelen sonuca göre yönlendirme yap ve loginFilter ekle
			return  ResponseEntity.ok(tokenManager.generateToken(user)) ;
		}catch(Exception e) {
			throw e;
		}
		
	}

}
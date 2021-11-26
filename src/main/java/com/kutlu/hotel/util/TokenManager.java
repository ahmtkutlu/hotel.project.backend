package com.kutlu.hotel.util;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.kutlu.hotel.model.AdminUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenManager {


	private static final int validity = 30 * 60 * 1000;// 30dk

	public String generateToken(AdminUser user) {
	
		Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		return Jwts.builder().setSubject(user.getMail())
				.setIssuer("admin.token.manager")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + validity)).signWith(key).compact();
	}

	public boolean tokenValidate(String token) {
		if (getUserFromToken(token) != null && isExpired(token)) {
			return true;
		}
		return false;
	}

	public String getUserFromToken(String token) {
		//Daha sonra burada da expire kontrolü yap
		return getClaims(token).getSubject();
	}
	
	public boolean isExpired(String token) {
		return getClaims(token).getExpiration().after(new Date(System.currentTimeMillis()));
	}
	
	private Claims getClaims(String token) {
		Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();	
	}
}
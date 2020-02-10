package com.bridgelabz.userloginregistration.services;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtToken {
	SignatureAlgorithm alorithm=SignatureAlgorithm.HS256;
	static String secretKey="iamsecretkey";
	

public  String generateToken(String email) {
	JwtBuilder builderJwts=Jwts.builder().setId(email).setIssuedAt(new Date(System.currentTimeMillis())).signWith(alorithm,secretKey );
	
	return builderJwts.compact();
}

public String getToken(String token)
{
Claims claim=null;
try {
	 claim=Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	 System.out.println("encodedtoken:--"+ claim.getSubject());
    }catch(Exception e)
   {
		System.out.println("invalide token");
	}
			return claim.getSubject();
}
}

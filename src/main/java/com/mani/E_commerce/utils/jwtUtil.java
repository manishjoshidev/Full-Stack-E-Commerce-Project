package com.mani.E_commerce.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class jwtUtil {
    public static final String SECRET="67d6d7s6s76s7s6699";

  public String generateToken(String userName){
    Map<String,Object> claims=new HashMap<>();
    return createToken(claims,userName);
  }
  private String createToken(Map<String,Object>claims,String userName){
return Jwts.builder()
        .setClaims(claims)
        .setSubject(userName)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis()+10000*60+30))
        .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();

  }
  public Key getSignKey(){  //this method will return a key
byte[]keybytes= Decoders.BASE64.decode(SECRET); //setting value of Keybutes variable
        return Keys.hmacShaKeyFor(keybytes);//formatting this keybytes variable and return

  }
  public String extractUserName(String token){  //this will return a string
      return extractClaims(token, Claims::getSubject);

  }
  public <T> T extractClaims(String token, Function<Claims,T> claimsResolver){
    final Claims claims=extractAllCalims(token);
    return claimsResolver.apply(claims);
  }
  private Claims extractAllCalims(String token){
    return Jwts.parserBuilder().setSigningKey(getSignKey().build().parseClaimsjws())
  }

}

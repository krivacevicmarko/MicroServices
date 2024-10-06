package org.example.security.service.implementation;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.security.service.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImplementation implements TokenService {
    @Value("${oauth.jwt.secret}")
    private String jwtSecret;
    @Override
    public String generate(Claims claims) {
       return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, jwtSecret).compact();
    }

    @Override
    public Claims parseToken(String jwt) {
        Claims claims;
        try{
            claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody();
        }catch(Exception a){
            return null;
        }

        return claims;
    }
}

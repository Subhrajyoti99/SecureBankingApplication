package com.spring.security.security;

import com.spring.security.constants.ApplicationConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtUtil {


    private final Environment env;

    public String generateToken(Authentication authResponse){
        String jwt="";
        if (authResponse != null && authResponse.isAuthenticated()) {
            if (env != null) {
                String secret = env.getProperty(ApplicationConstants.JWT_SECRET_KEY
                        , ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);
                //generating the secret key using Keys.hmacShaKeyFor()
                SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

                //generating the jwt token using builder() and signing it with .signWith().compact()
                jwt = Jwts.builder()
                        .issuer("Secure Bank")
                        .subject("JWT Token")
                        .claim("username", authResponse.getName())
                        .claim("role", authResponse.getAuthorities().stream()
                                .map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                        .issuedAt(new java.util.Date())
                        .expiration(new java.util.Date((new java.util.Date()).getTime() + 30000000))
                        .signWith(secretKey).compact();

            }
        }
        return jwt;
    }
}

package com.example.back.Config.SpringSecurity.utils;

import com.example.back.Config.constants.SecurityConstants;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

public class JwtUtil {

    private static SecretKey secretKey;


    public static String getUsername(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("userName", String.class);
    }

    public static List<String> getRole(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", List.class);
    }

    public static boolean isExpired(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    public static String createJwt(String userName, int expiredMs, String key, List<String> roles) {

        secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS512.key().build().getAlgorithm());

        //토큰 생성
        return Jwts.builder()//.signWith(시크릿키, 알고리즘)
                .signWith(secretKey)// jwt 알고리즘 방식
                .header()
                .add("type", SecurityConstants.TOKEN_TYPE)
                .and()
                .claim("userName", userName)
                .claim("roles", roles)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs)) // token 유효기간 설정
                .compact();


    }
}

package com.example.springargumentresolver.jwt;

import com.example.springargumentresolver.domain.User;
import com.example.springargumentresolver.exception.TokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
@Component
public class JwtProvider {

    private SecretKey secretKey = Keys.hmacShaKeyFor("hyenahyenahyenahyenahyenahyenahyena".getBytes(UTF_8));

    private static final long TOKEN_VALID_TIME = 24 * 60 * 60 * 1000L;

    public String createToken(final User user) {
        final Claims claims = Jwts.claims();
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());

        final Date date = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(date)
                .setExpiration(new Date(new Date().getTime() + TOKEN_VALID_TIME))
                .signWith(secretKey, HS256)
                .compact();
    }

    public User getClaim(final String token) {
        final Claims claimsBody = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(removeBearer(token))
                .getBody();

        return User.builder()
                .id(Long.valueOf((Integer) claimsBody.getOrDefault("id", 0L)))
                .username(claimsBody.getOrDefault("username", "").toString())
                .build();
    }

    private String removeBearer(final String token) {
        return token.replace("Bearer", "").trim();
    }

    public boolean validateToken(final String token) {
        try {
            return !Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(removeBearer(token))
                    .getBody()
                    .getExpiration().before(new Date());
        } catch (Exception e) {
            e.printStackTrace();
            throw new TokenException("[ERROR] Token이 없습니다.");
        }
    }
}

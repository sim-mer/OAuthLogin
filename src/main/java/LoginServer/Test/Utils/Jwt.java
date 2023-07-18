package LoginServer.Test.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class Jwt {
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60; //1HOUR
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7; //7DAYS

    private final Key key;

    public Jwt(@Value("${jwt.secret-key}") String key) {
        byte[] keyBytes = key.getBytes();
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String getAccessToken(String subject, Map<String, String> claims) {
        long now = (new Date()).getTime();
        Date ExpiredAt = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        return generate(subject, claims, ExpiredAt);
    }

    public String getRefreshToken(String subject) {
        long now = (new Date()).getTime();
        Date ExpiredAt = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);
        return generate(subject, null, ExpiredAt);
    }

    public String generate(String subject, Map<String, String> claims, Date expiredAt) {
        Claims jwtClaims = Jwts.claims();
        if(claims != null) jwtClaims.putAll(claims);
        return Jwts.builder()
                .setSubject(subject)
                .setClaims(jwtClaims)
                .setExpiration(expiredAt)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }




}

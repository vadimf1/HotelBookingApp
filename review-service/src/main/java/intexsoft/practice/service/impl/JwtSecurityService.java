package intexsoft.practice.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import intexsoft.practice.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JwtSecurityService {

    @Value("${jwt.secret}")
    private String secret;

    private JWTVerifier getVerifier() {
        return JWT.require(Algorithm.HMAC256(secret))
                .withIssuer("Hotel Booking Application")
                .build();
    }

    public boolean validateToken(String token) {
        try {
            DecodedJWT jwt = getVerifier().verify(token);
            Date expiration = jwt.getExpiresAt();
            return expiration == null || expiration.after(new Date());
        } catch (JWTVerificationException e) {
            throw new ServiceException("JWT verification failed", e);
        }
    }

    public String extractUserId(String token) {
        return getVerifier().verify(token).getSubject();
    }

    public List<String> extractRoles(String token) {
        return getVerifier().verify(token).getClaim("roles").asList(String.class);
    }
}

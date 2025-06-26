package intexsoft.practice.user_service.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import intexsoft.practice.user_service.entity.enums.UserRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtProvider {

    @Value("${spring.security.jwt.secret}")
    private String jwtSecret;

    @Value("${spring.security.jwt.expiration}")
    private long jwtExpiration;

    public String generateToken(String email, UserRole role) {
        return JWT.create()
                .withSubject("User details")
                .withClaim("email", email)
                .withClaim("role", role.name())
                .withIssuedAt(new Date())
                .withIssuer("Hotel Booking Application")
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtExpiration * 1000))
                .sign(Algorithm.HMAC256(jwtSecret));
    }

    public String getEmailFromToken(String token) throws JWTVerificationException {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(jwtSecret))
                .withSubject("User details")
                .withIssuer("Hotel Booking Application")
                .build();

        DecodedJWT jwt = jwtVerifier.verify(token);

        return jwt.getClaim("email").asString();
    }
}

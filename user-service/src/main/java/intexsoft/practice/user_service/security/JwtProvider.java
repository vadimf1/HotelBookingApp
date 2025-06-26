package intexsoft.practice.user_service.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import intexsoft.practice.user_service.config.JwtProperties;
import intexsoft.practice.user_service.entity.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final JwtProperties jwtProperties;

    public String generateToken(UUID id, String email, Set<UserRole> roles) {
        List<String> roleNames = roles.stream()
                .map(UserRole::name)
                .toList();

        return JWT.create()
                .withSubject(id.toString())
                .withClaim("email", email)
                .withClaim("roles", roleNames)
                .withIssuedAt(new Date())
                .withIssuer("Hotel Booking Application")
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtProperties.getExpiration() * 1000))
                .sign(Algorithm.HMAC256(jwtProperties.getSecret()));
    }

    public String getEmailFromToken(String token) throws JWTVerificationException {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(jwtProperties.getSecret()))
                .withSubject("User details")
                .withIssuer("Hotel Booking Application")
                .build();

        DecodedJWT jwt = jwtVerifier.verify(token);

        return jwt.getClaim("email").asString();
    }
}

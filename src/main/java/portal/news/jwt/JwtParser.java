package portal.news.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtParser {

    private final JwtKeyManager jwtKeyManager;

    public Optional<Date> extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Optional<String> extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public  <T> Optional<T> extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        T claimValue = claimsResolver.apply(claims);
        return Optional.ofNullable(claimValue);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(jwtKeyManager.getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}

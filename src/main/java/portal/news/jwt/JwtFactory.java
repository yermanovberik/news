package portal.news.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import portal.news.configurations.properties.JwtProperties;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtFactory {

    private final JwtProperties jwtProperties;
    private final JwtKeyManager jwtKeyManager;

    public String generateAccessToken(UserDetails userDetails) {
        return generateAccessToken(Collections.emptyMap(), userDetails);
    }

    public String generateAccessToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return buildToken(
                extraClaims,
                userDetails,
                jwtProperties.accessTokenExpiration()
        );
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return buildToken(
                Collections.emptyMap(),
                userDetails,
                jwtProperties.refreshTokenExpiration()
        );
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        System.out.println(userDetails.getUsername());
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(jwtKeyManager.getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

}

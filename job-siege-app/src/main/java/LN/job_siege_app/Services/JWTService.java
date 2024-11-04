package LN.job_siege_app.Services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {


    private final String secretKey = "S3cure!@#R@nd0m$Tr0ngK3y%FoRJWT1234567890";

    public String generateToken(String email){

        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                       .claims()
                       .add(claims)
                       .subject(email)
                       .issuedAt(new Date(System.currentTimeMillis()))
                       .expiration(new Date(System.currentTimeMillis() + 60 * 30 * 1000)) // 30 minutes
                       .and()
                       .signWith(getKey())
                       .compact();
    }

    private SecretKey getKey() {
        byte[] keyBytes = secretKey.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractEmail(String token) {
        // extract email from token;
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

//    private JwtParserBuilder jwtParserBuilder;
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                       .verifyWith(getKey())
                       .build()
                       .parseSignedClaims(token)
                       .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String email = extractEmail(token);
        return (email.equals(userDetails.getUsername()) && !tokenExpired(token));
    }

    private boolean tokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}

//    private String secretKey = "f1e3cc459404653ace3ccc8158a43875c44f793b450d029b9fac6a03a040e1fd618b2f320f92e852119047ff387bbe080ec3b755d46dba214a0feb1a50872772d3c9dc21eeb9db647f6fe0fad2605fecdd358acc301f41abba6913c1bb048a7a1ce211162054d6a24ad9438dd13a968e7bf3d736cafd6fc806a02c1b33093514db0848d20ada9810fe9d510b4ccbfdfcf0740e492c60ae205ec3489ce966810576f9ab74c24a4945c3ff8dbf4f0ba0facfac68437f0f7a5bba4a2107c01048778399b34190a415afec13185b0c3a339d839b1b061870e357eb8ff4aa0c11e5fc0bb06c8473755a614cc12169829f737779364a0bdf99456f6e96c3055043ed68";

//    public JWTService() throws NoSuchAlgorithmException {
//
//        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
//        SecretKey sk = keyGenerator.generateKey();
//        secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
//
//    }

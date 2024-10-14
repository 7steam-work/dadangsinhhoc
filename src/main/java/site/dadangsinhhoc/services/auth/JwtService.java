package site.dadangsinhhoc.services.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import site.dadangsinhhoc.models.TokenModel;
import site.dadangsinhhoc.models.UserModel;
import site.dadangsinhhoc.repositories.TokenRepository;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtService {
    private final TokenRepository tokenRepository;
    private final UserDetailsService userDetailsService;

    @Value("${app.jwt-secret}")
    private String secretKey;

    @Value("${app-jwt-expiration-milliseconds}")
    private long jwtExpiration;

    // Lấy tên người dùng bằng cách trích xuất từ Token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Lấy ra hạn token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Tạo hạn cho token
    public LocalDateTime generateExpirationDate() {
        return LocalDateTime.now().plus(jwtExpiration, ChronoUnit.MILLIS);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> claims,
            UserDetails userDetails) {
        return buildToken(claims, userDetails, jwtExpiration);
    }

    // REVIEW: đoạn mã lỗi chưa tạo lại token cùng khởi tạo mới hạn của nó
    public String generateRefreshToken(
            UserDetails userDetails
    ) {
        return buildToken(new HashMap<>(), userDetails, jwtExpiration);
    }

    private String buildToken(
            Map<String, Object> claims,
            UserDetails userDetails,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        try {
            final String username = extractUsername(token);
            return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        } catch (ExpiredJwtException e) {
            log.warn("Token đã hết hạn: {}", e.getMessage());
            return false;
        } catch (MalformedJwtException e) {
            log.error("Token không hợp lệ: {}", e.getMessage());
            return false;
        } catch (UnsupportedJwtException e) {
            log.error("Token không được hỗ trợ: {}", e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {
            log.error("Token claims string is empty: {}", e.getMessage());
            return false;
        } catch (SignatureException e) {
            log.error("Xác thực JWT thất bại: {}", e.getMessage());
            return false;
        } catch (Exception e) {
            log.error("Lỗi không xác định khi xác thực token: {}", e.getMessage());
            return false;
        }
    }

    public boolean isTokenValidWithData(UserModel userModel){
        TokenModel tokenModel = tokenRepository.findLastTokenByUser(userModel);
        if (tokenModel == null) {
            log.info("Không có token nào được tìm thấy");
            return false;
        }
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(userModel.getUsername());
        return isTokenValid(tokenModel.getToken(), userDetails) &&
                !tokenModel.isRevoked() &&
                !tokenModel.isExpired();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Xác thực và trích xuất thông tin từ token
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Tạo khóa bí mật: tạo ra một khóa bí mật từ chuỗi secretKey, được sử dụng để ký các JWT.
    private Key getSignInKey() {
        byte[] keyBytes = secretKey.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

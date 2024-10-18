package site.dadangsinhhoc.services.auth;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.exception.ErrorCode;
import site.dadangsinhhoc.models.TokenModel;
import site.dadangsinhhoc.dto.UserDTO;
import site.dadangsinhhoc.models.UserModel;
import site.dadangsinhhoc.repositories.TokenRepository;
import site.dadangsinhhoc.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Builder
public class AuthService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public ResponseObject login(String email, String password) throws Exception {
        try {
            log.info("1. Xác thực người dùng");
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            email,
                            password
                    )
            );

            log.info("2. Lưu thông tin xác thực SecurityContext");
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwtToken;
            UserModel user = userRepository.findByEmail(email);
            TokenModel lastToken = tokenRepository.findLastTokenByUser(user);

            if (lastToken != null && jwtService.isTokenValidWithData(user)) {
                jwtToken = lastToken.getToken();
            } else {
                jwtToken = handleTokenGeneration(user);
            }

            user.setLastSigninedTime(LocalDateTime.now());
            userRepository.save(user);
            return ResponseObject.success("Successfully logged in with email: "+ email, jwtToken);
        } catch (AuthenticationException e) {
            log.error("Authentication failed for user {}: {}", email, e.getMessage());
            return ResponseObject.error(
                    ErrorCode.UNAUTHENTICATION.getCode(),
                    "Invalid email or password"
            );
        } catch (Exception e) {
            log.error("Login failed: {}", e.getMessage());
            return ResponseObject.error(
                    ErrorCode.INTERNAL_SERVER_ERROR.getCode(),
                    "An unexpected error occurred"
            );
        }
    }

    public ResponseObject register(UserDTO dto) {
        UserModel user = UserModel.builder()
                .name(dto.getName())
                .password(passwordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .gender(dto.getGender())
                .dob(dto.getDob())
                .address(dto.getAddress())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .lastSigninedTime(null)
                .role("ROLE_USER")
                .status(true)
                .build();
        UserModel savedUser = userRepository.save(user);
        String jwtToken = jwtService.generateToken(savedUser);
        saveToken(savedUser, jwtToken);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dto.getEmail(),
                dto.getPassword()
                )
        );
        return ResponseObject.success("Successfully registered", jwtToken);
    }

    private String handleTokenGeneration(UserModel user) throws Exception {
        revokeAllUserTokens(user);
        String jwtToken = jwtService.generateToken(user);
        saveToken(user, jwtToken);
        return jwtToken;
    }

    public void revokeAllUserTokens(UserModel userModel) {
        List<TokenModel> tokens = tokenRepository.findByUser(userModel);
        for (TokenModel token : tokens) {
            token.setRevoked(true);
            token.setExpired(true);
        }
        tokenRepository.saveAll(tokens);
    }

    /**
     * Ghi lại token vào csdl
     *
     * @param userModel
     * @param jwtToken
     */
    private void saveToken(UserModel userModel, String jwtToken) {
        log.info("Token saved: {}", jwtToken);
        log.info("ExpirationDate: {}", jwtService.generateExpirationDate());
        tokenRepository.save(
                TokenModel.builder()
                        .token(jwtToken)
                        .expirationDate(jwtService.generateExpirationDate())
                        .tokenType("BEARER")
                        .revoked(false)
                        .expired(false)
                        .user(userModel)
                        .build()
        );
    }
}

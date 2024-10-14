package site.dadangsinhhoc.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "token")
@Data
public class TokenModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token", length = 255)
    private String token;

//    @Column(name = "refresh_token", length = 255)
//    private String refreshToken;

    @Column(name = "token_type", length = 50)
    private String tokenType;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

//    @Column(name = "refresh_expiration_date")
//    private LocalDateTime refreshExpirationDate;

    private boolean revoked;
    private boolean expired;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    public boolean isExpired() {
        return expirationDate.isBefore(LocalDateTime.now());
    }

    public boolean isRevoked() {
        return revoked;
    }
}
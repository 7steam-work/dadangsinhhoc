package site.dadangsinhhoc.repositories;

import org.antlr.v4.runtime.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import site.dadangsinhhoc.models.TokenModel;
import site.dadangsinhhoc.models.UserModel;

import java.util.List;

@Repository
public interface TokenRepository extends JpaRepository<TokenModel, Integer> {
    List<TokenModel> findByUser(UserModel user);
    TokenModel findByToken(String token);
    
    @Query("SELECT t FROM TokenModel t WHERE t.user = :user AND t.expired = false AND t.revoked = false ORDER BY t.expirationDate DESC")
    TokenModel findLastTokenByUser(@Param("user") UserModel user);
}
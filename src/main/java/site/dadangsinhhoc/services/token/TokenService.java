package site.dadangsinhhoc.services.token;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.exception.ErrorCode;
import site.dadangsinhhoc.models.TokenModel;
import site.dadangsinhhoc.repositories.TokenRepository;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TokenService implements ITokenService{
    private final TokenRepository tokenRepository;

    @Override
    public boolean existById(Integer id) {
        return tokenRepository.existsById(id);
    }

    @Override
    public ResponseObject findByToken(String token) throws Throwable {
        try {
            return ResponseObject.success(tokenRepository.findByToken(token));
        } catch (Throwable e) {
            log.error(e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
        }
    }

    @Override
    public ResponseObject writeToken(TokenModel token) throws Throwable {
        try {
            token.setId(null);
            TokenModel saveToken = tokenRepository.save(token);
            return ResponseObject.success(saveToken);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
        }
    }

}

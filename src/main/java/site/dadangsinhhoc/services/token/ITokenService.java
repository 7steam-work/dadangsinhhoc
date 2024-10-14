package site.dadangsinhhoc.services.token;

import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.models.TokenModel;

public interface ITokenService {

    boolean existById(Integer id);

    ResponseObject findByToken(String token) throws Throwable;

    ResponseObject writeToken(TokenModel token) throws Throwable;

}

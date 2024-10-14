package site.dadangsinhhoc.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import site.dadangsinhhoc.dto.response.ResponseObject;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseObject> handleException(Exception ex) {
        log.error("An unexpected error occurred: {}", ex.getMessage());
        ResponseObject responseObject = ResponseObject.error(
                ErrorCode.INTERNAL_SERVER_ERROR.getCode(),
                "An unexpected error occurred: " + ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObject);
    }
}
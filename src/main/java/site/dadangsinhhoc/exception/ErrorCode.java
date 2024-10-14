package site.dadangsinhhoc.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "You do not have enough authority! Try again with admin role."),
    UNAUTHENTICATION(403, "Authentication failed!"),
    NOT_FOUND(404, "Resource Not Found"),
    CONFLICT(409, "Resource Already Exists"),
    PAYLOAD_TOO_LARGE(429, "File is too large! Try again with maximum 10Mb."),
    UNSUPPORTED_MEDIA_TYPE(415, "File must be an image!"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final int code;
    private final String message;

}
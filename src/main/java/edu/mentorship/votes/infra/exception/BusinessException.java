package edu.mentorship.votes.infra.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends RuntimeException {

    private final HttpStatus status;
    private final String code;
    private final String[] details;

    public BusinessException(HttpStatus status, String code, String... details) {
        super(String.format("Throws business exception with http status %s and code %s", status, code));
        this.status = status;
        this.code = code;
        this.details = details;
    }
}


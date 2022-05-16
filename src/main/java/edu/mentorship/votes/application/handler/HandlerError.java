package edu.mentorship.votes.application.handler;

import edu.mentorship.votes.application.dto.ErrorBaseDto;
import edu.mentorship.votes.application.dto.ErrorDetailsDto;
import edu.mentorship.votes.application.dto.ErrorDto;
import edu.mentorship.votes.application.rest.EndpointsTranslator;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.data.util.StreamUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.List;
import java.util.stream.Collectors;

import static edu.mentorship.votes.structure.mapper.MessageMapper.ARGUMENT_INVALID;
import static org.springframework.http.HttpHeaders.ACCEPT_LANGUAGE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
@RequiredArgsConstructor
public class HandlerError implements EndpointsTranslator {

    private final MessageSource messageSource;

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorBaseDto> constraintViolationException(ConstraintViolationException ex, HttpServletRequest http) {
        var errorDetailsDtos = ex.getConstraintViolations().stream()
                .map(constraintViolation -> {
                    var field = fieldNameFromPropertyPath(constraintViolation.getPropertyPath());
                    var message = constraintViolation.getMessage();

                    return new ErrorDetailsDto()
                            .descriptionError(message)
                            .field(field);
                }).collect(Collectors.toList());

        return buildErrorBaseDtoWithDetails(http, errorDetailsDtos);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorBaseDto> methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest http) {
        var errorDetailsDtos = ex.getFieldErrors().stream()
                .map(fieldError -> {
                    var field = fieldError.getField();
                    var message = StringUtils.defaultString(fieldError.getDefaultMessage(), StringUtils.EMPTY);

                    return new ErrorDetailsDto()
                            .descriptionError(message)
                            .field(field);
                }).collect(Collectors.toList());

        return buildErrorBaseDtoWithDetails(http, errorDetailsDtos);
    }

    private ResponseEntity<ErrorBaseDto> buildErrorBaseDtoWithDetails(HttpServletRequest request,
                                                                       List<ErrorDetailsDto> errors) {
        var path = request.getServletPath();
        var locale = getLocale(request.getHeader(ACCEPT_LANGUAGE));
        var message = getMessage(ARGUMENT_INVALID.getCode(), locale);
        var error = new ErrorDto()
                .details(errors)
                .message(message)
                .path(path)
                .statusCode(BAD_REQUEST.value());

        return ResponseEntity.status(BAD_REQUEST).body(error);
    }

    private String fieldNameFromPropertyPath(Path path) {
        var list = StreamUtils.createStreamFromIterator(path.iterator()).collect(Collectors.toList());
        return list.get(list.size() - 1).getName();
    }

    @Override
    public MessageSource getMessageSource() {
        return messageSource;
    }
}

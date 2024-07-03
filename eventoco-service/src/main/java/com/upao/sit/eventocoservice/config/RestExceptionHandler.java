package com.upao.sit.eventocoservice.config;

import com.upao.sit.eventocoservice.exception.BadRequestException;
import com.upao.sit.eventocoservice.exception.ResourceDuplicateException;
import com.upao.sit.eventocoservice.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.View;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Locale;

@RestControllerAdvice
@AllArgsConstructor
public class RestExceptionHandler {
    private final View error;
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                "La solicitud tiene unos errores de validación.");

        Set<String> errors = new HashSet<>();
        List<FieldError> fieldErrors = ex.getFieldErrors();

        for (FieldError fe : fieldErrors) {
            String message = messageSource.getMessage(fe, Locale.getDefault());
            errors.add(message);
        }
        problemDetail.setProperty("errors", errors);

        return problemDetail;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,"El recurso no ha sido encontrado");
    }

    @ExceptionHandler(BadRequestException.class)
    public ProblemDetail handleBadRequestException(BadRequestException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(ResourceDuplicateException.class)
    public ProblemDetail handleResourceDuplicateException(ResourceDuplicateException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
    }
}

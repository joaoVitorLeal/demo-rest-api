package io.github.joaoVitorLeal.demo_rest_api.exception.handler;

import io.github.joaoVitorLeal.demo_rest_api.exception.BusinessException;
import io.github.joaoVitorLeal.demo_rest_api.exception.payload.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGeneral(
            Exception ex,
            HttpServletRequest request
    ) {
        logger.error("Unexpected error at [{}]: {}", request.getRequestURI(), ex.toString(), ex);

        String message = extractMessage(ex);
        var body = new ErrorResponseDTO(
                Instant.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                List.of(message),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(body);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponseDTO> handleBusinessException(
            BusinessException ex,
            HttpServletRequest request
    ) {
        logger.error("Business error at [{}]: {}", request.getRequestURI(), ex.toString(), ex);

        var body = new ErrorResponseDTO(
                Instant.now(),
                HttpStatus.CONFLICT.value(),
                List.of(ex.getMessage()),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(body);
    }

    /**
     * @deprecated Use {@link #handleBusinessException(BusinessException, HttpServletRequest)} instead.
     * @since 1.0.0
     */
    @Deprecated
    public ErrorResponseDTO handleBusinessRulesException(
            BusinessException ex,
            HttpServletRequest request
    ) {
        return new ErrorResponseDTO(
                Instant.now(),
                HttpStatus.CONFLICT.value(),
                List.of(ex.getMessage()),
                request.getRequestURI()
        );
    }

    private String extractMessage(Throwable ex) {
        String message = ex.getMessage();
        if (message == null || message.isBlank()) {
            logger.warn("Exception with null/empty message: {}", ex.getClass().getSimpleName());
            return "Unexpected server error.";
        }
        return message;
    }
}

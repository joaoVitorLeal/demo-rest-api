package io.github.joaoVitorLeal.demo_rest_api.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}

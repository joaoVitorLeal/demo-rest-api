package io.github.joaoVitorLeal.demo_rest_api.exception.payload;

import java.time.Instant;
import java.util.List;

public record ErrorResponseDTO(
        Instant timestamp,
        int statusCode,
        List<String> error,
        String path
    ) {

    // Construtor de conveniência: só precisa do HttpStatus, da mensagem de erro e do path
    public ErrorResponseDTO(int statusCode, List<String> error, String path) {
        this(Instant.now(), statusCode, error, path);
    }
}

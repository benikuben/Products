package ru.neoflex.products.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    @Schema(
            description = "message",
            name = "message",
            example = "Not found"
    )
    private String message;
}

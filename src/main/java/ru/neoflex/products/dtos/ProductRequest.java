package ru.neoflex.products.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    @Pattern(regexp = "^([A-Za-z ]{2,50})$", message = "Name must contain from 2 to 50 Latin letters")
    @Schema(
            description = "name",
            name = "name",
            example = "credit card"
    )
    private String name;
    @NotNull(message = "Product type must not be null")
    @Schema(
            description = "product type",
            name = "product type",
            example = "card"
    )
    private ProductType productType;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Start date must not be null")
    @Schema(
            description = "start date",
            name = "start date",
            example = "2000-01-01"
    )
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "End date must not be null")
    @Schema(
            description = "end date",
            name = "end date",
            example = "2001-01-01"
    )
    private LocalDate endDate;
    @Pattern(regexp = "^([A-Za-z]{10,100})$", message = "Description must contain from 10 to 100 Latin letters")
    @Schema(
            description = "description",
            name = "description",
            example = "credit card"
    )
    private String description;
    @Valid
    @NotNull(message = "Tariff must not be null")
    @Schema(
            description = "tariff name",
            name = "tariff name",
            example = "credit card tariff"
    )
    private String tariffName;
    @NotNull(message = "Author must not be null")
    @Schema(
            description = "author",
            name = "author",
            example = "61f0c404-5cb3-11e7-907b-a6006ad3dba0"
    )
    private UUID author;
}

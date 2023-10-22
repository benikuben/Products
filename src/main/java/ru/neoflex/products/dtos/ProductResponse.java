package ru.neoflex.products.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    @Schema(
            description = "name",
            name = "name",
            example = "credit card"
    )
    private String name;
    @Schema(
            description = "product type",
            name = "product type",
            example = "card"
    )
    private ProductType productType;
    @Schema(
            description = "start date",
            name = "start date",
            example = "2000-01-01"
    )
    private LocalDate startDate;
    @Schema(
            description = "end date",
            name = "end date",
            example = "2001-01-01"
    )
    private LocalDate endDate;
    @Schema(
            description = "description",
            name = "description",
            example = "credit card"
    )
    private String description;
    @Schema(
            description = "tariff name",
            name = "tariff name",
            example = "credit card tariff"
    )
    private TariffResponse tariff;
    @Schema(
            description = "author",
            name = "author",
            example = "61f0c404-5cb3-11e7-907b-a6006ad3dba0"
    )
    private UUID author;
    @Schema(
            description = "version",
            name = "version",
            example = "1"
    )
    private Integer version;
}

package ru.neoflex.products.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TariffResponse {
    @Schema(
            description = "name",
            name = "name",
            example = "credit card tariff"
    )
    private String name;
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
            description = "rate",
            name = "rate",
            example = "12"
    )
    private Double rate;
    @Schema(
            description = "version",
            name = "version",
            example = "1"
    )
    private Integer version;
}

package ru.neoflex.products.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class ProductResponse {
    private String name;
    private ProductType productType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private TariffResponse tariff;
    private UUID author;
    private Integer version;
}

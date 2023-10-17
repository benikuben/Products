package ru.neoflex.products.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TariffResponse {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private Double rate;
    private Integer version;
}

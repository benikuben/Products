package ru.neoflex.products.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class ProductRequest {
    @Pattern(regexp = "^([A-Za-z]{2,50})$", message = "Name must contain from 2 to 50 Latin letters")
    private String name;
    @NotNull(message = "Product type must not be null")
    private ProductType productType;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Start date must not be null")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "End date must not be null")
    private LocalDate endDate;
    @Pattern(regexp = "^([A-Za-z]{10,100})$", message = "Description must contain from 10 to 100 Latin letters")
    private String description;
    @Valid
    @NotNull(message = "Tariff must not be null")
    private String tariffName;
    @NotNull(message = "Author must not be null")
    private UUID author;
}

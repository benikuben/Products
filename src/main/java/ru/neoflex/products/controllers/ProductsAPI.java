package ru.neoflex.products.controllers;

import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.products.dtos.ProductRequest;
import ru.neoflex.products.dtos.ProductResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ProductsAPI {
    @GetMapping("/{id}/current-version")
    ResponseEntity<ProductResponse> getCurrentVersion(@PathVariable("id") UUID id);

    @PostMapping()
    ResponseEntity<Void> create(@RequestBody @Valid ProductRequest productRequest);

    @PatchMapping("/{id}")
    ResponseEntity<Void> update(@PathVariable("id") UUID id,
                                @RequestBody @Valid ProductRequest productRequest);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") UUID id);

    @GetMapping("/versions/{id}")
    ResponseEntity<List<ProductResponse>> getAllPreviousVersions(@PathVariable("id") UUID id);

    @GetMapping("/version/{id}/period")
    ResponseEntity<List<ProductResponse>> getVersionForCertainPeriod(@PathVariable("id") UUID id,
                                                                     @RequestParam("start_date")
                                                                     @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                                     LocalDate startDate,
                                                                     @RequestParam("end_date")
                                                                     @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                                     LocalDate endDate);

    @PutMapping("/version/{id}/rollback")
    ResponseEntity<Void> rollBackVersion(@PathVariable("id") UUID id);
}

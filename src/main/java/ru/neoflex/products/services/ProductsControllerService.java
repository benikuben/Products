package ru.neoflex.products.services;

import ru.neoflex.products.dtos.ProductRequest;
import ru.neoflex.products.dtos.ProductResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ProductsControllerService {
    ProductResponse getCurrentVersion(UUID id);

    void create(ProductRequest productRequest);

    void update(UUID id, ProductRequest productRequest);

    void delete(UUID id);

    List<ProductResponse> getAllPreviousVersions(UUID id);

    List<ProductResponse> getVersionForCertainPeriod(UUID id, LocalDate startDate, LocalDate endDate);

    void rollBackVersion(UUID id);
}

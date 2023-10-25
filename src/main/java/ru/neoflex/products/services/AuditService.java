package ru.neoflex.products.services;

import ru.neoflex.products.models.Product;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AuditService {
    List<Product> getAllPreviousVersions(UUID id, Integer currentVersion);

    List<Product> getVersionForCertainPeriod(UUID id, LocalDate startDate, LocalDate endDate);

    Product revertToPreviousRevision(UUID id, Integer currentVersion);
}

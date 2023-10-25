package ru.neoflex.products.repositories.audit;

import ru.neoflex.products.models.Product;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AuditRepository {
    List<Product> getAllVersions(UUID id);

    List<Product> getVersionForCertainPeriod(UUID id, LocalDate startDate, LocalDate endDate);

    Product getPreviousVersion(UUID id, Integer currentVersion);

    void deleteCurrentVersion(UUID id, Integer currentVersion);
}

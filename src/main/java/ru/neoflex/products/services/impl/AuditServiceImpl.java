package ru.neoflex.products.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.neoflex.products.models.Product;
import ru.neoflex.products.repositories.audit.AuditRepository;
import ru.neoflex.products.services.AuditService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuditServiceImpl implements AuditService {
    private final AuditRepository auditRepository;

    @Override
    public List<Product> getAllPreviousVersions(UUID id, Integer currentVersion) {
        log.info("Get all previous versions of product with id {} and currentVersion {}", id, currentVersion);
        List<Product> products = auditRepository.getAllVersions(id).stream()
                .filter(t -> t.getVersion() < currentVersion)
                .toList();
        log.info("Previous versions {}", products);
        return products;
    }

    @Override
    public List<Product> getVersionForCertainPeriod(UUID id, LocalDate startDate, LocalDate endDate) {
        log.info("Get all versions of product with id {} from {} to {}", id, startDate, endDate);
        List<Product> products = auditRepository.getVersionForCertainPeriod(id, startDate, endDate);
        log.info("Versions {}", products);
        return products;
    }

    @Override
    public Product revertToPreviousRevision(UUID id, Integer currentVersion) {
        log.info("Revert to previous revision of product with id {} and currentVersion {}", id, currentVersion);
        Product previousVersion = auditRepository.getPreviousVersion(id, currentVersion);
        log.info("Previous version {}", previousVersion);
        log.info("Delete current version of product with id {} and currentVersion {}", id, currentVersion);
        auditRepository.deleteCurrentVersion(id, currentVersion);
        return previousVersion;
    }
}

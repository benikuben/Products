package ru.neoflex.products.repositories.audit;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.neoflex.products.models.Product;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Component
@Transactional(readOnly = true)
public class AuditRepositoryImpl implements AuditRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> getAllVersions(UUID id) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        AuditQuery auditQuery = auditReader.createQuery().forRevisionsOfEntity(Product.class, true, true)
                .add(AuditEntity.id().eq(id));

        List<Product> results = auditQuery.getResultList();

        return results;
    }

    @Override
    public List<Product> getVersionForCertainPeriod(UUID id, LocalDate startDate, LocalDate endDate) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        AuditQuery auditQuery = auditReader.createQuery().forRevisionsOfEntity(Product.class, true, true)
                .add(AuditEntity.id().eq(id))
                .add(AuditEntity.property("startDate").ge(startDate))
                .add(AuditEntity.property("endDate").le(endDate));

        List<Product> results = auditQuery.getResultList();

        return results;
    }

    @Override
    public Product getPreviousVersion(UUID id, Integer currentVersion) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        AuditQuery auditQuery = auditReader.createQuery().forRevisionsOfEntity(Product.class, true, true)
                .add(AuditEntity.id().eq(id))
                .add(AuditEntity.property("version").eq(currentVersion - 1));

        Product result = (Product) auditQuery.getSingleResult();

        return result;
    }

    @Override
    public void deleteCurrentVersion(UUID id, Integer currentVersion) {
        entityManager.createNativeQuery("delete from tariff_aud where id = :id and version = :currentV or version = :previousV")
                .setParameter("id", id)
                .setParameter("currentV", currentVersion)
                .setParameter("previousV", currentVersion - 1)
                .executeUpdate();
    }
}

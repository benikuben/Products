package ru.neoflex.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.neoflex.products.models.Product;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findByName(String name);

    @Modifying
    @Query("update Product p set p.version = p.version + 1 where p.tariff.id = ?1")
    void updateByTariff(UUID tariffId);
}

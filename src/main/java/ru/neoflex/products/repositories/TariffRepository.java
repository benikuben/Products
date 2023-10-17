package ru.neoflex.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.neoflex.products.models.Tariff;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TariffRepository extends JpaRepository<Tariff, UUID> {
    Optional<Tariff> findByName(String name);
}

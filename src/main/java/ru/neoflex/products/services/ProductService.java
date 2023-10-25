package ru.neoflex.products.services;

import ru.neoflex.products.models.Product;

import java.util.Optional;
import java.util.UUID;

public interface ProductService {
    Optional<Product> findById(UUID id);

    Optional<Product> findByName(String name);

    void save(Product product);

    void update(UUID id, Product product);

    void delete(Product product);

    void updateByTariff(UUID tariffId);
}

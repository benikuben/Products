package ru.neoflex.products.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.neoflex.products.models.Product;
import ru.neoflex.products.repositories.ProductRepository;
import ru.neoflex.products.services.ProductService;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Optional<Product> findById(UUID id) {
        log.info("Find product by id {}", id);
        Optional<Product> product = productRepository.findById(id);
        log.info("Found product {}", product);
        return product;
    }

    @Override
    public Optional<Product> findByName(String name) {
        log.info("Find product by name {}", name);
        Optional<Product> product = productRepository.findByName(name);
        log.info("Found product {}", product);
        return product;
    }

    @Transactional
    @Override
    public void save(Product product) {
        productRepository.save(product);
        log.info("Saved product {}", product);
    }

    @Transactional
    @Override
    public void update(UUID id, Product updatedProduct) {
        updatedProduct.setId(id);
        productRepository.save(updatedProduct);
        log.info("Updated product {}", updatedProduct);
    }

    @Transactional
    @Override
    public void delete(Product product) {
        productRepository.delete(product);
        log.info("Deleted product {}", product);
    }

    @Transactional
    @Override
    public void updateByTariff(UUID tariffId) {
        log.info("Update product by tariff with id {}", tariffId);
        productRepository.updateByTariff(tariffId);
    }
}

package ru.neoflex.products.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.neoflex.products.dtos.ProductRequest;
import ru.neoflex.products.dtos.ProductResponse;
import ru.neoflex.products.exceptions.NotFoundException;
import ru.neoflex.products.exceptions.ProductsException;
import ru.neoflex.products.models.Product;
import ru.neoflex.products.models.Tariff;
import ru.neoflex.products.services.AuditService;
import ru.neoflex.products.services.ProductService;
import ru.neoflex.products.services.ProductsControllerService;
import ru.neoflex.products.services.TariffService;
import ru.neoflex.products.util.mappers.ProductMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductsControllerServiceImpl implements ProductsControllerService {
    private final AuditService auditService;
    private final ProductService productService;
    private final TariffService tariffService;
    private final ProductMapper productMapper;

    @Override
    public ProductResponse getCurrentVersion(UUID id) {
        Product product = productService.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Product with id %s not found", id)));

        return productMapper.productToProductDto(product);
    }

    @Override
    @Transactional
    public void create(ProductRequest productRequest) {
        Product newProduct = productMapper.productDtoToProduct(productRequest);

        String name = newProduct.getName();
        if (productService.findByName(name).isPresent()) {
            throw new ProductsException(String.format("Product with name %s already exists", name));
        }

        String newProductTariffName = newProduct.getTariff().getName();
        Tariff tariff = tariffService.findByName(newProductTariffName)
                .orElseThrow(() -> new NotFoundException(String.format("Tariff with name %s not found", newProductTariffName)));

        newProduct.setTariff(tariff);
        newProduct.setVersion(1);

        productService.save(newProduct);
    }

    @Override
    @Transactional
    public void update(UUID id, ProductRequest productRequest) {
        Product updatedProduct = productMapper.productDtoToProduct(productRequest);

        Product productFromDb = productService.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Product with id %s not found", id)));

        updatedProduct.setId(id);
        updatedProduct.setVersion(productFromDb.getVersion());

        String updatedProductTariffName = updatedProduct.getTariff().getName();
        if (!productFromDb.getTariff().getName()
                .equals(updatedProductTariffName))
            updatedProduct.setTariff(
                    tariffService.findByName(updatedProductTariffName)
                            .orElseThrow(() -> new NotFoundException(String.format("Tariff with name %s not found", updatedProductTariffName)))
            );
        else {
            updatedProduct.setTariff(productFromDb.getTariff());
        }

        if (!productFromDb.equals(updatedProduct)) {
            updatedProduct.updateVersion();
        }

        productService.update(id, updatedProduct);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Product product = productService.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Product with id %s not found", id)));

        productService.delete(product);
    }

    @Override
    public List<ProductResponse> getAllPreviousVersions(UUID id) {
        Integer currentVersion = productService.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Product with id %s not found", id)))
                .getVersion();

        List<Product> products = auditService.getAllPreviousVersions(id, currentVersion);

        return products.stream().map(productMapper::productToProductDto).toList();
    }

    @Override
    public List<ProductResponse> getVersionForCertainPeriod(UUID id, LocalDate startDate, LocalDate endDate) {
        List<Product> products = auditService.getVersionForCertainPeriod(id, startDate, endDate);
        return products.stream().map(productMapper::productToProductDto).toList();

    }

    @Override
    @Transactional
    public void rollBackVersion(UUID id) {
        Integer currentVersion = productService.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Product with id %s not found", id)))
                .getVersion();

        if (currentVersion != 1) {
            Product previousVersionOfProduct = auditService.revertToPreviousRevision(id, currentVersion);

            productService.update(id, previousVersionOfProduct);
        }
    }
}

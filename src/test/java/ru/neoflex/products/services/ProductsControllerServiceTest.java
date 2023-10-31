package ru.neoflex.products.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.neoflex.products.dtos.ProductRequest;
import ru.neoflex.products.exceptions.NotFoundException;
import ru.neoflex.products.exceptions.ProductsException;
import ru.neoflex.products.models.Product;
import ru.neoflex.products.models.Tariff;
import ru.neoflex.products.services.impl.ProductsControllerServiceImpl;
import ru.neoflex.products.util.mappers.ProductMapper;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductsControllerServiceTest {
    @Mock
    private ProductService productService;
    @Mock
    private TariffService tariffService;
    @Mock
    private ProductMapper productMapper;
    @InjectMocks
    private ProductsControllerServiceImpl productsControllerService;

    @Test
    void getCurrentVersion() {
        when(productService.findById(any()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> productsControllerService.getCurrentVersion(UUID.randomUUID()));
    }

    @Test
    void create() {
        Product product = Product.builder()
                .tariff(new Tariff())
                .build();

        when(productMapper.productDtoToProduct(any()))
                .thenReturn(product);

        when(productService.findByName(any()))
                .thenReturn(Optional.of(product), Optional.empty());

        assertThrows(ProductsException.class, () ->
                productsControllerService.create(
                        new ProductRequest()
                ));

        when(tariffService.findByName(any()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () ->
                productsControllerService.create(
                        new ProductRequest()
                ));
    }

    @Test
    void update() {
        UUID id = UUID.randomUUID();

        when(productMapper.productDtoToProduct(any()))
                .thenReturn(
                        Product.builder()
                                .tariff(Tariff.builder().name("tariff").build())
                                .build());

        when(productService.findById(any())).thenReturn(Optional.empty(),
                Optional.of(
                        Product.builder()
                                .tariff(Tariff.builder().name("different tariff").build())
                                .build()
                ));

        assertThrows(NotFoundException.class, () ->
                productsControllerService.update(
                        id, new ProductRequest()
                ));

        when(tariffService.findByName(any()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () ->
                productsControllerService.update(
                        id, new ProductRequest()
                ));
    }

    @Test
    void delete() {
        when(productService.findById(any()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> productsControllerService.delete(UUID.randomUUID()));
    }

    @Test
    void getAllPreviousVersions() {
        when(productService.findById(any()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> productsControllerService.getAllPreviousVersions(UUID.randomUUID()));
    }

    @Test
    void getVersionForCertainPeriod() {
        when(productService.findById(any()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> productsControllerService.getVersionForCertainPeriod(UUID.randomUUID(),
                LocalDate.of(2000, 1, 1),
                LocalDate.of(2000, 2, 1)));
    }

    @Test
    void rollBackVersion() {
        when(productService.findById(any()))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> productsControllerService.rollBackVersion(UUID.randomUUID()));
    }
}
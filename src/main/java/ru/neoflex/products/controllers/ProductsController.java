package ru.neoflex.products.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.products.dtos.ProductRequest;
import ru.neoflex.products.dtos.ProductResponse;
import ru.neoflex.products.services.ProductsControllerService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductsController implements ProductsAPI {
    private final ProductsControllerService productsControllerService;

    @Override
    public ResponseEntity<ProductResponse> getCurrentVersion(UUID id) {
        return new ResponseEntity<>(productsControllerService.getCurrentVersion(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> create(ProductRequest productRequest) {
        productsControllerService.create(productRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> update(UUID id, ProductRequest productRequest) {
        productsControllerService.update(id, productRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(UUID id) {
        productsControllerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProductResponse>> getAllPreviousVersions(UUID id) {
        return new ResponseEntity<>(productsControllerService.getAllPreviousVersions(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProductResponse>> getVersionForCertainPeriod(UUID id, LocalDate startDate, LocalDate endDate) {
        return new ResponseEntity<>(productsControllerService.getVersionForCertainPeriod(id, startDate, endDate), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> rollBackVersion(UUID id) {
        productsControllerService.rollBackVersion(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

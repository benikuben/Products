package ru.neoflex.products.util.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.neoflex.products.dtos.ProductRequest;
import ru.neoflex.products.dtos.ProductResponse;
import ru.neoflex.products.models.Product;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {TariffMapper.class})
public interface ProductMapper {
    @Mapping(source = "tariffName", target = "tariff.name")
    Product productDtoToProduct(ProductRequest productRequest);

    @Mapping(source = "tariff", target = "tariff")
    ProductResponse productToProductDto(Product product);
}

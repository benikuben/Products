package ru.neoflex.products.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import ru.neoflex.products.exceptions.ProductsException;

public enum ProductType {
    LOAN("loan"),
    CARD("card");

    private String value;

    ProductType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static ProductType fromValue(String value) {
        for (ProductType b : ProductType.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new ProductsException(String.format("Unexpected product type %s", value));
    }
}

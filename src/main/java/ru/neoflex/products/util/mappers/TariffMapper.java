package ru.neoflex.products.util.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.neoflex.products.dtos.TariffResponse;
import ru.neoflex.products.models.Tariff;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TariffMapper {
    TariffResponse tariffToTariffDto(Tariff tariff);
}

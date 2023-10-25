package ru.neoflex.products.services;


import ru.neoflex.products.models.Tariff;

import java.util.Optional;
import java.util.UUID;

public interface TariffService {
    Optional<Tariff> findByName(String name);
    void save(Tariff tariff);

    void update(UUID id, Tariff tariff);

    void delete(Tariff tariff);

}

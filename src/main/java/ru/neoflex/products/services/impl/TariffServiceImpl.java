package ru.neoflex.products.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.neoflex.products.models.Tariff;
import ru.neoflex.products.repositories.TariffRepository;
import ru.neoflex.products.services.TariffService;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TariffServiceImpl implements TariffService {
    private final TariffRepository tariffRepository;

    @Override
    public Optional<Tariff> findByName(String name) {
        log.info("Find tariff by name {}", name);
        return tariffRepository.findByName(name);
    }

    @Override
    @Transactional
    public void save(Tariff tariff) {
        tariffRepository.save(tariff);
        log.info("Saved tariff {}", tariff);
    }

    @Override
    @Transactional
    public void update(UUID id, Tariff tariff) {
        tariff.setId(id);
        tariffRepository.save(tariff);
        log.info("Updated tariff {}", tariff);
    }

    @Override
    @Transactional
    public void delete(Tariff tariff) {
        tariffRepository.delete(tariff);
        log.info("Deleted tariff {}", tariff);
    }
}

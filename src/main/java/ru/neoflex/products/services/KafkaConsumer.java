package ru.neoflex.products.services;

import ru.neoflex.products.models.Tariff;

public interface KafkaConsumer {
    void consumeMessage(Tariff tariff, String topic);
}

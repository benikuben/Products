package ru.neoflex.products.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.neoflex.products.models.Tariff;
import ru.neoflex.products.services.KafkaConsumer;
import ru.neoflex.products.services.ProductService;
import ru.neoflex.products.services.TariffService;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumerImpl implements KafkaConsumer {
    private final TariffService tariffService;
    private final ProductService productService;
    public static final String CREATE_TARIFF = "create-tariff";
    public static final String UPDATE_TARIFF = "update-tariff";
    public static final String DELETE_TARIFF = "delete-tariff";
    public static final String GROUP_ID = "products";

    @Override
    @KafkaListener(groupId = GROUP_ID,
            topics = {CREATE_TARIFF, UPDATE_TARIFF, DELETE_TARIFF})
    @Transactional(transactionManager = "transactionManager")
    public void consumeMessage(Tariff tariff, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        log.info("Message consumed {}", tariff);

        productService.updateByTariff(tariff.getId());
        tariffService.update(tariff.getId(), tariff);

        switch (topic) {
            case CREATE_TARIFF -> tariffService.save(tariff);
            case UPDATE_TARIFF -> {
                tariffService.update(tariff.getId(), tariff);
                productService.updateByTariff(tariff.getId());
            }
            case DELETE_TARIFF -> {
                tariffService.delete(tariff);
//                productService.updateByTariff(tariff.getId());
            }
            default -> log.warn("{} - unhandled topic", topic);
        }
    }
}


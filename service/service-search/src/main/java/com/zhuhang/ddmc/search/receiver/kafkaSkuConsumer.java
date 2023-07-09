package com.zhuhang.ddmc.search.receiver;

import com.zhuhang.ddmc.search.Service.SkuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class kafkaSkuConsumer {
    @Autowired
    private SkuService skuService;

    @KafkaListener(topics = "up-sku")
    public void upsku(String content) {
        Long skuId = Long.parseLong(content);
        if (null != skuId) {
            log.info("consumer processMessage up sku: {}",skuId);

            skuService.upperSku(skuId);
        }
    }

    @KafkaListener(topics = "down-sku")
    public void downsku(String content) {
        Long skuId = Long.parseLong(content);

        log.info("consumer processMessage down sku: {}",skuId);
        if (null != skuId) {
            skuService.lowerSku(skuId);
        }
    }
}

package com.tygq.demo04.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@PropertySource(value = {"classpath:test.properties"}, ignoreResourceNotFound = true)
public class KafkaReceiver {

    @KafkaListener(topics = "${consumer.group.id}")
    public void listen(ConsumerRecord<?, ?> record) {

        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            System.out.println("----------------- record =" + record);
            System.out.println("------------------ message =" + message);
        }

    }
}

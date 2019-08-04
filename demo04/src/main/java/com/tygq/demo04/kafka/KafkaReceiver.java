package com.tygq.demo04.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@PropertySource(value = {"classpath:test.properties"}, ignoreResourceNotFound = true)
public class KafkaReceiver {
    @Autowired
    private AutomaticResolveServiceDataUtil automaticResolveServiceDataUtil;

    @KafkaListener(topics = "${consumer.group.id}")
    public void listen(ConsumerRecord<?, ?> record) {

        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {
            log.info("开始接收...");
            Object message = kafkaMessage.get();
            log.info("-----------------接收到的数据 record =" + record);
            log.info("------------------接收到的数据 message =" + message);
            //将收到的数据入库
            automaticResolveServiceDataUtil.receiveData(message.toString());
        }

    }
}

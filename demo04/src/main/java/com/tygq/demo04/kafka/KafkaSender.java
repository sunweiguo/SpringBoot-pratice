package com.tygq.demo04.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tygq.demo04.utils.JsonUtil;
import com.tygq.demo04.vo.MessageVO;
import com.tygq.demo04.vo.TopicConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@PropertySource(value = {"classpath:test.properties"}, ignoreResourceNotFound = true)
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${producer.group.id}")
    private String producerGroupId;

    //发送消息方法
    public void send(MessageVO messageVO) throws JsonProcessingException {
        kafkaTemplate.send(producerGroupId, messageVO.getSendmsg());
    }
}

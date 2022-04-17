package com.techopact.kafkautil.service.impl;

import com.techopact.kafkautil.service.MessageConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaMessageConsumer implements MessageConsumer {


    @Override
    @KafkaListener(topics = "quickstart-events", groupId = "employee-group-id") //Group mentioned here takes precedence
    public void consumeMessage(Message<byte[]> message) {
        log.info("Generic Message: " + message);
        log.info("Payload: " + new String(message.getPayload()));

        final MessageHeaders headers = message.getHeaders();
        log.info("Header: " + headers);
        log.info("Key: " + headers.get(KafkaHeaders.RECEIVED_MESSAGE_KEY));
        log.info("Partition: " + headers.get(KafkaHeaders.RECEIVED_PARTITION_ID));
    }
}

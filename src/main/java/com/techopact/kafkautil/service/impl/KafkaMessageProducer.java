package com.techopact.kafkautil.service.impl;

import com.techopact.kafkautil.service.MessageProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaMessageProducer implements MessageProducer {
    private final KafkaTemplate<Object, Object> kafkaTemplate;

    @Override
    public void send(String topicName, Integer partition, Object key, Object value) {
        final ListenableFuture<SendResult<Object, Object>> sendStatus = partition == null ?
                kafkaTemplate.send(topicName, key, value) :
                kafkaTemplate.send(topicName, partition, key, value);

        sendStatus.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult<Object, Object> result) {
                log.info("The following message has been sent to the partition {} with offset {}. Key: {}, value: {}",
                        result.getRecordMetadata().partition(), result.getRecordMetadata().offset(), key, value);
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Unable to send message=[" + value + "] due to : " + ex.getMessage());
            }
        });
    }


}

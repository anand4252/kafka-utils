package com.techopact.kafkautil.service;


import org.springframework.messaging.Message;

public interface MessageConsumer {

    void consumeMessage(Message<byte[]> message);
}

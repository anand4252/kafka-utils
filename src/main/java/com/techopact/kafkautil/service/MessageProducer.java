package com.techopact.kafkautil.service;

public interface MessageProducer {
     void send(String topicName, Integer partition, Object key, Object value);
}

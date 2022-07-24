# Kafka Commands
#### Create Topic:

```bin/kafka-topics.sh --create --topic sample-input --bootstrap-server localhost:9092``` 


#### List All topics:
```bin/kafka-topics.sh --list --bootstrap-server localhost:9092``` 


#### Describe a topics:
```bin/kafka-topics.sh --describe --topic sample-input --bootstrap-server localhost:9092``` 


#### Delete a topics:
```bin/kafka-topics.sh --delete --topic kafka-streams-poc-KSTREAM-REDUCE-STATE-STORE-0000000003-changelog --bootstrap-server localhost:9092``` 


## Consumer 
#### Create consumer:
```
bin/kafka-console-consumer.sh \
  --topic sample-input \
  --bootstrap-server localhost:9092 \
  --from-beginning \
  --property print.key=true \
  --property key.separator="-"
```



#### List All Consumers:
```bin/kafka-consumer-groups.sh --list --bootstrap-server localhost:9092```


#### Delete a consumer group:
```bin/kafka-consumer-groups.sh \
--bootstrap-server localhost:9092 \
--delete --group console-consumer-13617
```


#### Describe a consumer group to check lag:
```bin/kafka-consumer-groups.sh --describe --group kafka-streams-poc --bootstrap-server localhost:9092``` 

## Producer 
#### Produce messages:
```bin/kafka-console-producer.sh \
  --topic sample-input \
  --bootstrap-server localhost:9092 \
  --property parse.key=true \
  --property key.separator="-"
```
```bin/kafka-console-producer.sh --topic sample-input --bootstrap-server localhost:9092 --property key.separator="-"```



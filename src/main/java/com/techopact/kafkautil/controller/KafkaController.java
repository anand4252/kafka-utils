package com.techopact.kafkautil.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techopact.kafkautil.model.Address;
import com.techopact.kafkautil.model.Employee;
import com.techopact.kafkautil.service.MessageProducer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafkautils")
@RequiredArgsConstructor
public class KafkaController {

    private final MessageProducer messageSender;

    @RequestMapping("/produce/{topic}")
    public String produce(@PathVariable String topic, @RequestParam(required = false) Integer partition,
                          @RequestParam(required = false) String key, @RequestParam String value) {

        messageSender.send(topic, partition, key, value.getBytes());
        return "success";
    }

    @RequestMapping("/produce/{topic}/employee")
    @SneakyThrows
    public String produceEmployee(@PathVariable String topic, @RequestParam(required = false) Integer partition,
                                  @RequestParam(required = false) String key) {
        final Employee employee = Employee.builder()
                .name("cutes")
                .age(6)
                .address(Address.builder()
                        .city("chennai")
                        .country("India")
                        .build())
                .build();
        ObjectMapper mapper = new ObjectMapper();
        final byte[] employeeAsBytes = mapper.writeValueAsBytes(employee);
        messageSender.send(topic, partition, key, employeeAsBytes);
        return "success";
    }


}

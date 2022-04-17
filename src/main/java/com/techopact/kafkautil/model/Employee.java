package com.techopact.kafkautil.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employee {
    private String name;
    private int age;
    private Address address;
}

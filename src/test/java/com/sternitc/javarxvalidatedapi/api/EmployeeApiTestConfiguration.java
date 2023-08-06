package com.sternitc.javarxvalidatedapi.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.IdGenerator;

import java.util.UUID;

@Configuration
public class EmployeeApiTestConfiguration {

    @Bean
    public IdGenerator idGenerator() {
        return new IdGenerator() {
            public UUID id = UUID.randomUUID();

            @Override
            public UUID generateId() {
                return id;
            }
        };
    }
}
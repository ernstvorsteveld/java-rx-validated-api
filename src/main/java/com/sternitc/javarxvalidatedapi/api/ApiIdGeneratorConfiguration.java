package com.sternitc.javarxvalidatedapi.api;

import com.sternitc.javarxvalidatedapi.api.create.IdGeneratorSimple;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.IdGenerator;

@Configuration
public class ApiIdGeneratorConfiguration {

    @Bean
    public IdGenerator idGenerator() {
        return new IdGeneratorSimple();
    }

}

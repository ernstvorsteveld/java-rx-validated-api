package com.sternitc.javarxvalidatedapi.api.create;

import org.springframework.util.IdGenerator;

import java.util.UUID;

public class IdGeneratorSimple implements IdGenerator {
    @Override
    public UUID generateId() {
        return UUID.randomUUID();
    }
}

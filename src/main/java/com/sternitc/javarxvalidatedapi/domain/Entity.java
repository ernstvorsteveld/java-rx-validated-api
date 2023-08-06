package com.sternitc.javarxvalidatedapi.domain;

import lombok.Data;

import java.util.UUID;

@Data
public abstract class Entity {

    private UUID Id;
}

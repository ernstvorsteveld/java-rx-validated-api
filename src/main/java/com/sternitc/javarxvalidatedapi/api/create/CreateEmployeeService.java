package com.sternitc.javarxvalidatedapi.api.create;

import java.util.UUID;

public interface CreateEmployeeService {
    String create(CreateEmployeeCommand command);
}

package com.sternitc.javarxvalidatedapi.api.dao;

import com.sternitc.javarxvalidatedapi.domain.Employee;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface EmployeeDao {
    String create(Employee employee);

    Employee get(String id);

    Employee update(String id, Employee employee);
}

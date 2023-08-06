package com.sternitc.javarxvalidatedapi.api.dao;

import com.sternitc.javarxvalidatedapi.domain.Employee;
import org.springframework.util.IdGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EmployeeDaoImpl implements EmployeeDao {

    private final IdGenerator idGenerator;

    private final Map<UUID, Employee> employees = new HashMap<>();

    public EmployeeDaoImpl(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public String create(Employee employee) {
        UUID uuid = idGenerator.generateId();
        employee.setId(uuid);
        employees.put(uuid, employee);
        return uuid.toString();
    }

    @Override
    public Employee get(String id) {
        return this.employees.get(UUID.fromString(id));
    }

    @Override
    public Employee update(String id, Employee employee) {
        this.employees.put(UUID.fromString(id), employee);
        return employee;
    }
}

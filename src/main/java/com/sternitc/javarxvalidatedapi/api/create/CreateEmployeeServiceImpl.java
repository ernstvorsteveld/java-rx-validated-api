package com.sternitc.javarxvalidatedapi.api.create;

import com.sternitc.javarxvalidatedapi.api.dao.EmployeeDao;
import com.sternitc.javarxvalidatedapi.domain.Employee;
import com.sternitc.javarxvalidatedapi.domain.Name;

import java.time.ZoneId;
import java.util.Date;

public class CreateEmployeeServiceImpl implements CreateEmployeeService {
    private final EmployeeDao employeeDao;

    public CreateEmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public String create(CreateEmployeeCommand command) {
        Name.NameBuilder nameBuilder = new Name.NameBuilder(command.getLast());
        nameBuilder.first(command.getFirst());

        Date date = Date.from(command.getDateOfBirth().atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
        Employee employee = new Employee(nameBuilder.build(), date);
        return employeeDao.create(employee);
    }
}

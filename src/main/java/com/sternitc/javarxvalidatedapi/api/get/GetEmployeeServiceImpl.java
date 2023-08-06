package com.sternitc.javarxvalidatedapi.api.get;

import com.sternitc.javarxvalidatedapi.api.dao.EmployeeDao;
import com.sternitc.javarxvalidatedapi.domain.Employee;

public class GetEmployeeServiceImpl implements GetEmployeeService {
    private final EmployeeDao employeeDao;

    public GetEmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public Employee get(String id) {
        return this.employeeDao.get(id);
    }
}

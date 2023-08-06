package com.sternitc.javarxvalidatedapi.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sternitc.javarxvalidatedapi.api.create.CreateEmployeeService;
import com.sternitc.javarxvalidatedapi.api.create.CreateEmployeeServiceImpl;
import com.sternitc.javarxvalidatedapi.api.dao.EmployeeDao;
import com.sternitc.javarxvalidatedapi.api.dao.EmployeeDaoImpl;
import com.sternitc.javarxvalidatedapi.api.get.GetEmployeeService;
import com.sternitc.javarxvalidatedapi.api.get.GetEmployeeServiceImpl;
import com.sternitc.javarxvalidatedapi.api.patch.PatchEmployeeService;
import com.sternitc.javarxvalidatedapi.api.patch.PatchEmployeeServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.IdGenerator;

@Configuration
public class ApiConfiguration {

    @Bean
    public GetEmployeeService getEmployeeService(EmployeeDao employeeDao) {
        return new GetEmployeeServiceImpl(employeeDao);
    }

    @Bean
    public CreateEmployeeService createEmployeeService(EmployeeDao employeeDao) {
        return new CreateEmployeeServiceImpl(employeeDao);
    }

    @Bean
    public EmployeeDao employeeDao(IdGenerator idGenerator) {
        return new EmployeeDaoImpl(idGenerator);
    }

    @Bean
    public PatchEmployeeService patchEmployeeService(
            ObjectMapper objectMapper,
            EmployeeDao employeeDao) {
        return new PatchEmployeeServiceImpl(objectMapper, employeeDao);
    }

}

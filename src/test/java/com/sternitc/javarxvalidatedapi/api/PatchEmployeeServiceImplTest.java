package com.sternitc.javarxvalidatedapi.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sternitc.javarxvalidatedapi.api.dao.EmployeeDao;
import com.sternitc.javarxvalidatedapi.api.patch.PatchEmployeeService;
import com.sternitc.javarxvalidatedapi.api.patch.PatchEmployeeServiceImpl;
import com.sternitc.javarxvalidatedapi.domain.Employee;
import com.sternitc.javarxvalidatedapi.domain.Name;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

class PatchEmployeeServiceImplTest {

    @Test
    public void should_path_employee() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        EmployeeDao employeeDao = new EmployeeDao() {
            @Override
            public String create(Employee employee) {
                return null;
            }

            @Override
            public Employee get(String id) {
                return null;
            }

            @Override
            public Employee update(String id, Employee employee) {
                return null;
            }
        };
        PatchEmployeeService service = new PatchEmployeeServiceImpl(mapper, employeeDao);

        Employee employee = new Employee(new Name("John", "", "Doe"), new Date());
        employee.setId(UUID.randomUUID());
        String patchString = "[{ \"op\": \"replace\", \"path\": \"/name/first\", \"value\": \"changed\" }]";
        service.patch(employee, patchString.getBytes());
    }

}
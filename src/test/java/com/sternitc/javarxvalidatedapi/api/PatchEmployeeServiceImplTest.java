package com.sternitc.javarxvalidatedapi.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.github.fge.jsonpatch.JsonPatch;
import com.sternitc.javarxvalidatedapi.api.dao.EmployeeDao;
import com.sternitc.javarxvalidatedapi.api.get.GetEmployeeService;
import com.sternitc.javarxvalidatedapi.domain.Employee;
import com.sternitc.javarxvalidatedapi.domain.Name;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PatchEmployeeServiceImplTest {

    @Test
    public void should_path_employee() throws IOException {
        GetEmployeeService getEmployeeService = new GetEmployeeService() {
            @Override
            public Employee get(String id) {
                Employee employee = new Employee(new Name("John", "", "Doe"), new Date());
                employee.setId(UUID.randomUUID());
                return employee;
            }
        };
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
        PatchEmployeeService service = new PatchEmployeeServiceImpl(getEmployeeService, mapper, employeeDao);

        String patchString = "[{ \"op\": \"replace\", \"path\": \"/name/first\", \"value\": \"changed\" }]";
        service.patch(UUID.randomUUID().toString(), patchString);
    }

}
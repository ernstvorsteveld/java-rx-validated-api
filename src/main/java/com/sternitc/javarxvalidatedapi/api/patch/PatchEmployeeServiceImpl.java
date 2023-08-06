package com.sternitc.javarxvalidatedapi.api.patch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.sternitc.javarxvalidatedapi.api.dao.EmployeeDao;
import com.sternitc.javarxvalidatedapi.api.get.GetEmployeeService;
import com.sternitc.javarxvalidatedapi.domain.Employee;
import lombok.AllArgsConstructor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@AllArgsConstructor
public class PatchEmployeeServiceImpl implements PatchEmployeeService {

    private final ObjectMapper mapper;
    private final EmployeeDao employeeDao;

    @Override
    public Employee patch(Employee employee, String patch) {
        Employee patchedEmployee = applyPatch(toJsonPatch(patch), employee);
        return employeeDao.update(employee.getId().toString(), patchedEmployee);
    }

    private JsonPatch toJsonPatch(String patch) {
        try {
            InputStream in = new ByteArrayInputStream(patch.getBytes());
            return mapper.readValue(in, JsonPatch.class);
        } catch (JsonProcessingException e) {
            throw new PatchException(e);
        } catch (IOException e) {
            throw new PatchException(e);
        }
    }

    private Employee applyPatch(JsonPatch patch, Employee target) {
        try {
            JsonNode jsonNode = mapper.convertValue(target, JsonNode.class);
            JsonNode patched = patch.apply(jsonNode);
            return mapper.treeToValue(patched, Employee.class);
        } catch (JsonProcessingException e) {
            throw new PatchException(e);
        } catch (JsonPatchException e) {
            throw new PatchException(e);
        }
    }

    public static final class PatchException extends RuntimeException {
        public PatchException(Exception e) {
            super(e);
        }
    }

}

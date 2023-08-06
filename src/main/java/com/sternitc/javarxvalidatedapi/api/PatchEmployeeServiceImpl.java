package com.sternitc.javarxvalidatedapi.api;

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

    private final GetEmployeeService getEmployeeService;
    private final ObjectMapper mapper;
    private final EmployeeDao employeeDao;

    @Override
    public Employee patch(String id, String patch) {
        Employee employee = this.getEmployeeService.get(id);
        Employee patchedEmployee = applyPatch(toJsonPatch(patch), employee);
        return employeeDao.update(id, patchedEmployee);
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

    @Override
    public Employee lookup(String id) {
        return getEmployeeService.get(id);
    }

    @Override
    public Employee asObject(JsonNode employee) {
        return mapper.convertValue(employee, Employee.class);
    }

}

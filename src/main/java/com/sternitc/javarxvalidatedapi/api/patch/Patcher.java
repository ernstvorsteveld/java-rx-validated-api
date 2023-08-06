package com.sternitc.javarxvalidatedapi.api.patch;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sternitc.javarxvalidatedapi.domain.Employee;


public class Patcher {

    private final String id;
    private final PatchEmployeeService service;
    private Employee object;
    private final JsonNode employee;
    private final ObjectMapper objectMapper;

    public Patcher(String id, PatchEmployeeService service, ObjectMapper objectMapper) {
        this.id = id;
        this.service = service;
        this.objectMapper = objectMapper;
        this.object = service.lookup(id);
        this.employee = this.objectMapper.convertValue(this.object, JsonNode.class);
    }

//    public Employee patch(JsonPatch patches) {
//        JsonStructure target = objectMapper.convertValue(this.object, JsonStructure.class);
//        JsonValue patchedEmployee = patches.apply(target);
//        this.object = objectMapper.convertValue(patchedEmployee, Employee.class);
//        return this.object;
//    }

    public Employee get() {
        return service.asObject(this.employee);
    }
}

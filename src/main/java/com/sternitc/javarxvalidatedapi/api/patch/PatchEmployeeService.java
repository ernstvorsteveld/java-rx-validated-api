package com.sternitc.javarxvalidatedapi.api.patch;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonpatch.JsonPatch;
import com.sternitc.javarxvalidatedapi.domain.Employee;

public interface PatchEmployeeService {
    Employee patch(String id, String patch);

    Employee lookup(String id);

    Employee asObject(JsonNode employee);
}

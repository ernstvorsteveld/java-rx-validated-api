package com.sternitc.javarxvalidatedapi.api.patch;

import com.sternitc.javarxvalidatedapi.domain.Employee;

public interface PatchEmployeeService {
    Employee patch(Employee employee, String patch);

}

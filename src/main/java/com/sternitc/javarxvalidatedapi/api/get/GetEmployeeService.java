package com.sternitc.javarxvalidatedapi.api.get;

import com.sternitc.javarxvalidatedapi.domain.Employee;
import reactor.core.publisher.Mono;

public interface GetEmployeeService {

    Employee get(String id);
}

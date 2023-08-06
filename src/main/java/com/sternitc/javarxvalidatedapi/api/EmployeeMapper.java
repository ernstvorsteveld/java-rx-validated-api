package com.sternitc.javarxvalidatedapi.api;

import com.sternitc.generated.model.*;
import com.sternitc.javarxvalidatedapi.api.create.CreateEmployeeCommand;
import com.sternitc.javarxvalidatedapi.domain.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(source = "firstName", target = "first")
    @Mapping(source = "lastName", target = "last")
    @Mapping(source = "dateOfBirth", target = "dateOfBirth")
    @Mapping(target = "middle", ignore = true)
    CreateEmployeeCommand toCreateCommand(CreateEmployeeRequest createEmployeeRequest);

    @Mapping(source = "name.first", target = "firstName")
    @Mapping(source = "name.last", target = "lastName")
    @Mapping(source = "dateOfBirth", target = "dateOfBirth")
    GetEmployeeResponse toApi(Employee employee);

    @Mapping(source = "name.first", target = "firstName")
    @Mapping(source = "name.last", target = "lastName")
    @Mapping(source = "dateOfBirth", target = "dateOfBirth")
    @Mapping(target = "name.middle", ignore = true)
    PatchEmployeeResponse toPatchResponse(Employee employee);
}

package com.sternitc.javarxvalidatedapi.api.create;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateEmployeeCommand {

    private String first;
    private String middle;
    private String last;
    private LocalDate dateOfBirth;
}

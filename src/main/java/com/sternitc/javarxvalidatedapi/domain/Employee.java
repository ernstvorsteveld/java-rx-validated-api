package com.sternitc.javarxvalidatedapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends Entity {

    private Name name;
    private Date dateOfBirth;

    @JsonIgnore
    public int getAge() {
        Period period = Period.between(
                dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                LocalDate.now());
        return period.getYears();
    }

    public void rename(Name name) {
        this.name = name;
    }
}

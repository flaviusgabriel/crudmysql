package com.example.crudmysql;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDto {
    private long id;
    private String firstName;
    private String secondName;
    private long departmentId;
}

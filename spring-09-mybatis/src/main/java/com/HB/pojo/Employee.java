package com.HB.pojo;

import lombok.Data;

import java.sql.Date;

@Data
public class Employee {
    private int empNo;
    private Date birthDate;
    private String firstName;
    private String lastName;
    private String gender;
    private Date hireDate;
}

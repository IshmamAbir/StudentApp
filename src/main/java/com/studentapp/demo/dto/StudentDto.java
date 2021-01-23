package com.studentapp.demo.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class StudentDto {

    private long studentId;
    private String studentName;
    private String studentAge;
    private String studentGender;
    private boolean enabled=true;
    private DepartmentDto departmentDto;

}

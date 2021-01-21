package com.studentapp.demo.dto;

import lombok.Data;

@Data
public class DepartmentDto {
    private long departmentId;
    private String departmentName;
    private String departmentCode;
    private boolean enabled=true;
}

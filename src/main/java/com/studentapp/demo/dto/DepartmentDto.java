package com.studentapp.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class DepartmentDto {
    private long departmentId;
    private String departmentName;
    private String departmentCode;
    private boolean enabled=true;
    private List<StudentDto> studentDtoList;
    private List<Long> studentIdList;
    private List<SubjectDto> subjectDtoList;
    private List<Long> subjectIdList;
}

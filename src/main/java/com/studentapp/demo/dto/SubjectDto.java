package com.studentapp.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SubjectDto {
    private long subjectId;
    private String subjectName;
    private String subjectCode;
    private double subjectCreditHour;
    private boolean enabled=true;

    private List<DepartmentDto> departmentDtoList;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<Long> departmentIdList;

}

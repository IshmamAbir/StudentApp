package com.studentapp.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long subjectId;
    @Column
    private String subjectName;
    @Column
    private String subjectCode;
    @Column
    private double subjectCreditHour;
    @Column
    private boolean enabled=true;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "department_subject",joinColumns = @JoinColumn(name = "subjectId"),inverseJoinColumns = @JoinColumn(name = "departmentId"))
    private List<Department> departmentList;



}

package com.studentapp.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long departmentId;
    @Column
    private String departmentName;
    @Column
    private String departmentCode;
    @Column
    private boolean enabled=true;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "student_department",joinColumns = @JoinColumn(name = "departmentId"),inverseJoinColumns = @JoinColumn(name = "studentId"))
    private List<Student> studentList;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "department_subject",joinColumns = @JoinColumn(name = "departmentId"),inverseJoinColumns = @JoinColumn(name = "subjectId"))
    private List<Subject> subjectList;

}

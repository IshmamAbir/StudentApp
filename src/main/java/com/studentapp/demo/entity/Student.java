package com.studentapp.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "student")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long studentId;
    @Column
    private String studentName;
    @Column
    private String studentAge;
    @Column
    private String studentGender;
    @Column
    private boolean enabled=true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "student_department",joinColumns = @JoinColumn(name = "studentId"),inverseJoinColumns = @JoinColumn(name = "departmentId"))
    private Department department;
}

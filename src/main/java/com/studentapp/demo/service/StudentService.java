package com.studentapp.demo.service;

import com.studentapp.demo.entity.Student;
import com.studentapp.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void saveStudent(Student student){
        studentRepository.save(student);
    }


}

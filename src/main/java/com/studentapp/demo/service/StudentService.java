package com.studentapp.demo.service;

import com.studentapp.demo.entity.Student;
import com.studentapp.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void saveStudent(Student student){
        studentRepository.save(student);
    }

    public List<Student> getAllStudent(){
        List<Student> studentList=studentRepository.findAllByEnabledTrue();
        return studentList;
    }

    public Student getStudentById(long student) {
        Optional<Student> optionalStudent=studentRepository.findById(student);
        Student student1=optionalStudent.get();
        return student1;
    }

    public void deleteById(long id) {
        Student student=getStudentById(id);
        student.setEnabled(false);
        saveStudent(student);
    }
}

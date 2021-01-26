package com.studentapp.demo.service;

import com.studentapp.demo.entity.Subject;
import com.studentapp.demo.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public void saveSubject(Subject subject){
        subjectRepository.save(subject);
    }

    public List<Subject> getAllSubject(){
        List<Subject> subjectList=subjectRepository.findAllByEnabledTrue();
        return subjectList;
    }

}

package com.studentapp.demo.service;

import com.studentapp.demo.entity.Subject;
import com.studentapp.demo.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public void saveSubject(Subject subject){
        subjectRepository.save(subject);

    }

}

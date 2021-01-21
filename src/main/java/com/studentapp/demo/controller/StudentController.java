package com.studentapp.demo.controller;

import com.studentapp.demo.dto.StudentDto;
import com.studentapp.demo.entity.Student;
import com.studentapp.demo.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/add")
    public String addNewStudent(Model model){
        model.addAttribute("studentDto",new StudentDto());
        model.addAttribute("genderList",getGenderList());
         return "student/add";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute StudentDto studentDto){
        Student student=new Student();
        BeanUtils.copyProperties(studentDto,student);
        studentService.saveStudent(student);

        return "redirect:/student/add";
    }




//    ----------------------    H e l p e r     M e t h o d     ----------------------------------

    private List<String> getGenderList() {
        List<String> genderList=new ArrayList<>();
        genderList.add("Male");
        genderList.add("Female");
        genderList.add("Other");

        return genderList;
    }
}

package com.studentapp.demo.controller;

import com.studentapp.demo.dto.DepartmentDto;
import com.studentapp.demo.dto.StudentDto;
import com.studentapp.demo.entity.Department;
import com.studentapp.demo.entity.Student;
import com.studentapp.demo.service.DepartmentService;
import com.studentapp.demo.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final DepartmentService departmentService;

    public StudentController(StudentService studentService, DepartmentService departmentService) {
        this.studentService = studentService;
        this.departmentService = departmentService;
    }

    @GetMapping("/add")
    public String addNewStudent(Model model){
        model.addAttribute("title","Add Student");
        model.addAttribute("studentDto",new StudentDto());
        model.addAttribute("genderList",getGenderList());
         return "student/add";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute StudentDto studentDto){
        Student student=null;
        if (studentDto!=null){
           student=studentService.getStudentById(studentDto.getStudentId());
        }else {
            student=new Student();
        }
        BeanUtils.copyProperties(studentDto,student);
        studentService.saveStudent(student);

        return "redirect:/student/show";
    }

    @GetMapping("/show")
    public String showAllStudent(Model model){
        model.addAttribute("studentDtoList",convertStudentListToDtoList(studentService.getAllStudent()));
         return "student/show";
    }

    @GetMapping("update/{id}")
    public String updateStudent(@PathVariable("id")long id,Model model){
        Student student=studentService.getStudentById(id);
        StudentDto studentDto=new StudentDto();
        BeanUtils.copyProperties(student,studentDto);
        model.addAttribute("studentDto",studentDto);
        model.addAttribute("genderList",getGenderList());

        return "student/add";
    }




//    ----------------------    H e l p e r     M e t h o d     ----------------------------------

    private List<String> getGenderList() {
        List<String> genderList=new ArrayList<>();
        genderList.add("Male");
        genderList.add("Female");
        genderList.add("Other");

        return genderList;
    }

    private List<StudentDto> convertStudentListToDtoList(List<Student> allStudent) {
        List<StudentDto> studentDtoList=new ArrayList<>();
        for (Student student:allStudent) {
            StudentDto studentDto=new StudentDto();
            BeanUtils.copyProperties(student,studentDto);

            if (student.getDepartment()==null){
                DepartmentDto departmentDto=new DepartmentDto();
                departmentDto.setDepartmentName("Not Assigned");
                studentDto.setDepartmentDto(departmentDto);
            }else {
                DepartmentDto departmentDto=new DepartmentDto();
                BeanUtils.copyProperties(student.getDepartment(),departmentDto);
                studentDto.setDepartmentDto(departmentDto);
            }

            /*DepartmentDto departmentDto=new DepartmentDto();
            if (student.getDepartment()==null){
                departmentDto.setDepartmentName("Not Assigned");
                studentDto.setDepartmentDto(departmentDto);
            }else {
                studentDto.setDepartmentDto(departmentDto);
                departmentDto.setDepartmentName(student.getDepartment().getDepartmentName());
                departmentDto.setDepartmentCode(student.getDepartment().getDepartmentCode());
            }
            BeanUtils.copyProperties(student.getDepartment(),departmentDto);*/


            studentDtoList.add(studentDto);

        }
        return studentDtoList;
    }


}

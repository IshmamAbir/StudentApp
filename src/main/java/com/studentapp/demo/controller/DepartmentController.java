package com.studentapp.demo.controller;

import com.studentapp.demo.dto.DepartmentDto;
import com.studentapp.demo.dto.StudentDto;
import com.studentapp.demo.dto.SubjectDto;
import com.studentapp.demo.entity.Department;
import com.studentapp.demo.entity.Student;
import com.studentapp.demo.entity.Subject;
import com.studentapp.demo.service.DepartmentService;
import com.studentapp.demo.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;
    private final StudentService studentService;

    public DepartmentController(DepartmentService departmentService, StudentService studentService) {
        this.departmentService = departmentService;
        this.studentService = studentService;
    }

    @GetMapping("/add")
    public String addNewDepartment(Model model){
        model.addAttribute("title","Add Department");
        model.addAttribute("departmentDto",new DepartmentDto());
        List<Department> departmentList=departmentService.getAllDepartment();

        model.addAttribute("departmentDtoList",convertDepartmentListToDtoList(departmentList));
        return "department/addAndShow";
    }



    @PostMapping("/save")
    public String saveDepartment(@ModelAttribute DepartmentDto departmentDto){
        Department department=new Department();
        BeanUtils.copyProperties(departmentDto,department);
        departmentService.saveDepartment(department);

        return "redirect:/department/add";
    }


    @GetMapping("/assignDepartment")
    public String assignDepartmentToStudent(Model model){
        model.addAttribute("title","Assign to Department");
        model.addAttribute("departmentDto",new DepartmentDto());
        model.addAttribute("departmentListDto",convertDepartmentListToDtoList(departmentService.getAllDepartment()));
        model.addAttribute("studentListDto",getAllNotAssignedStudent());
        return "department/assignDepartment";
    }
    @PostMapping("/saveAssign")
    public String saveAssignedStudent(@ModelAttribute DepartmentDto departmentDto){
        Department department=departmentService.findDepartmentById(departmentDto.getDepartmentId());

        List<Student> studentList=new ArrayList<>();
        for (long student:departmentDto.getStudentIdList()) {
            Student student1=studentService.getStudentById(student);
            studentList.add(student1);
        }
        department.setStudentList(studentList);
        departmentService.saveDepartment(department);

        return "redirect:/department/assignDepartment";
    }

    @GetMapping("/details")
    public String showDetails(Model model){
        model.addAttribute("title","View Details");
        model.addAttribute("departmentListDto",convertDepartmentListToDtoList(departmentService.getAllDepartment()));

        return "department/userDetails";
    }

    @GetMapping(value = "/getStudentAndSubject/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DepartmentDto getStudentAndSubjectDetails(@PathVariable("id")long departmentId){
        Department department=departmentService.findDepartmentById(departmentId);

        return convertDepartmentToDto(department);
    }



//    -----------------   H e l p e r     M e t h o d   ------------------------------------



    private List<DepartmentDto> convertDepartmentListToDtoList(List<Department> departmentList) {
        List<DepartmentDto> departmentDtoList=new ArrayList<>();
        for (Department department:departmentList) {
            DepartmentDto departmentDto=new DepartmentDto();
            BeanUtils.copyProperties(department,departmentDto);
            departmentDtoList.add(departmentDto);
        }
        return departmentDtoList;
    }

    private List<StudentDto> convertStudentListToDtoList(List<Student> allStudent) {
        List<StudentDto> studentDtoList=new ArrayList<>();
        for (Student student:allStudent) {
            StudentDto studentDto=new StudentDto();
            BeanUtils.copyProperties(student,studentDto);
            studentDtoList.add(studentDto);
        }
        return studentDtoList;
    }

    private List<StudentDto> getAllNotAssignedStudent(){
        List<Student> studentList=studentService.getAllStudent();
        List<StudentDto> studentDtoList=new ArrayList<>();
        for (Student student:studentList) {
            if (student.getDepartment()==null){
                StudentDto studentDto=new StudentDto();
                BeanUtils.copyProperties(student,studentDto);
                studentDtoList.add(studentDto);
            }
        }
        return studentDtoList;
    }

    private List<SubjectDto> convertSubjectListToDtoList(List<Subject> subjectList){
        List<SubjectDto> subjectDtoList=new ArrayList<>();
        for (Subject subject:subjectList) {
            SubjectDto subjectDto=new SubjectDto();
            BeanUtils.copyProperties(subject,subjectDto);
            subjectDtoList.add(subjectDto);
        }
        return subjectDtoList;
    }


    private DepartmentDto convertDepartmentToDto(Department department) {
        DepartmentDto departmentDto=new DepartmentDto();
        BeanUtils.copyProperties(department,departmentDto);

        List<Student> studentList=department.getStudentList();
        departmentDto.setStudentDtoList(convertStudentListToDtoList(studentList));

        List<Subject> subjectList=department.getSubjectList();
        departmentDto.setSubjectDtoList(convertSubjectListToDtoList(subjectList));

        return departmentDto;
    }


}

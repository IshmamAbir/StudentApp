package com.studentapp.demo.controller;

import com.studentapp.demo.dto.DepartmentDto;
import com.studentapp.demo.dto.SubjectDto;
import com.studentapp.demo.entity.Department;
import com.studentapp.demo.entity.Subject;
import com.studentapp.demo.service.DepartmentService;
import com.studentapp.demo.service.SubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/subject")
public class SubjectController {
    private final DepartmentService departmentService;
    private final SubjectService subjectService;

    public SubjectController(DepartmentService departmentService, SubjectService subjectService) {
        this.departmentService = departmentService;
        this.subjectService = subjectService;
    }

    @GetMapping("/add")
    public String addSubject(Model model){
        model.addAttribute("title", "Add New Subject");
//        model.addAttribute("subjectDto",new SubjectDto());
        model.addAttribute("departmentListDto",getAllDepartment());

        return "subject/add";
    }

    @PostMapping("/save")
    @ResponseBody
    public SubjectDto saveSubject(@RequestBody SubjectDto subjectDto){
        Subject subject=new Subject();
        BeanUtils.copyProperties(subjectDto,subject);
        subject.setDepartmentList(this.getSelectedDepartment(subjectDto.getDepartmentIdList()));
        subjectService.saveSubject(subject);

        return subjectDto;
    }

    @GetMapping(value="/show",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<SubjectDto> showAllSubject(){
        List<Subject> subjectList=subjectService.getAllSubject();
        List<SubjectDto> subjectDtoList=convertSubjectListToDtoList(subjectList);
        return subjectDtoList;
    }



// -------------------------  H e l p e r    M e t h o d  ---------------------------

    private List<DepartmentDto> getAllDepartment() {
        List<Department> departmentList=departmentService.getAllDepartment();
        List<DepartmentDto> departmentDtoList=new ArrayList<>();
        for (Department department:departmentList) {
            DepartmentDto departmentDto=new DepartmentDto();
            BeanUtils.copyProperties(department,departmentDto);
            departmentDtoList.add(departmentDto);
        }
        return departmentDtoList;
    }

    private List<Department> getSelectedDepartment(List<Long> departmentIdList){
        List<Department> departmentList = new ArrayList<>();
        for (long departmentId : departmentIdList) {
            Department department = departmentService.findDepartmentById(departmentId);
            departmentList.add(department);
        }
        return departmentList;
    }

    private List<SubjectDto> convertSubjectListToDtoList(List<Subject> subjectList) {
        List<SubjectDto> subjectDtoList=new ArrayList<>();
        for (Subject subject:subjectList) {
            SubjectDto subjectDto=new SubjectDto();
            BeanUtils.copyProperties(subject,subjectDto);
            subjectDtoList.add(subjectDto);
        }
        return subjectDtoList;
    }
}

package com.studentapp.demo.controller;

import com.studentapp.demo.dto.DepartmentDto;
import com.studentapp.demo.dto.StudentDto;
import com.studentapp.demo.entity.Department;
import com.studentapp.demo.entity.Student;
import com.studentapp.demo.service.DepartmentService;
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
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
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
}

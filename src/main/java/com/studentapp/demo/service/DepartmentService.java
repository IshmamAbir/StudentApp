package com.studentapp.demo.service;

import com.studentapp.demo.entity.Department;
import com.studentapp.demo.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public void saveDepartment(Department department){
        departmentRepository.save(department);
    }

    public Department findDepartmentById(long id){
        Optional<Department> optionalDepartment=departmentRepository.findById(id);
        Department department=optionalDepartment.get();
        return department;
    }

    public List<Department> getAllDepartment(){
        List<Department> departmentList=departmentRepository.findAllByEnabledTrue();
        return departmentList;
    }
}

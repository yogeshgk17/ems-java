package com.fyrasoft.ems.learning.controller;

import com.fyrasoft.ems.learning.Service.EmployeeService;
import com.fyrasoft.ems.learning.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/employee")
@RestController
public class EmployeeController {

    @Autowired
    public EmployeeService service;

    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee EmployeeDetails){
        return service.save(EmployeeDetails);
    }
//    @GetMapping("/all")
    @RequestMapping()
    public List<Employee>getAllEmployees(){
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public Employee getById(@PathVariable Integer id){
        return service.getById(id);
    }
    @PutMapping("/put/{id}")
    public Employee updateEmployee(@PathVariable Integer id,@RequestBody Employee employeeDetails){
        employeeDetails.setId(id);
        return service.save(employeeDetails);
    }
    @PatchMapping("/patch/{id}")
    public Employee updateEmployees(@PathVariable Integer id,@RequestBody Employee employeeDetails){
        Employee excisting = service.getById(id);
        if(excisting !=null){
            if(employeeDetails.getName()!=null){
                excisting.setName(employeeDetails.getName());
            }
            if(employeeDetails.getAddress()!=null){
                excisting.setAddress(employeeDetails.getAddress());
            }
            if(employeeDetails.getDepartment()!=null){
                excisting.setDepartment(employeeDetails.getDepartment());
            }
            return service.save(excisting);
        }
        return null;
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
        service.delete(id);
        return "Deleteted";
    }
}

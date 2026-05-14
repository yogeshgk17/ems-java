package com.fyrasoft.ems.learning.Service;



import com.fyrasoft.ems.learning.entity.Employee;
import com.fyrasoft.ems.learning.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    public EmployeeRepository repository;

    @Transactional
    public Employee save (Employee EmployeeDetails){
        return repository.save(EmployeeDetails);
    }

    public Employee updateEmployee ( Employee e){
        Employee updated = new Employee();
        updated.setId(e.getId());
        updated.setName(e.getName());
        updated.setDepartment(e.getDepartment());
        updated.setAddress(e.getAddress());
        return repository.save(updated);
    }

    public List<Employee>getAll(){
        return repository.findAll();
    }

    public Optional<Employee> getById(Integer id){
        return repository.findById(id);
    }

    @Transactional
    public void delete(Integer id){
        repository.deleteById(id);
    }


}

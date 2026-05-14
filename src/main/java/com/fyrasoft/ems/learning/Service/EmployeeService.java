package com.fyrasoft.ems.learning.Service;



import com.fyrasoft.ems.learning.entity.Employee;
import com.fyrasoft.ems.learning.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    public EmployeeRepository repository;

    @Transactional
    public Employee save (Employee EmployeeDetails){
        return repository.save(EmployeeDetails);
    }

    public List<Employee>getAll(){
        return repository.findAll();
    }

    public Employee getById(Integer id){
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public void delete(Integer id){
        repository.deleteById(id);
    }


}

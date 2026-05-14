package com.fyrasoft.ems.learning.controller;

import com.fyrasoft.ems.learning.Service.EmployeeService;
import com.fyrasoft.ems.learning.entity.Address;
import com.fyrasoft.ems.learning.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    @RequestMapping("/all")
    public List<Employee>getAllEmployees(){
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public Employee getById(@PathVariable Integer id){
        return service.getById(id).orElse(null);
    }
    @PutMapping("/put/{id}")
    public Employee updateEmployee(@PathVariable Integer id,@RequestBody Employee employeeDetails){
        employeeDetails.setId(id);
        return service.updateEmployee(employeeDetails);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Employee> patchEmployee(
            @PathVariable Integer id,
            @RequestBody Map<String, Object> updates) {

        Optional<Employee> optionalEmployee = service.getById(id);

        if (optionalEmployee.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Employee employee = optionalEmployee.get();

        // Update Employee fields
        if (updates.containsKey("name")) {
            employee.setName((String) updates.get("name"));
        }

        if (updates.containsKey("department")) {
            employee.setDepartment((String) updates.get("department"));
        }

        // Update Address fields
        if (updates.containsKey("address")) {

            Map<String, Object> addressMap =
                    (Map<String, Object>) updates.get("address");

            Address address = employee.getAddress();

            if (address == null) {
                address = new Address();
            }

            if (addressMap.containsKey("city")) {
                address.setCity((String) addressMap.get("city"));
            }

            if (addressMap.containsKey("state")) {
                address.setState((String) addressMap.get("state"));
            }

            if (addressMap.containsKey("pincode")) {
                address.setPincode((Integer) addressMap.get("pincode"));
            }

            employee.setAddress(address);
        }

        Employee updatedEmployee = service.save(employee);

        return ResponseEntity.ok(updatedEmployee);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
        service.delete(id);
        return "Deleteted";
    }
}

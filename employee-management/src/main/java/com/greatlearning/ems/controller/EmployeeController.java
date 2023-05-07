package com.greatlearning.ems.controller;

import com.greatlearning.ems.entity.Employee;
import com.greatlearning.ems.model.EmployeeRequest;
import com.greatlearning.ems.model.EmployeeResponse;
import com.greatlearning.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ems")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @GetMapping("/employees")
    public List<EmployeeResponse> getEmployees(){
        return employeeService.getAllEmployees();
    }
    @GetMapping("/employee/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable long id){
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/employee")
    public EmployeeResponse saveEmployee(@RequestBody EmployeeRequest request){
        request.getId();
        return employeeService.saveEmployee(request);
    }
    @PutMapping("/employee")
    public EmployeeResponse editEmployee(@RequestBody EmployeeRequest request){
        return employeeService.updateEmployee(request);
    }
    @DeleteMapping("/employee/{id}")
    public EmployeeResponse deleteEmployee(@PathVariable long id){
        employeeService.deleteEmployeeById(id);
        return  EmployeeResponse.builder().id(id).build();
    }

}

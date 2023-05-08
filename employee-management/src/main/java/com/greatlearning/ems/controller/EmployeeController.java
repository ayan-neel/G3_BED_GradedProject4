package com.greatlearning.ems.controller;

import com.greatlearning.ems.entity.Employee;
import com.greatlearning.ems.model.EmployeeRequest;
import com.greatlearning.ems.model.EmployeeResponse;
import com.greatlearning.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ems")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeResponse>> getEmployees(){

        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }
    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable long id){
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    @PostMapping("/employee")
    public ResponseEntity<EmployeeResponse> saveEmployee(@RequestBody EmployeeRequest request){
        return  new ResponseEntity<>(employeeService.saveEmployee(request),HttpStatus.CREATED);
    }
    @PutMapping("/employee")
    public ResponseEntity<EmployeeResponse> editEmployee(@RequestBody EmployeeRequest request){
        return new ResponseEntity<>(employeeService.updateEmployee(request),HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<EmployeeResponse> deleteEmployee(@PathVariable long id){
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>(EmployeeResponse.builder().id(id).build(),HttpStatus.OK);
    }

}

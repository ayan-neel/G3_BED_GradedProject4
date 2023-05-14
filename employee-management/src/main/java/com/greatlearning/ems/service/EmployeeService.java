package com.greatlearning.ems.service;

import com.greatlearning.ems.entity.Employee;
import com.greatlearning.ems.model.EmployeeRequest;
import com.greatlearning.ems.model.EmployeeResponse;

import java.util.List;

public interface EmployeeService {

    EmployeeResponse getEmployeeById(long id);
    List<EmployeeResponse>getAllEmployees(String sort);
    EmployeeResponse saveEmployee(EmployeeRequest request);
    EmployeeResponse updateEmployee(EmployeeRequest request);
    void deleteEmployeeById(long id);

    List<EmployeeResponse> getEmployeesByFirstName(String firstName);

}

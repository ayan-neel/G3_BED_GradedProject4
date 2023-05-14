package com.greatlearning.ems.service.impl;

import com.greatlearning.ems.entity.Employee;
import com.greatlearning.ems.mapper.EmployeeMapper;
import com.greatlearning.ems.model.EmployeeRequest;
import com.greatlearning.ems.model.EmployeeResponse;
import com.greatlearning.ems.repository.EmployeeRepository;
import com.greatlearning.ems.service.EmployeeService;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Override
    public EmployeeResponse getEmployeeById(long id) {
        Optional<Employee> emp = employeeRepository.findById(id);
        if(emp.isPresent())
            return EmployeeMapper.INSTANCE.employeeToEmployeeResponse(emp.get());
        return new EmployeeResponse();
    }

    @Override
    public List<EmployeeResponse> getAllEmployees(String sort) {
        if(StringUtils.isNullOrEmpty(sort)) {
            return employeeRepository.findAll().stream()
                    .map(EmployeeMapper.INSTANCE::employeeToEmployeeResponse).collect(Collectors.toList());
        }
       else if(sort.equalsIgnoreCase("desc"))
            return employeeRepository.findAll(Sort.by(Sort.Direction.DESC,"firstName")).stream()
                    .map(EmployeeMapper.INSTANCE::employeeToEmployeeResponse).collect(Collectors.toList());
       else if(sort.equalsIgnoreCase("asc"))
           return employeeRepository.findAll(Sort.by(Sort.Direction.ASC,"firstName")).stream()
                   .map(EmployeeMapper.INSTANCE::employeeToEmployeeResponse).collect(Collectors.toList());

       else {
            throw new RuntimeException("invalid sort parameter");
        }
    }

    @Override
    public EmployeeResponse saveEmployee(EmployeeRequest request) {
        Employee employee = EmployeeMapper.INSTANCE.employeeRequestToEmployee(request);
        System.out.println("Employee"+employee);
        return EmployeeMapper.INSTANCE.employeeToEmployeeResponse(employeeRepository.save(employee));
    }

    @Override
    public EmployeeResponse updateEmployee(EmployeeRequest request) {
        Employee employee = EmployeeMapper.INSTANCE.employeeRequestToEmployee(request);
        return EmployeeMapper.INSTANCE.employeeToEmployeeResponse(employeeRepository.save(employee));
    }

    @Override
    public void deleteEmployeeById(long id) {
        employeeRepository.deleteById(id);

    }

    @Override
    public List<EmployeeResponse> getEmployeesByFirstName(String firstName) {
        return employeeRepository.findEmployeeByFirstName(firstName).get().stream()
                .map(EmployeeMapper.INSTANCE::employeeToEmployeeResponse).collect(Collectors.toList());
    }
}

package com.greatlearning.ems.mapper;

import com.greatlearning.ems.entity.Employee;
import com.greatlearning.ems.model.EmployeeRequest;
import com.greatlearning.ems.model.EmployeeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    Employee employeeRequestToEmployee(EmployeeRequest request);
    EmployeeResponse employeeToEmployeeResponse(Employee employee);
}

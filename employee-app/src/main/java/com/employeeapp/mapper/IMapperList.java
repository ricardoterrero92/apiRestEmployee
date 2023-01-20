package com.employeeapp.mapper;

import com.employeeapp.model.Employee;
import com.employeeapp.model.EmployeesListAllResponse;
import com.employeeapp.persistence.entity.Employees;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.Converter;
import java.util.List;


@Mapper(componentModel = "spring", implementationPackage = "com.employeeapp.mapper.implementation")
public interface IMapperList {

    List<Employees> mapToInner(final List<Employee> from);
    List<EmployeesListAllResponse> mapToOuter(final List<Employees> from);

}

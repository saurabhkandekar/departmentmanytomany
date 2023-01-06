package com.crossasyst.deptempmanytomany.mapper;

import com.crossasyst.deptempmanytomany.entity.DepartmentEntity;
import com.crossasyst.deptempmanytomany.entity.EmployeeEntity;
import com.crossasyst.deptempmanytomany.model.DepartmentRequest;
import com.crossasyst.deptempmanytomany.model.EmployeeRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeEntity modelToEntity(EmployeeRequest employeeRequest);

    EmployeeRequest entityToModel(EmployeeEntity employeeEntity);

    List<EmployeeRequest> entityToModels(List<EmployeeEntity> employeeEntity);


}

package com.crossasyst.deptempmanytomany.mapper;

import com.crossasyst.deptempmanytomany.entity.DepartmentEntity;
import com.crossasyst.deptempmanytomany.model.DepartmentRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentEntity deptModelToEntity(DepartmentRequest DepartmentRequest);

    DepartmentRequest deptEntityToModel(DepartmentEntity departmentEntity);

    List<DepartmentRequest> deptEntityToModels(List<DepartmentEntity> DepartmentEntity);

}

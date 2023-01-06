package com.crossasyst.deptempmanytomany.model;

import com.crossasyst.deptempmanytomany.entity.DepartmentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {

    private String firstName;
    private String lastName;
    private List<DepartmentRequest> department;
}

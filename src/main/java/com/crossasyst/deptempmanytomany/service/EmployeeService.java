package com.crossasyst.deptempmanytomany.service;

import com.crossasyst.deptempmanytomany.entity.DepartmentEntity;
import com.crossasyst.deptempmanytomany.entity.EmployeeEntity;
import com.crossasyst.deptempmanytomany.mapper.DepartmentMapper;
import com.crossasyst.deptempmanytomany.mapper.EmployeeMapper;
import com.crossasyst.deptempmanytomany.model.DepartmentRequest;
import com.crossasyst.deptempmanytomany.model.EmployeeRequest;
import com.crossasyst.deptempmanytomany.model.EmployeeResponse;
import com.crossasyst.deptempmanytomany.repository.DepartmentRepository;
import com.crossasyst.deptempmanytomany.repository.EmployeeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeMapper employeeMapper;
    private final DepartmentMapper departmentMapper;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, EmployeeMapper employeeMapper, DepartmentMapper departmentMapper) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.employeeMapper = employeeMapper;
        this.departmentMapper = departmentMapper;
    }

    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        EmployeeEntity employeeEntity = employeeMapper.modelToEntity(employeeRequest);
        employeeRepository.save(employeeEntity);

        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setEmployeeId(employeeEntity.getEmployeeId());
        log.info("Create new Employee");
        return employeeResponse;
    }

    public List<EmployeeRequest> getAllEmployee() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        List<EmployeeRequest> employeeRequests = employeeMapper.entityToModels(employeeEntities);
        log.info("GetAll the all data of employees");
        return employeeRequests;
    }

    public EmployeeRequest getById(Long employeeId) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        EmployeeRequest employeeRequest = employeeMapper.entityToModel(employeeEntity);
        log.info("get the data EmployeeId" +employeeId);
        return employeeRequest;
    }

    public EmployeeRequest updateEmployee(Long employeeId, EmployeeRequest employeeRequest) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        EmployeeEntity employeeEntityOne = employeeMapper.modelToEntity(employeeRequest);
        employeeEntityOne.setEmployeeId(employeeEntity.getEmployeeId());
        employeeRepository.save(employeeEntity);
        log.info("Employee data is updated");
        return employeeRequest;
    }

    public void deleteById(Long employeeId) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);
        if (employeeEntity.isPresent()) {
            employeeRepository.deleteById(employeeId);
        } else {
            log.info("employeeId" + employeeId + "is not present in your database");
        }

    }

    public List<DepartmentRequest> findDepartmentByEmployeeId(Long employeeId) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        List<DepartmentEntity> departmentEntity = employeeEntity.getDepartment();

        List<DepartmentRequest> departmentRequest = departmentMapper.deptEntityToModels(departmentEntity);
        log.info("This Is All Department Of EmployeeId " +employeeId);
        return departmentRequest;
    }
//
//    public void usingEmployeeIdDeleteSpecificDepartmentId(Long employeeId, Long departmentId) {
//        EmployeeEntity employeeEntity =employeeRepository.findById(employeeId).get();
//        List<DepartmentEntity> departmentEntityList =employeeEntity.getDepartment();
//      log.info("EmployeeId"+ employeeId +"It's departmentId"+ departmentId +"has been deleted");
//        departmentEntityList.remove(departmentId);
//        employeeRepository.save(employeeEntity);
//
//    }
    public EmployeeRequest addDepartmentToEmployee(Long empId, Long deptId) {
        EmployeeEntity employeeEntity = employeeRepository.findById(empId).get();
        DepartmentEntity departmentEntity = departmentRepository.findById(deptId).get();
        List<DepartmentEntity> departments = employeeEntity.getDepartment();
        departments.add(departmentEntity);
        employeeRepository.save(employeeEntity);
        log.info("");
        return employeeMapper.entityToModel(employeeEntity);

    }

    public EmployeeRequest removeDepartmentFromEmployee(Long empId, Long deptId) {
        EmployeeEntity employeeEntity = employeeRepository.findById(empId).get();
        DepartmentEntity departmentEntity = departmentRepository.findById(deptId).get();
        List<DepartmentEntity> departments = employeeEntity.getDepartment();
        departments.remove(departmentEntity);
        employeeRepository.save(employeeEntity);
        EmployeeRequest employeeRequest = employeeMapper.entityToModel(employeeEntity);
        return employeeRequest;

    }
}

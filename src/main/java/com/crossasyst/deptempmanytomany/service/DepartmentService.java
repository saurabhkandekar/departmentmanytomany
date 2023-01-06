package com.crossasyst.deptempmanytomany.service;

import com.crossasyst.deptempmanytomany.entity.DepartmentEntity;
import com.crossasyst.deptempmanytomany.mapper.DepartmentMapper;
import com.crossasyst.deptempmanytomany.model.DepartmentRequest;
import com.crossasyst.deptempmanytomany.model.DepartmentResponse;
import com.crossasyst.deptempmanytomany.repository.DepartmentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public DepartmentService(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;

        this.departmentMapper = departmentMapper;
    }

    public DepartmentResponse createDepartment(DepartmentRequest departmentRequest) {
        DepartmentEntity departmentEntity = departmentMapper.deptModelToEntity(departmentRequest);
        departmentRepository.save(departmentEntity);

        DepartmentResponse departmentResponse = new DepartmentResponse();
        departmentResponse.setDepartmentId(departmentEntity.getDepartmentId());
        log.info("Create new one department");
        return departmentResponse;

    }

    public List<DepartmentRequest> getAllDepartment() {
        List<DepartmentEntity> departmentEntity = departmentRepository.findAll();
        List<DepartmentRequest> departmentRequests = departmentMapper.deptEntityToModels(departmentEntity);
        log.info("Get All The Department");
        return departmentRequests;
    }

    public DepartmentRequest getById(Long departmentId) {
        DepartmentEntity departmentEntity = departmentRepository.findById(departmentId).get();
        DepartmentRequest departmentRequest = departmentMapper.deptEntityToModel(departmentEntity);
        log.info("Get data ById");
        return departmentRequest;
    }

    public DepartmentRequest updateDepartment(Long departmentId, DepartmentRequest departmentRequest) {
        DepartmentEntity departmentEntity = departmentRepository.findById(departmentId).get();
        DepartmentEntity departmentEntity1 = departmentMapper.deptModelToEntity(departmentRequest);
        departmentEntity1.setDepartmentId(departmentEntity.getDepartmentId());
        departmentRepository.save(departmentEntity);
        log.info("your data is updated");
        return departmentRequest;
    }

    public void deleteById(Long departmentId) {
        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentId);
        if (departmentEntity.isPresent()) {
            departmentRepository.deleteById(departmentId);
            log.info("Department Id " + departmentId + "is deleted successfully");
        } else {
            log.info("Department Id " + departmentId + "is not present in your database");
        }
    }
}

package com.crossasyst.deptempmanytomany.controller;

import com.crossasyst.deptempmanytomany.model.DepartmentRequest;
import com.crossasyst.deptempmanytomany.model.DepartmentResponse;
import com.crossasyst.deptempmanytomany.service.DepartmentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping(value = "/department")
    public ResponseEntity<DepartmentResponse> createDepartment(@RequestBody DepartmentRequest departmentRequest) {
        DepartmentResponse departmentResponse = departmentService.createDepartment(departmentRequest);
        log.info("post data by controller is running....");
        return new ResponseEntity<>(departmentResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/departments")
    public ResponseEntity<List<DepartmentRequest>> getAllDepartment() {
        List<DepartmentRequest> departmentRequest = departmentService.getAllDepartment();
        log.info("GetAll data by controller is running....");
        return new ResponseEntity<>(departmentRequest, HttpStatus.OK);
    }

    @GetMapping(value = "/department/{departmentId}")
    public ResponseEntity<DepartmentRequest> getById(@PathVariable Long departmentId) {
        DepartmentRequest departmentRequest = departmentService.getById(departmentId);
        log.info("getById  controller is running....");
        return new ResponseEntity<>(departmentRequest, HttpStatus.OK);
    }

    @PutMapping(value = "/department/{departmentId}")
    public ResponseEntity<DepartmentRequest> updateDepartment(@PathVariable Long departmentId, @RequestBody DepartmentRequest departmentRequest) {
        DepartmentRequest departmentRequestOne = departmentService.updateDepartment(departmentId, departmentRequest);
        log.info("put data by controller is running....");
        return new ResponseEntity<>(departmentRequestOne, HttpStatus.OK);
    }

    @DeleteMapping(value = "/department/{departmentId}")
    public void deleteById(@PathVariable Long departmentId) {
        departmentService.deleteById(departmentId);
        log.info("delete is run using controller");
    }


}

package com.crossasyst.deptempmanytomany.controller;


import com.crossasyst.deptempmanytomany.model.DepartmentRequest;
import com.crossasyst.deptempmanytomany.model.EmployeeRequest;
import com.crossasyst.deptempmanytomany.model.EmployeeResponse;

import com.crossasyst.deptempmanytomany.service.EmployeeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(value = "/employee")
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse employeeResponse = employeeService.createEmployee(employeeRequest);
        log.info("post data by controller");
        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/employee")
    public ResponseEntity<List<EmployeeRequest>> getAllEmployee() {
        List<EmployeeRequest> employeeRequest = employeeService.getAllEmployee();

        return new ResponseEntity<>(employeeRequest, HttpStatus.OK);
    }
    @GetMapping(value = "/employee/{employeeId}")
    public ResponseEntity<EmployeeRequest> getById(@PathVariable Long employeeId){
        EmployeeRequest employeeRequest =employeeService.getById(employeeId);
        return new ResponseEntity<>(employeeRequest,HttpStatus.OK);
    }

    @PutMapping(value = "/employee/{employeeId}")
    public ResponseEntity<EmployeeRequest> updateEmployee(@PathVariable Long employeeId, @RequestBody EmployeeRequest employeeRequest) {
        EmployeeRequest employeeRequestOne = employeeService.updateEmployee(employeeId, employeeRequest);

        return new ResponseEntity<>(employeeRequestOne, HttpStatus.OK);
    }

    @DeleteMapping(value = "/employee/{employeeId}")
    public void deleteById(@PathVariable Long employeeId) {
        employeeService.deleteById(employeeId);

    }
    @GetMapping(value = "/employee/{employeeId}/department")
        public ResponseEntity<List<DepartmentRequest>> findDepartmentByEmployeeId(@PathVariable Long employeeId){
        List<DepartmentRequest> departmentRequests = employeeService.findDepartmentByEmployeeId(employeeId);
        return new ResponseEntity<>(departmentRequests,HttpStatus.OK);
    }
//    @DeleteMapping(value = "/employee/{employeeId}/department/{departmentId}")
//    public void usingEmployeeIdDeleteSpecificDepartmentId(@PathVariable Long employeeId,@PathVariable Long departmentId){
//        employeeService.usingEmployeeIdDeleteSpecificDepartmentId(employeeId,departmentId);
//
//    }
    @PutMapping(value = "/employees/{empId}/departments/{deptId}")
    public ResponseEntity<EmployeeRequest> addDepartmentToEmployee(@PathVariable Long empId, @PathVariable Long deptId) {
        return new ResponseEntity<>(employeeService.addDepartmentToEmployee(empId, deptId), HttpStatus.OK);
    }
    @DeleteMapping(value = "/employees/{empId}/departments/{deptId}")
    public ResponseEntity<?> removeDepartmentFromEmployee(@PathVariable Long empId, @PathVariable Long deptId) {
        return new ResponseEntity<>(employeeService.removeDepartmentFromEmployee(empId, deptId), HttpStatus.OK);
    }

}

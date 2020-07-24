package com.springbootcrud.demo.controller;

import com.springbootcrud.demo.exception.ResourceNotFoundException;
import com.springbootcrud.demo.model.BankAccount;
import com.springbootcrud.demo.model.Employee;
import com.springbootcrud.demo.model.EmployeeBank;
import com.springbootcrud.demo.repository.EmployeePersonalDataRepository;
import com.springbootcrud.demo.service.EmployeeService;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.jndi.JndiTemplateEditor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;


@RestController
public class EmployeeDataController {
    @Autowired
    private EmployeePersonalDataRepository employeePersonalDataRepository;

    @Autowired
    EmployeeService employeeService;

    //creating new Employee
    @PostMapping("/newEmployee")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createNewEmployee(employee);

    }

    // getting All Employees
    @GetMapping("/getAllEmployees")
    public List<Employee> getAllEmployees() {
        return employeeService.findAllEmployee();

    }


    //getting employee by emp id
    @GetMapping("/getEmployeeById/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "employeeId") Long employeeId)
            throws ResourceNotFoundException {
        return ResponseEntity.ok().body(employeeService.getEmployeeById(employeeId));
    }

    //updating employee data based on emp id
    @PutMapping("/updateEmployeeById/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "employeeId") Long employeeId,
                                                   @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeId, employeeDetails));
    }

    //Delete Empoloyee Based on emp id
    @DeleteMapping("/deleteEmployees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        employeeService.deleteEmployeeById(employeeId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    // Demonstrate example of RestTemplate
    //getting Bank Details of an Employee by calling Bank Service
    @RequestMapping(value = "/getEmployeeBankDetails/{employeeId}")
    public EmployeeBank getBankDetails(@PathVariable(value = "employeeId") String employeeId) {
        return employeeService.getEmployeeBankDetails(employeeId);
    }


}

package com.springbootcrud.demo.controller;
import com.springbootcrud.demo.exception.ResourceNotFoundException;
import com.springbootcrud.demo.model.BankAccount;
import com.springbootcrud.demo.model.Employee;
import com.springbootcrud.demo.model.EmployeeBank;
import com.springbootcrud.demo.repository.EmployeePersonalDataRepository;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
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
    RestTemplate restTemplate;
    @Autowired
    Environment env;

    //creating new Employee
    @PostMapping("/newEmployee")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeePersonalDataRepository.save(employee);
    }

    // getting All Employees
    @GetMapping("/getAllEmployees")
    public List<Employee> getAllEmployees() {
        return  employeePersonalDataRepository.findAll();
    }

    //getting employee by emp id
    @GetMapping("/getEmployeeById/{employeeId}")
    public ResponseEntity < Employee > getEmployeeById(@PathVariable(value = "employeeId") Long employeeId)
            throws ResourceNotFoundException {
        Employee employee = employeePersonalDataRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }

    //updating employee data based on emp id
    @PutMapping("/updateEmployeeById/{employeeId}")
    public ResponseEntity< Employee > updateEmployee(@PathVariable(value = "employeeId") Long employeeId,
                                                      @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = employeePersonalDataRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this employeeId :: " + employeeId));

        employee.setEmailId(employeeDetails.getEmailId());
        employee.setAddress(employeeDetails.getAddress());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        final Employee updatedEmployee = employeePersonalDataRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    //Delete Empoloyee Based on emp id
    @DeleteMapping("/deleteEmployees/{id}")
    public Map< String, Boolean > deleteEmployee(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        Employee employee = employeePersonalDataRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employeePersonalDataRepository.delete(employee);
        Map < String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

 // Demonstrate example of RestTemplate
   //getting Bank Details of an Employee by calling Bank Service
    @RequestMapping(value = "/getEmployeeBankDetails/{employeeId}")
    public EmployeeBank getBankDetails(@PathVariable(value = "employeeId") Long employeeId) {
        HttpHeaders headers = new HttpHeaders();
        EmployeeBank employeeBank=new EmployeeBank();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<BankAccount> entity = new HttpEntity<>(headers);
        Employee employee=null;
        try {
            employee=getEmployeeById(Long.valueOf(employeeId)).getBody();
            employeeBank.setEmployee(employee);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }

        BankAccount bankAccount=restTemplate.exchange(env.getProperty("employee_bank_uri") +employeeId, HttpMethod.GET, entity, BankAccount.class).getBody();
        employeeBank.setBankAccount(bankAccount);
        return employeeBank;

    }





}

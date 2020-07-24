package com.springbootcrud.demo.service;

import com.springbootcrud.demo.exception.ResourceNotFoundException;
import com.springbootcrud.demo.model.BankAccount;
import com.springbootcrud.demo.model.Employee;
import com.springbootcrud.demo.model.EmployeeBank;
import com.springbootcrud.demo.repository.EmployeePersonalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeePersonalDataRepository employeePersonalDataRepository;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    Environment env;

    public Employee createNewEmployee(Employee employee) {
        return employeePersonalDataRepository.save(employee);

    }
    public List<Employee> findAllEmployee() {

        return employeePersonalDataRepository.findAll();
    }

    public Employee getEmployeeById(Long employeeId) throws ResourceNotFoundException {
        return employeePersonalDataRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
    }


    public Employee updateEmployeeById(Long employeeId, Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = employeePersonalDataRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this employeeId :: " + employeeId));

        employee.setEmailId(employeeDetails.getEmailId());
        employee.setAddress(employeeDetails.getAddress());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        return employeePersonalDataRepository.save(employee);


    }

    public void deleteEmployeeById(Long employeeId) throws ResourceNotFoundException {
        Employee employee = employeePersonalDataRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employeePersonalDataRepository.delete(employee);

    }

    public EmployeeBank getEmployeeBankDetails(String employeeId) {
        HttpHeaders headers = new HttpHeaders();
        EmployeeBank employeeBank = new EmployeeBank();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<BankAccount> entity = new HttpEntity<>(headers);
        Employee employee = null;
        try {
            employee = getEmployeeById(Long.valueOf(employeeId));
            employeeBank.setEmployee(employee);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }

        BankAccount bankAccount = restTemplate.exchange(env.getProperty("employee_bank_uri") + employeeId, HttpMethod.GET, entity, BankAccount.class).getBody();
        employeeBank.setBankAccount(bankAccount);

        return employeeBank;

    }
}

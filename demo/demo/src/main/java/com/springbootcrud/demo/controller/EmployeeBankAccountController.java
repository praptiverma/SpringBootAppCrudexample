package com.springbootcrud.demo.controller;

import com.springbootcrud.demo.exception.ResourceNotFoundException;

import com.springbootcrud.demo.model.BankAccount;
import com.springbootcrud.demo.repository.BankAccountDataRepository;
import com.springbootcrud.demo.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class EmployeeBankAccountController {

    @Autowired
    BankAccountService bankAccountService;

    //creating new Bank Account of an Employee
    @PostMapping("/newAccount")
    public BankAccount createAccount(@RequestBody BankAccount bankAccount) throws ResourceNotFoundException {
        return bankAccountService.createNewAccount(bankAccount);

    }

    //getting All Bank Accounts
    @GetMapping("/getAllAccounts")
    public List<BankAccount> getAllAccounts() {
        return bankAccountService.getAllEmployeeAccount();
    }

    //getting bank account by employee Bank Id
    @GetMapping("/getAccount/{employeeId}")
    public ResponseEntity<BankAccount> getAccount(@PathVariable(value = "employeeId") Long employeeId)
            throws ResourceNotFoundException {
        return ResponseEntity.ok().body(bankAccountService.getEmployeeById(employeeId));

    }
    //getting bank account by employee Bank Id
    @GetMapping("/getAccountByAccountNo/{accountNo}")
    public ResponseEntity<BankAccount> getAccountByAccountNo(@PathVariable(value = "accountNo") String accountNo)
            throws ResourceNotFoundException {
        return ResponseEntity.ok().body(bankAccountService.getAccountByAccountNo(accountNo));

    }



    //updating Bank details based on account id
    @PutMapping("/updateAccount/{accountNo}")
    public ResponseEntity<BankAccount> updateAccount(@PathVariable(value = "accountNo") String accountNo,
                                                     @RequestBody BankAccount bankDetails) throws ResourceNotFoundException {

        return ResponseEntity.ok().body(bankAccountService.updateAccountById(accountNo,bankDetails));

    }

    // Deleting Bank Account based on account id
    @DeleteMapping("/deleteAccount/{accountNo}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "accountNo") String accountNo)
            throws ResourceNotFoundException {
        bankAccountService.deleteAccount(accountNo);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

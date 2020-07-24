package com.springbootcrud.demo.controller;

import com.springbootcrud.demo.exception.ResourceNotFoundException;

import com.springbootcrud.demo.model.BankAccount;
import com.springbootcrud.demo.repository.BankAccountDataRepository;
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
    private BankAccountDataRepository bankAccountDataRepository;

    //creating new Bank Account of an Employee
    @PostMapping("/newAccount")
    public BankAccount createAccount(@RequestBody BankAccount bankAccount) throws ResourceNotFoundException {
        return bankAccountDataRepository.save(bankAccount);
    }

    //getting All Bank Accounts
    @GetMapping("/getAllAccounts")
    public List<BankAccount> getAllAccounts() {
        return bankAccountDataRepository.findAll();
    }

    //getting bank account by employee Bank Id
    @GetMapping("/getAccount/{employeeId}")
    public ResponseEntity<BankAccount> getAccount(@PathVariable(value = "employeeId") Long employeeId)
            throws ResourceNotFoundException {
        try {
            BankAccount account = getAllAccounts().stream().filter(s -> s.getEmployeeId() == employeeId).collect(Collectors.toList()).get(0);
            return ResponseEntity.ok().body(account);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Bank Account Doesnot Exist");
        }
    }

    //updating Bank details based on account id
    @PutMapping("/updateAccount/{accountNo}")
    public ResponseEntity<BankAccount> updateAccount(@PathVariable(value = "accountNo") String accountNo,
                                                     @RequestBody BankAccount bankDetails) throws ResourceNotFoundException {
        try {
            BankAccount account = getAllAccounts().stream().filter(s -> s.getAccountNo().equalsIgnoreCase(accountNo)).collect(Collectors.toList()).get(0);
            account.setAccountName(bankDetails.getAccountName());
            account.setAccountNo(bankDetails.getAccountNo());
            account.setBankName(bankDetails.getBankName());
            account.setIfscCode(bankDetails.getIfscCode());
            final BankAccount updatedAccount = bankAccountDataRepository.save(account);
            return ResponseEntity.ok(updatedAccount);
        }
        catch(Exception e)
        {
            throw new ResourceNotFoundException("Bank Account Doesnot Exist");
        }
    }

    // Deleting Bank Account based on account id
    @DeleteMapping("/deleteAccount/{accountNo}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "accountNo") String accountNo)
            throws ResourceNotFoundException {
        try {
            BankAccount account = getAllAccounts().stream().filter(s -> s.getAccountNo().equalsIgnoreCase(accountNo)).collect(Collectors.toList()).get(0);
            bankAccountDataRepository.delete(account);
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
            return response;
        }
        catch(Exception e){
            throw new ResourceNotFoundException("Bank Account Doesnot Exist");

        }
    }
}

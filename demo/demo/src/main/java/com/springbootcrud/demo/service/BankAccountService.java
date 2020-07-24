package com.springbootcrud.demo.service;

import com.springbootcrud.demo.exception.ResourceNotFoundException;
import com.springbootcrud.demo.model.BankAccount;
import com.springbootcrud.demo.repository.BankAccountDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BankAccountService {
    @Autowired
    private BankAccountDataRepository bankAccountDataRepository;
    public BankAccount createNewAccount(BankAccount bankAccount) throws ResourceNotFoundException {
        return bankAccountDataRepository.save(bankAccount);
    }


    public List<BankAccount> getAllEmployeeAccount() {
        return bankAccountDataRepository.findAll();
    }


    public BankAccount getEmployeeById(Long employeeId) throws  ResourceNotFoundException{
        try {
            return getAllEmployeeAccount().stream().filter(s -> s.getEmployeeId() == employeeId).collect(Collectors.toList()).get(0);
        }
        catch(Exception e)
        {
            throw new ResourceNotFoundException("\"Employee not found for this id :: \" + employeeId)");
        }
    }

    public BankAccount updateAccountById(String accountNo,BankAccount bankDetails) throws ResourceNotFoundException {

            BankAccount account=  bankAccountDataRepository.findById(accountNo)
                    .orElseThrow(() -> new ResourceNotFoundException("Account not found for this id :: " + accountNo));
            account.setAccountName(bankDetails.getAccountName());
            account.setAccountNo(bankDetails.getAccountNo());
            account.setBankName(bankDetails.getBankName());
            account.setIfscCode(bankDetails.getIfscCode());
            return  bankAccountDataRepository.save(account);

    }

    public  void deleteAccount(String accountNo) throws ResourceNotFoundException{

            BankAccount account=  bankAccountDataRepository.findById(accountNo).orElseThrow(() -> new ResourceNotFoundException("Account not found for this id :: " + accountNo));
            bankAccountDataRepository.delete(account);

    }

    public BankAccount getAccountByAccountNo(String accountNo) throws ResourceNotFoundException{
            return bankAccountDataRepository.findById(accountNo).orElseThrow(() -> new ResourceNotFoundException("Account not found for this id :: " + accountNo));
    }
}

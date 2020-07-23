package com.springbootcrud.demo.model;

import javax.persistence.*;

@Entity
@Table(name="BankAccountDetails")
public class BankAccount {
    private long employeeId;
    private String accountName;
    private String accountNo;
    private String bankName;
    private String ifscCode;

    public BankAccount() {

    }

    public BankAccount(long employeeId, String accountName, String accountNo, String bankName, String ifscCode) {
        this.employeeId = employeeId;
        this.accountName = accountName;
        this.accountNo= accountNo;
        this.bankName = bankName;
        this.ifscCode = ifscCode;
    }
    @Id

    public String getAccountNo() {
        return this.accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }


    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }


    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }
}

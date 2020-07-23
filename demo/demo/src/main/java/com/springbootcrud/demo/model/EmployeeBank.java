package com.springbootcrud.demo.model;


public class EmployeeBank {
    public Employee employee;
    public BankAccount bankAccount;


    public EmployeeBank() {
    }
    public EmployeeBank(Employee employee, BankAccount bankAccount) {
        this.employee = employee;
        this.bankAccount = bankAccount;
    }
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}

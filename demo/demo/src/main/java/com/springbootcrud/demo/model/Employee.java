package com.springbootcrud.demo.model;

import javax.persistence.*;

@Entity
@Table (name="EmployeePersonalData")
public class Employee {

        private long employeeId;
        private String firstName;
        private String lastName;
        private  String address;
        private String emailId;

    public Employee() {

    }

    public Employee(long employeeId, String firstName, String lastName, String address, String emailId) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.emailId = emailId;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    @Column( nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @Column(name = "email_address", nullable = false)
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}

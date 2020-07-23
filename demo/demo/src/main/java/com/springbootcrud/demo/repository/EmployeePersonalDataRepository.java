package com.springbootcrud.demo.repository;
import com.springbootcrud.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeePersonalDataRepository extends JpaRepository<Employee,Long> {
}

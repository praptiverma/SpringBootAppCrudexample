package com.springbootcrud.demo.repository;

import com.springbootcrud.demo.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountDataRepository extends JpaRepository<BankAccount,String>  {




}

package com.example.for_final.repository;

import com.example.for_final.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepo extends JpaRepository<Company,Integer> {
    Optional<Company> findCompanyByCompanyName(String companyName);
}

package com.example.for_final.service.company;

import com.example.for_final.entity.Company;

import java.util.List;

public interface CompanyServiceInterface {
    List<Company> getListOfCompanies();
    Company addCompany(Company company);
}

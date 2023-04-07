package com.example.for_final.service.company;

import com.example.for_final.dto.UserToAddInCompany;
import com.example.for_final.entity.Company;
import com.example.for_final.entity.User;

import java.util.List;

public interface CompanyServiceInterface {
    List<Company> getListOfCompanies();
    Company addCompany(Company company);

    Company addUsersInCompany(UserToAddInCompany user);

    List<User> getCompanyUsers(String companyName);
}

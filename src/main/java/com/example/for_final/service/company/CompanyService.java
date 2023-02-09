package com.example.for_final.service.company;

import com.example.for_final.RoleAndTypes.AccountType;
import com.example.for_final.entity.Company;
import com.example.for_final.entity.User;
import com.example.for_final.repository.CompanyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService implements CompanyServiceInterface {
    private final CompanyRepo companyRepo;
    @Override
    public List<Company> getListOfCompanies() {
        return companyRepo.findAll();
    }

    @Override
    public Company addCompany(Company company) {
        var user = new User("ni", "1111", "ni@mail.com", AccountType.JOB_SEEKER);
        List<User> userList = new ArrayList<>();
        userList.add(user);
        company.setUsers(userList);
        company.setJobs(null);
        return companyRepo.save(company);
    }
}

package com.example.for_final.service.company;

import com.example.for_final.RoleAndTypes.AccountType;
import com.example.for_final.dto.UserToAddInCompany;
import com.example.for_final.entity.Company;
import com.example.for_final.entity.User;
import com.example.for_final.repository.CompanyRepo;
import com.example.for_final.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService implements CompanyServiceInterface {
    private final CompanyRepo companyRepo;
    private final UserRepo userRepo;
    @Override
    public List<Company> getListOfCompanies() {
        return companyRepo.findAll();
    }

    @Override
    public Company addCompany(Company company) {
        List<User> userList = new ArrayList<>();
        company.setUsers(userList);
        company.setJobs(null);
        return companyRepo.save(company);
    }

    @Override
    public Company addUsersInCompany(UserToAddInCompany user) {
        var userToAdd = userRepo.findByEmail(user.getEmail())
                .orElseThrow(() -> new NotFoundException("User Not Found"));

        var company = companyRepo.findCompanyByCompanyName(user.getCompanyName())
                .orElseThrow(() -> new NotFoundException("Company Not Found"));
        List<User> users = new ArrayList<>(company.getUsers());
        users.add(userToAdd);

        company.setUsers(users);
        return companyRepo.save(company);
    }

    @Override
    public List<User> getCompanyUsers(String companyName) {
        var company = companyRepo.findCompanyByCompanyName(companyName)
                .orElseThrow(() -> new NotFoundException("Company Not Found"));
        return company.getUsers();
    }

}

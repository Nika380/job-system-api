package com.example.for_final.controller;

import com.example.for_final.entity.Company;
import com.example.for_final.security.SecUser;
import com.example.for_final.service.company.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping
    public List<Company> getListOfCompanies() {
        return companyService.getListOfCompanies();
    }

    @PostMapping("/add")
    public Company addCompany(@RequestBody Company company, @AuthenticationPrincipal SecUser secUser) {
        company.setCreatedBy(secUser.getUsername());
        company.setUpdatedBy(secUser.getUsername());
        return companyService.addCompany(company);
    }
}

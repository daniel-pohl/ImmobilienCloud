package de.neuefische.backend.Company;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepo companyRepo;


    public CompanyService(CompanyRepo companyRepo) {
        this.companyRepo = companyRepo;
    }

    public List<Company> allCompanies() {
        return companyRepo.findAll();
    }
}

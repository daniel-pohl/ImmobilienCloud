package de.neuefische.backend.company;

import de.neuefische.backend.exceptions.CompanyNotFoundException;
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


    public Company findCompanyById(String id) throws CompanyNotFoundException {
        return companyRepo.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("Company with id "+ id + "not found")
        );
    }
}

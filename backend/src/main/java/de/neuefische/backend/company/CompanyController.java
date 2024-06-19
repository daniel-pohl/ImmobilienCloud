package de.neuefische.backend.company;

import de.neuefische.backend.exceptions.CompanyNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/company")
    public List<Company> getCompanies() {
        return companyService.allCompanies();
    }
    @GetMapping("/company/{id}")
    public Company getCompanyById(@PathVariable String id) throws CompanyNotFoundException {
        return companyService.findCompanyById(id);
    }
}

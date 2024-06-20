package de.neuefische.backend.company;

import de.neuefische.backend.exceptions.CompanyNotFoundException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/company")
    public Company addCompany(@Valid @RequestBody CompanyDTO companyDTO) {
        return companyService.saveCompany(companyDTO);
    }

}

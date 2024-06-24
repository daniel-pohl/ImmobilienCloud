package de.neuefische.backend.company;

import de.neuefische.backend.UuidService;
import de.neuefische.backend.exceptions.CompanyNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepo companyRepo;
    private final UuidService uuidService;

    public CompanyService(CompanyRepo companyRepo, UuidService uuidService) {
        this.companyRepo = companyRepo;
        this.uuidService = uuidService;
    }

    public List<Company> allCompanies() {
        return companyRepo.findAll();
    }

    public Company findCompanyById(String id) throws CompanyNotFoundException {
        return companyRepo.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("Company with id "+ id + "not found")
        );
    }

    public Company saveCompany(CompanyDTO companyDTO) {
        Company company = CompanyMapper.toEntity(companyDTO);
        company.setId(uuidService.generateId());
        return companyRepo.save(company);
    }

    public void deleteCompanyById(String id){
            companyRepo.deleteById(id);
    }

    public Company updateCompany(CompanyDTO companyDTO, String id) throws CompanyNotFoundException {
        Company existingCompany = this.findCompanyById(id);

        existingCompany.setName(companyDTO.getName().trim());
        existingCompany.setCountry(companyDTO.getCountry().trim());
        existingCompany.setCity(companyDTO.getCity().trim());
        existingCompany.setPlz(companyDTO.getPlz().trim());
        existingCompany.setStreet(companyDTO.getStreet().trim());
        existingCompany.setStreetNumber(companyDTO.getStreetNumber().trim());
        existingCompany.setPhoneNumber(companyDTO.getPhoneNumber().trim());
        existingCompany.setEmail(companyDTO.getEmail().trim());
        existingCompany.setWebsite(companyDTO.getWebsite().trim());
        existingCompany.setComment(companyDTO.getComment().trim());

        return companyRepo.save(existingCompany);
    }

}

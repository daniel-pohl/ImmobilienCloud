package de.neuefische.backend.company;

public class CompanyMapper {
    public static Company toEntity(CompanyDTO dto) {
        Company company = new Company();
        company.setName(dto.getName());
        company.setCountry(dto.getCountry());
        company.setCity(dto.getCity());
        company.setPlz(dto.getPlz());
        company.setStreet(dto.getStreet());
        company.setStreetNumber(dto.getStreetNumber());
        company.setPhoneNumber(dto.getPhoneNumber());
        company.setEmail(dto.getEmail());
        company.setWebsite(dto.getWebsite());
        company.setComment(dto.getComment());
        company.setContactIds(dto.getContactIds());
        return company;
    }
}

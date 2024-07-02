package de.neuefische.backend.company;

import de.neuefische.backend.UuidService;
import de.neuefische.backend.exceptions.CompanyNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CompanyServiceTest {

    private final CompanyRepo mockCompanyRepo = mock(CompanyRepo.class);
    private final UuidService mockUuidService = mock(UuidService.class);

    @Test
    void searchCompaniesByName() {
        Company company1 = new Company("123", "Example Company", "Germany", "Berlin", "12345", "Example Street", "1", "+491234567890", "example1@mail.com", "www.example1.com", "comment1", new ArrayList<>());
        Company company2 = new Company("124", "Another Example", "Germany", "Munich", "54321", "Another Street", "2", "+491234567891", "example2@mail.com", "www.example2.com", "comment2", new ArrayList<>());
        List<Company> expectedCompanies = List.of(company1, company2);

        when(mockCompanyRepo.findByNameContainingIgnoreCase("example")).thenReturn(expectedCompanies);

        CompanyService companyService1 = new CompanyService(mockCompanyRepo, mockUuidService);
        List<Company> result = companyService1.searchCompaniesByName("example");

        verify(mockCompanyRepo).findByNameContainingIgnoreCase("example");
        assertEquals(expectedCompanies, result);
    }

    @Test
    void allCompanies() {
        List<String> contactIdsToTest = List.of("sdfsr24","sdf25");
        Company expectedCompany = new Company("123", "John Doe", "Germany", "Berlin", "61355", "Demostreet", "132", "+492374928349", "test@mail.de", "www.test.de", "comment1235345 comment1235", contactIdsToTest);
        List<Company> expectedCompanies = List.of(expectedCompany);
        when(mockCompanyRepo.findAll()).thenReturn(expectedCompanies);

        CompanyService companyService1 = new CompanyService(mockCompanyRepo, mockUuidService);

        List<Company> result = companyService1.allCompanies();
        verify(mockCompanyRepo).findAll();
        assertEquals(expectedCompanies, result);
    }

    @Test
    void findCompanyById_companyFound() throws CompanyNotFoundException {
        List<String> contactIdsToTest = List.of("sdfsr24","sdf25");
        Company expectedCompany = new Company("123", "John Doe", "Germany", "Berlin", "61355", "Demostreet", "132", "+492374928349", "test@mail.de", "www.test.de", "comment1235345 comment1235", contactIdsToTest);
        when(mockCompanyRepo.findById("123")).thenReturn(Optional.of(expectedCompany));

        CompanyService companyService1 = new CompanyService(mockCompanyRepo, mockUuidService);
        Company result = companyService1.findCompanyById("123");
        verify(mockCompanyRepo).findById("123");
        assertEquals(expectedCompany, result);
    }

    @Test
    void findCompanyById_companyNotFound() {

        when(mockCompanyRepo.findById("123")).thenReturn(Optional.empty());

        CompanyService companyService1 = new CompanyService(mockCompanyRepo, mockUuidService);
        assertThrows(CompanyNotFoundException.class, () ->
                companyService1.findCompanyById("123"));
        verify(mockCompanyRepo).findById("123");

    }

    @Test
    void saveCompany_whenValidCompany_shouldReturnCreatedCompany(){
        List<String> contactIdsToTest = Collections.emptyList();

        CompanyDTO givenCompanyDTO = new CompanyDTO("John Doe", "Germany", "Berlin", "61355", "Demostreet", "132", "+492374928349", "test@mail.de", "www.test.de", "comment1235345 comment1235", contactIdsToTest);

        Company expectedCompany = CompanyMapper.toEntity(givenCompanyDTO);
        expectedCompany.setId("123");
        when(mockUuidService.generateId()).thenReturn("123");
        when(mockCompanyRepo.save(expectedCompany)).thenReturn(expectedCompany);

        CompanyService companyService1 = new CompanyService(mockCompanyRepo, mockUuidService);
        Company result = companyService1.saveCompany(givenCompanyDTO);

        verify(mockCompanyRepo).save(expectedCompany);

        assertEquals(expectedCompany, result);
    }

    @Test
    void deleteCompany_whenMethodCalled_thenDeleteMethodOnRepositoryWasCalledOnlyOnce() {
        CompanyService companyService1 = new CompanyService(mockCompanyRepo, mockUuidService);
        companyService1.deleteCompanyById("123");
        verify(mockCompanyRepo, times(1)).deleteById("123");
    }

    @Test
    void updateCompany_whenValidCompanyDTO_shouldReturnUpdatedCompany() throws CompanyNotFoundException {
        List<String> contactIdsToTest = List.of("sdfsr24","sdf25");
        Company existingCompany = new Company("123", "John Doe", "Germany", "Berlin", "61355", "Demostreet", "132", "+492374928349", "test@mail.de", "www.test.de", "comment1235345 comment1235",contactIdsToTest);
        CompanyDTO updatedCompanyDTO = new CompanyDTO("Jane Doe", "Germany", "Munich", "80331", "Newstreet", "456", "+491234567890", "jane@mail.de", "www.jane.de", "new comment", contactIdsToTest);
        Company expectedUpdatedCompany = new Company("123", "Jane Doe", "Germany", "Munich", "80331", "Newstreet", "456", "+491234567890", "jane@mail.de", "www.jane.de", "new comment", contactIdsToTest);

        when(mockCompanyRepo.findById("123")).thenReturn(Optional.of(existingCompany));
        when(mockCompanyRepo.save(expectedUpdatedCompany)).thenReturn(expectedUpdatedCompany);

        CompanyService companyService1 = new CompanyService(mockCompanyRepo, mockUuidService);
        Company result = companyService1.updateCompany(updatedCompanyDTO, "123");

        verify(mockCompanyRepo).findById("123");
        verify(mockCompanyRepo).save(expectedUpdatedCompany);

        assertEquals(expectedUpdatedCompany, result);
    }


}

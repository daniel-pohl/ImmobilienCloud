package de.neuefische.backend.company;

import de.neuefische.backend.UuidService;
import de.neuefische.backend.exceptions.CompanyNotFoundException;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CompanyServiceTest {

    private final CompanyRepo mockCompanyRepo = mock(CompanyRepo.class);
    private final UuidService mockUuidService = mock(UuidService.class);

    @Test
    void allCompanies() {
        Company expectedCompany = new Company("123", "John Doe", "Germany", "Berlin", "61355", "Demostreet", "132", "+492374928349", "test@mail.de", "www.test.de", "comment1235345 comment1235");
        List<Company> expectedCompanies = List.of(expectedCompany);
        when(mockCompanyRepo.findAll()).thenReturn(expectedCompanies);

        CompanyService companyService1 = new CompanyService(mockCompanyRepo, mockUuidService);

        List<Company> result = companyService1.allCompanies();
        verify(mockCompanyRepo).findAll();
        assertEquals(expectedCompanies, result);
    }

    @Test
    void findCompanyById_companyFound() throws CompanyNotFoundException {
        Company expectedCompany = new Company("123", "John Doe", "Germany", "Berlin", "61355", "Demostreet", "132", "+492374928349", "test@mail.de", "www.test.de", "comment1235345 comment1235");
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
        CompanyDTO givenCompanyDTO = new CompanyDTO("John Doe", "Germany", "Berlin", "61355", "Demostreet", "132", "+492374928349", "test@mail.de", "www.test.de", "comment1235345 comment1235");

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
        Company existingCompany = new Company("123", "John Doe", "Germany", "Berlin", "61355", "Demostreet", "132", "+492374928349", "test@mail.de", "www.test.de", "comment1235345 comment1235");
        CompanyDTO updatedCompanyDTO = new CompanyDTO("Jane Doe", "Germany", "Munich", "80331", "Newstreet", "456", "+491234567890", "jane@mail.de", "www.jane.de", "new comment");
        Company expectedUpdatedCompany = new Company("123", "Jane Doe", "Germany", "Munich", "80331", "Newstreet", "456", "+491234567890", "jane@mail.de", "www.jane.de", "new comment");

        when(mockCompanyRepo.findById("123")).thenReturn(Optional.of(existingCompany));
        when(mockCompanyRepo.save(expectedUpdatedCompany)).thenReturn(expectedUpdatedCompany);

        CompanyService companyService1 = new CompanyService(mockCompanyRepo, mockUuidService);
        Company result = companyService1.updateCompany(updatedCompanyDTO, "123");

        verify(mockCompanyRepo).findById("123");
        verify(mockCompanyRepo).save(expectedUpdatedCompany);

        assertEquals(expectedUpdatedCompany, result);
    }


}

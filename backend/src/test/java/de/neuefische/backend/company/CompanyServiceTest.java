package de.neuefische.backend.company;

import de.neuefische.backend.exceptions.CompanyNotFoundException;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CompanyServiceTest {

    private final CompanyRepo mockCompanyRepo = mock(CompanyRepo.class);

    @Test
    void allCompanies() {
        Company expectedCompany = new Company("123", "John Doe", "Germany", "Berlin", "61355", "Demostreet", "132", "+492374928349", "test@mail.de", "www.test.de", "comment1235345 comment1235");
        List<Company> expectedCompanies = List.of(expectedCompany);
        when(mockCompanyRepo.findAll()).thenReturn(expectedCompanies);

        CompanyService companyService1 = new CompanyService(mockCompanyRepo);

        List<Company> result = companyService1.allCompanies();
        verify(mockCompanyRepo).findAll();
        assertEquals(expectedCompanies, result);
    }
    //nennen wie die Funktion heisst und whenOneProductInDB_thenReturnListOfOne
    //bei allCompanies nur einen Test, weil ich eh "alles" finde

    @Test
    void findCompanyById_companyFound() throws CompanyNotFoundException {
        Company expectedCompany = new Company("123", "John Doe", "Germany", "Berlin", "61355", "Demostreet", "132", "+492374928349", "test@mail.de", "www.test.de", "comment1235345 comment1235");
        when(mockCompanyRepo.findById("123")).thenReturn(Optional.of(expectedCompany));

        CompanyService companyService1 = new CompanyService(mockCompanyRepo);
        Company result = companyService1.findCompanyById("123");
        verify(mockCompanyRepo).findById("123");
        assertEquals(expectedCompany, result);
    }
    //was ist eine checked excep oder unchecked?

    @Test
    void getCompanyById_companyNotFound() {

        when(mockCompanyRepo.findById("123")).thenReturn(Optional.empty());

        CompanyService companyService1 = new CompanyService(mockCompanyRepo);
        assertThrows(CompanyNotFoundException.class, () -> {
            companyService1.findCompanyById("123");
        });
        verify(mockCompanyRepo).findById("123");

    }


}

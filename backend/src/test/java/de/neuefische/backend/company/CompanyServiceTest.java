package de.neuefische.backend.company;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CompanyServiceTest {

    private final CompanyRepo mockCompanyRepo = mock(CompanyRepo.class);

    @Test
    void getCompanies() {

        Company expectedCompany = new Company("123", "John Doe", "Germany", "Berlin", "61355", "Demostreet", "132", "+492374928349", "test@mail.de", "www.test.de", "comment1235345 comment1235");
        List<Company> expectedCompanies = List.of(expectedCompany);
        when(mockCompanyRepo.findAll()).thenReturn(expectedCompanies);

        CompanyService companyService1 = new CompanyService(mockCompanyRepo);

        List<Company> result = companyService1.allCompanies();
        verify(mockCompanyRepo).findAll();
        assertEquals(expectedCompanies, result);

        // Mock-Verhalten für den Testfall definieren


        // TodoService mit dem Mock erstellen


        // Test ausführen


        // Überprüfen, ob die Methode aufgerufen wurde


        // Überprüfen, ob das erwartete Ergebnis zurückgegeben wurde


    }


}

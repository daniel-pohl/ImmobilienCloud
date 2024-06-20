package de.neuefische.backend.company;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class CompanyControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CompanyRepo companyRepo;

    @DirtiesContext
    @Test
    void getAllCompanies_whenNoCompanyInDB_thenReturnEmptyList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/company"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]"));
    }

    @DirtiesContext
    @Test
    void getAllCompanies_whenCompaniesInDB_thenReturnListOfCompanies() throws Exception {
        Company company = new Company(null, "TestFirma3", "TestCountry3", "TestCity3", "133335", "TestStreet", "3", "123-3333-7890", "test3@example.com", "http://www.testfirma3.com", "This is a test comment333.");
        companyRepo.save(company);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/company"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                              [
                                        {
                                              "name": "TestFirma3",
                                              "country": "TestCountry3",
                                              "city": "TestCity3",
                                              "plz": "133335",
                                              "street": "TestStreet",
                                              "streetNumber": "3",
                                              "phoneNumber": "123-3333-7890",
                                              "email": "test3@example.com",
                                              "website": "http://www.testfirma3.com",
                                              "comment": "This is a test comment333."
                                        }
                            ]
                        """));
    }

    @DirtiesContext
    @Test
    void getAllCompanies_whenOneCompanyInDB_thenReturnListOfOne() throws Exception {
        Company company = new Company(null, "TestFirma3", "TestCountry3", "TestCity3", "133335", "TestStreet", "3", "123-3333-7890", "test3@example.com", "http://www.testfirma3.com", "This is a test comment333.");
        companyRepo.save(company);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/company"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        [
                            {
                                  "name": "TestFirma3",
                                  "country": "TestCountry3",
                                  "city": "TestCity3",
                                  "plz": "133335",
                                  "street": "TestStreet",
                                  "streetNumber": "3",
                                  "phoneNumber": "123-3333-7890",
                                  "email": "test3@example.com",
                                  "website": "http://www.testfirma3.com",
                                  "comment": "This is a test comment333."
                            }
                        ]
                        """))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").exists());

    }

    @DirtiesContext
    @Test
    void getCompanyById_whenCompanyInDB_thenReturnCompany() throws Exception {
        Company company = new Company("123", "TestFirma3", "TestCountry3", "TestCity3", "133335", "TestStreet", "3", "123-3333-7890", "test3@example.com", "http://www.testfirma3.com", "This is a test comment333.");

        companyRepo.save(company);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/company/123"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        
                                        {
                                              "id": "123",
                                              "name": "TestFirma3",
                                              "country": "TestCountry3",
                                              "city": "TestCity3",
                                              "plz": "133335",
                                              "street": "TestStreet",
                                              "streetNumber": "3",
                                              "phoneNumber": "123-3333-7890",
                                              "email": "test3@example.com",
                                              "website": "http://www.testfirma3.com",
                                              "comment": "This is a test comment333."
                                        }
                        
                        """));
    }
}

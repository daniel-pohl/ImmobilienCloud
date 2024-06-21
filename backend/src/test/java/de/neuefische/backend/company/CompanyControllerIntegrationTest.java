package de.neuefische.backend.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureMockMvc
class CompanyControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CompanyRepo companyRepo;
    @Autowired
    private ObjectMapper objectMapper;

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
        Company company = new Company(null, "TestFirma3", "TestCountry3", "TestCity3", "133335", "TestStreet", "3", "123-3333-7890", "test3@example.com", "https://www.testfirma3.com", "This is a test comment333.");
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
                                              "website": "https://www.testfirma3.com",
                                              "comment": "This is a test comment333."
                                        }
                            ]
                        """));
    }

    @DirtiesContext
    @Test
    void getAllCompanies_whenOneCompanyInDB_thenReturnListOfOne() throws Exception {
        Company company = new Company(null, "TestFirma3", "TestCountry3", "TestCity3", "133335", "TestStreet", "3", "123-3333-7890", "test3@example.com", "https://www.testfirma3.com", "This is a test comment333.");
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
                                  "website": "https://www.testfirma3.com",
                                  "comment": "This is a test comment333."
                            }
                        ]
                        """))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").exists());

    }

    @DirtiesContext
    @Test
    void getCompanyById_whenCompanyInDB_thenReturnCompany() throws Exception {
        Company company = new Company("123", "TestFirma3", "TestCountry3", "TestCity3", "133335", "TestStreet", "3", "123-3333-7890", "test3@example.com", "https://www.testfirma3.com", "This is a test comment333.");

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
                                              "website": "https://www.testfirma3.com",
                                              "comment": "This is a test comment333."
                                        }
                        
                        """));
    }
    @DirtiesContext
    @Test
    void addCompany_whenValidRequest_thenReturnSavedCompany() throws Exception {
        CompanyDTO companyDTO = new CompanyDTO("TestFirma4", "TestCountry4", "TestCity4", "133336", "TestStreet", "4", "123-4444-7890", "test4@example.com", "https://www.testfirma4.com", "This is a test comment444.");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/company")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(companyDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("TestFirma4"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.country").value("TestCountry4"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value("TestCity4"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.plz").value("133336"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.street").value("TestStreet"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.streetNumber").value("4"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("123-4444-7890"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("test4@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.website").value("https://www.testfirma4.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.comment").value("This is a test comment444."));
    }
    @DirtiesContext
    @Test
    void addCompany_whenInvalidRequest_thenReturnBadRequest() throws Exception {

        CompanyDTO invalidCompanyDTO = new CompanyDTO(null, "TestCountry4", "TestCity4", "133336", "TestStreet", "4", "123-4444-7890", "test4@example.com", "https://www.testfirma4.com", "This is a test comment444.");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/company")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(invalidCompanyDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @DirtiesContext
    @Test
    void deleteCompany_whenCompanyInDB_thenDBDoesNotContainCompanyAnymore() throws Exception {

        MvcResult resultCompanyToDelete = mockMvc.perform(MockMvcRequestBuilders.post("/api/company")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                            {
                                              "name": "TestFirma3",
                                              "country": "TestCountry3",
                                              "city": "TestCity3",
                                              "plz": "133335",
                                              "street": "TestStreet",
                                              "streetNumber": "3",
                                              "phoneNumber": "123-3333-7890",
                                              "email": "test3@example.com",
                                              "website": "https://www.testfirma3.com",
                                              "comment": "This is a test comment333."
                            }
                        """)).andReturn();

        String idToDelete = objectMapper.readValue(resultCompanyToDelete.getResponse().getContentAsString(), Company.class).getId();

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/company/" + idToDelete));
        assertThrows(ServletException.class, () -> mockMvc.perform(MockMvcRequestBuilders.get("/api/company/" + idToDelete)));

    }

}

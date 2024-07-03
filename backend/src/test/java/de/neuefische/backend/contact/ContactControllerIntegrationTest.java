package de.neuefische.backend.contact;

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
class ContactControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ContactRepo contactRepo;
    @Autowired
    private ObjectMapper objectMapper;

    @DirtiesContext
    @Test
    void getAllContacts_whenNoContactInDB_thenReturnEmptyList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/contact"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]"));
    }

    @DirtiesContext
    @Test
    void getAllContacts_whenContactsInDB_thenReturnListOfContacts() throws Exception {

        Contact contact = new Contact(null, "TestKontakt3", "TestCountry3", "TestCity3", "133335", "TestStreet", "3", "123-3333-7890", "test3@example.com", "https://www.testkontakt3.com", "This is a test comment333.", "2345ouoh", false);
        contactRepo.save(contact);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/contact"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        [
                            {
                                  "name": "TestKontakt3",
                                  "country": "TestCountry3",
                                  "city": "TestCity3",
                                  "plz": "133335",
                                  "street": "TestStreet",
                                  "streetNumber": "3",
                                  "phoneNumber": "123-3333-7890",
                                  "email": "test3@example.com",
                                  "website": "https://www.testkontakt3.com",
                                  "comment": "This is a test comment333.",
                                  "companyId": "2345ouoh",
                                  "favorite": false
                            }
                        ]
                        """));
    }

    @DirtiesContext
    @Test
    void getAllContacts_whenOneContactInDB_thenReturnListOfOne() throws Exception {
        Contact contact = new Contact(null, "TestKontakt3", "TestCountry3", "TestCity3", "133335", "TestStreet", "3", "123-3333-7890", "test3@example.com", "https://www.testkontakt3.com", "This is a test comment333.","2345ouoh", false);
        contactRepo.save(contact);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/contact"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        [
                            {
                                  "name": "TestKontakt3",
                                  "country": "TestCountry3",
                                  "city": "TestCity3",
                                  "plz": "133335",
                                  "street": "TestStreet",
                                  "streetNumber": "3",
                                  "phoneNumber": "123-3333-7890",
                                  "email": "test3@example.com",
                                  "website": "https://www.testkontakt3.com",
                                  "comment": "This is a test comment333.",
                                  "companyId": "2345ouoh",
                                  "favorite": false
                            }
                        ]
          """))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").exists());

    }

    @DirtiesContext
    @Test
    void getContactById_whenContactInDB_thenReturnContact() throws Exception {
        Contact contact = new Contact("123", "TestKontakt3", "TestCountry3", "TestCity3", "133335", "TestStreet", "3", "123-3333-7890", "test3@example.com", "https://www.testkontakt3.com", "This is a test comment333.","2345ouoh", false);
        contactRepo.save(contact);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/contact/123"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                                          {
                                                "id": "123",
                                                "name": "TestKontakt3",
                                                "country": "TestCountry3",
                                                "city": "TestCity3",
                                                "plz": "133335",
                                                "street": "TestStreet",
                                                "streetNumber": "3",
                                                "phoneNumber": "123-3333-7890",
                                                "email": "test3@example.com",
                                                "website": "https://www.testkontakt3.com",
                                                "comment": "This is a test comment333.",
                                                "companyId": "2345ouoh",
                                                "favorite": false
                                          }
                        """));
    }

    @DirtiesContext
    @Test
    void addContact_whenValidRequest_thenReturnSavedContact() throws Exception {
        ContactDTO contactDTO = new ContactDTO("TestKontakt4", "TestCountry4", "TestCity4", "133336", "TestStreet", "4", "123-4444-7890", "test4@example.com", "https://www.testkontakt4.com", "This is a test comment444.", "2345ouoh", false);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/contact")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(contactDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("TestKontakt4"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.country").value("TestCountry4"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value("TestCity4"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.plz").value("133336"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.street").value("TestStreet"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.streetNumber").value("4"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("123-4444-7890"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("test4@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.website").value("https://www.testkontakt4.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.comment").value("This is a test comment444."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.companyId").value(""))
                .andExpect(MockMvcResultMatchers.jsonPath("$.favorite").value(false));
    }

    @DirtiesContext
    @Test
    void addContact_whenInvalidRequest_thenReturnBadRequest() throws Exception {
        ContactDTO invalidContactDTO = new ContactDTO(null, "TestCountry4", "TestCity4", "133336", "TestStreet", "4", "123-4444-7890", "test4@example.com", "https://www.testkontakt4.com", "This is a test comment444.", "2345ouoh", false);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/contact")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(invalidContactDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @DirtiesContext
    @Test
    void deleteContact_whenContactInDB_thenDBDoesNotContainContactAnymore() throws Exception {
        MvcResult resultContactToDelete = mockMvc.perform(MockMvcRequestBuilders.post("/api/contact")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                              "name": "TestKontakt3",
                              "country": "TestCountry3",
                              "city": "TestCity3",
                              "plz": "133335",
                              "street": "TestStreet",
                              "streetNumber": "3",
                              "phoneNumber": "123-3333-7890",
                              "email": "test3@example.com",
                              "website": "https://www.testkontakt3.com",
                              "comment": "This is a test comment333.",
                              "companyId": "2345ouoh",
                              "favorite": false
                        }
                        """)).andReturn();

        String idToDelete = objectMapper.readValue(resultContactToDelete.getResponse().getContentAsString(), Contact.class).getId();

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/contact/" + idToDelete));
        assertThrows(ServletException.class, () -> mockMvc.perform(MockMvcRequestBuilders.get("/api/contact/" + idToDelete)));
    }

    @DirtiesContext
    @Test
    void updateContact_whenValidRequest_thenReturnUpdatedContact() throws Exception {
        Contact existingContact = new Contact("123", "OldContact", "OldCountry", "OldCity", "11111", "OldStreet", "1", "111-111-1111", "old@example.com", "https://www.oldcontact.com", "Old comment","2345ouoh", false);
        contactRepo.save(existingContact);

        ContactDTO updatedContactDTO = new ContactDTO("NewContact", "NewCountry", "NewCity", "22222", "NewStreet", "2", "222-222-2222", "new@example.com", "https://www.newcontact.com", "New comment", "2345ouoh", false);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/contact/123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedContactDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("123"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("NewContact"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.country").value("NewCountry"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value("NewCity"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.plz").value("22222"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.street").value("NewStreet"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.streetNumber").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("222-222-2222"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("new@example.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.website").value("https://www.newcontact.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.comment").value("New comment"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.companyId").value("2345ouoh"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.favorite").value(false));
    }

    @DirtiesContext
    @Test
    void toggleFavorite_whenValidRequest_thenReturnUpdatedContact() throws Exception {
        Contact existingContact = new Contact("123", "TestContact", "TestCountry", "TestCity", "12345", "TestStreet", "1", "123-456-7890", "test@example.com", "https://www.testcontact.com", "Test comment", "2345ouoh", false);
        contactRepo.save(existingContact);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/contact/123/favorite"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("123"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.favorite").value(true));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/contact/123/favorite"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("123"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.favorite").value(false));
    }

}

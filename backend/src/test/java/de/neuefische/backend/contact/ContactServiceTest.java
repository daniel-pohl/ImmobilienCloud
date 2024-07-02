package de.neuefische.backend.contact;

import de.neuefische.backend.UuidService;
import de.neuefische.backend.exceptions.ContactNotFoundException;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ContactServiceTest {

    private final ContactRepo mockContactRepo = mock(ContactRepo.class);
    private final UuidService mockUuidService = mock(UuidService.class);

    @Test
    void allContacts() {
        Contact expectedContact = new Contact("123", "John Doe", "Germany", "Berlin", "61355", "Demostreet", "132", "+492374928349", "test@mail.de", "www.test.de", "comment1235345 comment1235", "2345ouoh");
        List<Contact> expectedContacts = List.of(expectedContact);
        when(mockContactRepo.findAll()).thenReturn(expectedContacts);

        ContactService contactService1 = new ContactService(mockContactRepo, mockUuidService);

        List<Contact> result = contactService1.allContacts();
        verify(mockContactRepo).findAll();
        assertEquals(expectedContacts, result);
    }

    @Test
    void findContactById_contactFound() throws ContactNotFoundException {
        Contact expectedContact = new Contact("123", "John Doe", "Germany", "Berlin", "61355", "Demostreet", "132", "+492374928349", "test@mail.de", "www.test.de", "comment1235345 comment1235", "2345ouoh");
        when(mockContactRepo.findById("123")).thenReturn(Optional.of(expectedContact));

        ContactService contactService1 = new ContactService(mockContactRepo, mockUuidService);
        Contact result = contactService1.findContactById("123");
        verify(mockContactRepo).findById("123");
        assertEquals(expectedContact, result);
    }

    @Test
    void findContactById_contactNotFound() {
        when(mockContactRepo.findById("123")).thenReturn(Optional.empty());

        ContactService contactService1 = new ContactService(mockContactRepo, mockUuidService);
        assertThrows(ContactNotFoundException.class, () ->
                contactService1.findContactById("123"));
        verify(mockContactRepo).findById("123");
    }

    @Test
    void saveContact_whenValidContact_shouldReturnCreatedContact() {
        ContactDTO givenContactDTO = new ContactDTO("John Doe", "Germany", "Berlin", "61355", "Demostreet", "132", "+492374928349", "test@mail.de", "www.test.de", "comment1235345 comment1235", "");

        Contact expectedContact = ContactMapper.toEntity(givenContactDTO);
        expectedContact.setId("123");
        when(mockUuidService.generateId()).thenReturn("123");
        when(mockContactRepo.save(expectedContact)).thenReturn(expectedContact);

        ContactService contactService1 = new ContactService(mockContactRepo, mockUuidService);
        Contact result = contactService1.saveContact(givenContactDTO);

        verify(mockContactRepo).save(expectedContact);

        assertEquals(expectedContact, result);
    }

    @Test
    void deleteContact_whenMethodCalled_thenDeleteMethodOnRepositoryWasCalledOnlyOnce() {
        ContactService contactService1 = new ContactService(mockContactRepo, mockUuidService);
        contactService1.deleteContactById("123");
        verify(mockContactRepo, times(1)).deleteById("123");
    }

    @Test
    void updateContact_whenValidContactDTO_shouldReturnUpdatedContact() throws ContactNotFoundException {
        Contact existingContact = new Contact("123", "John Doe", "Germany", "Berlin", "61355", "Demostreet", "132", "+492374928349", "test@mail.de", "www.test.de", "comment1235345 comment1235", "2345ouoh");
        ContactDTO updatedContactDTO = new ContactDTO("Jane Doe", "Germany", "Munich", "80331", "Newstreet", "456", "+491234567890", "jane@mail.de", "www.jane.de", "new comment", "2345ouoh");
        Contact expectedUpdatedContact = new Contact("123", "Jane Doe", "Germany", "Munich", "80331", "Newstreet", "456", "+491234567890", "jane@mail.de", "www.jane.de", "new comment", "2345ouoh");

        when(mockContactRepo.findById("123")).thenReturn(Optional.of(existingContact));
        when(mockContactRepo.save(expectedUpdatedContact)).thenReturn(expectedUpdatedContact);

        ContactService contactService1 = new ContactService(mockContactRepo, mockUuidService);
        Contact result = contactService1.updateContact(updatedContactDTO, "123");

        verify(mockContactRepo).findById("123");
        verify(mockContactRepo).save(expectedUpdatedContact);

        assertEquals(expectedUpdatedContact, result);
    }
}

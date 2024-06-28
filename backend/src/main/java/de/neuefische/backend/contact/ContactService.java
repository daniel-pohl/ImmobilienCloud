package de.neuefische.backend.contact;

import de.neuefische.backend.UuidService;
import de.neuefische.backend.exceptions.ContactNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final ContactRepo contactRepo;
    private final UuidService uuidService;


    public ContactService(ContactRepo contactRepo, UuidService uuidService) {
        this.contactRepo = contactRepo;
        this.uuidService = uuidService;
    }

    public List<Contact> searchContactsByName(String name) {
        return contactRepo.findByNameContainingIgnoreCase(name);
    }

    public List<Contact> allContacts() {
        return contactRepo.findAll();
    }

    public Contact findContactById(String id) throws ContactNotFoundException {
        return contactRepo.findById(id)
                .orElseThrow(() -> new ContactNotFoundException("Contact with id "+ id + "not found")
                );
    }

    public Contact saveContact(ContactDTO contactDTO) {
        Contact contact = ContactMapper.toEntity(contactDTO);
        contact.setId(uuidService.generateId());
        return contactRepo.save(contact);
    }

    public void deleteContactById(String id){
        contactRepo.deleteById(id);
    }

    public Contact updateContact(ContactDTO contactDTO, String id) throws ContactNotFoundException {
        Contact existingContact = this.findContactById(id);

        existingContact.setName(contactDTO.getName().trim());
        existingContact.setCountry(contactDTO.getCountry().trim());
        existingContact.setCity(contactDTO.getCity().trim());
        existingContact.setPlz(contactDTO.getPlz().trim());
        existingContact.setStreet(contactDTO.getStreet().trim());
        existingContact.setStreetNumber(contactDTO.getStreetNumber().trim());
        existingContact.setPhoneNumber(contactDTO.getPhoneNumber().trim());
        existingContact.setEmail(contactDTO.getEmail().trim());
        existingContact.setWebsite(contactDTO.getWebsite().trim());
        existingContact.setComment(contactDTO.getComment().trim());

        return contactRepo.save(existingContact);
    }

}

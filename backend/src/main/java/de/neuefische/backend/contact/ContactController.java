package de.neuefische.backend.contact;

import de.neuefische.backend.exceptions.ContactNotFoundException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contact")
    public List<Contact> getContacts() {
        return contactService.allContacts();
    }

    @GetMapping("/contact/{id}")
    public Contact getContactById(@PathVariable String id) throws ContactNotFoundException {
        return contactService.findContactById(id);
    }

    @PostMapping("/contact")
    public Contact addContact(@Valid @RequestBody ContactDTO contactDTO) {
        return contactService.saveContact(contactDTO);
    }

    @DeleteMapping("/contact/{id}")
    public void deleteContact(@PathVariable String id) {
        contactService.deleteContactById(id);
    }

    @PutMapping("/contact/{id}")
    Contact putContact(@Valid @RequestBody ContactDTO contactDTO
            ,@PathVariable String id) throws ContactNotFoundException {
        return contactService.updateContact(contactDTO, id);
    }
}

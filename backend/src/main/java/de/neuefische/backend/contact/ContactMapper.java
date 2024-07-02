package de.neuefische.backend.contact;



public class ContactMapper {

    public static Contact toEntity(ContactDTO dto) {
        Contact contact = new Contact();
        contact.setName(dto.getName());
        contact.setCountry(dto.getCountry());
        contact.setCity(dto.getCity());
        contact.setPlz(dto.getPlz());
        contact.setStreet(dto.getStreet());
        contact.setStreetNumber(dto.getStreetNumber());
        contact.setPhoneNumber(dto.getPhoneNumber());
        contact.setEmail(dto.getEmail());
        contact.setWebsite(dto.getWebsite());
        contact.setComment(dto.getComment());
        contact.setCompanyId(dto.getCompanyId());
        return contact;
    }

}

package de.neuefische.backend.contact;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContactMapperTest {
    @Test
    void toEntity_whenInputEqualsContactDTO_thenReturnContact() {

        Contact expected = new Contact(null, "John Doe", "Germany", "Berlin", "61355", "DemoStreet", "132", "+492374928349", "test@mail.de", "www.test.de", "comment1235345 comment1235","2345ouoh");

        ContactDTO contactDTO = new ContactDTO("John Doe", "Germany", "Berlin", "61355", "DemoStreet", "132", "+492374928349", "test@mail.de", "www.test.de", "comment1235345 comment1235", "2345ouoh");

        Contact actual = ContactMapper.toEntity(contactDTO);

        assertEquals(actual, expected);
    }
}

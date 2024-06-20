package de.neuefische.backend.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompanyMapperTest {
    @Test
    void toEntity_whenInputEqualsCompanyDTO_thenReturnCompany() {
        Company expected = new Company(null, "John Doe", "Germany", "Berlin", "61355", "DemoStreet", "132", "+492374928349", "test@mail.de", "www.test.de", "comment1235345 comment1235");

        CompanyDTO companyDTO = new CompanyDTO("John Doe", "Germany", "Berlin", "61355", "DemoStreet", "132", "+492374928349", "test@mail.de", "www.test.de", "comment1235345 comment1235");

        Company actual = CompanyMapper.toEntity(companyDTO);

        assertEquals(actual, expected);
    }

}

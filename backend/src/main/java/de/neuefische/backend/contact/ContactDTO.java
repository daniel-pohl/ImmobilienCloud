package de.neuefische.backend.contact;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO {
    @NotBlank(message = "Der Kontaktname darf nicht leer sein.")
    private String name;
    private String country;
    private String city;
    private String plz;
    private String street;
    private String streetNumber;
    private String phoneNumber;
    private String email;
    private String website;
    private String comment;
    private String companyId;
    private boolean favorite;
}


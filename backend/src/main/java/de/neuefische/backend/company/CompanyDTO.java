package de.neuefische.backend.company;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {
    @NotBlank(message = "Der Firmenname darf nicht leer sein.")
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
    private List<String> contactIds;

}

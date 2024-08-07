package de.neuefische.backend.company;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Document(collection = "Companies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    private String id;
    @NotBlank(message = "Der Name darf nicht leer sein.")
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
    private List<String> contactIds = new ArrayList<>();

}

package de.neuefische.backend.contact;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepo extends MongoRepository<Contact, String> {
    List<Contact> findByNameContainingIgnoreCase(String name);
}




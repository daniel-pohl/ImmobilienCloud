package de.neuefische.backend;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UuidService {
    public String generateId() {
        return UUID.randomUUID().toString();
    }

}

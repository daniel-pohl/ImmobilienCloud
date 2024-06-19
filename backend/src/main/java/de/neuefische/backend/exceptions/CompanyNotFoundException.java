package de.neuefische.backend.exceptions;

public class CompanyNotFoundException extends Exception{
    public CompanyNotFoundException(String message) {
        super(message);
    }
}

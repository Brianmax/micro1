package org.example.micro1.service;

import org.example.micro1.entity.PersonaEntity;
import org.springframework.http.ResponseEntity;

public interface PersonaService {
    ResponseEntity<PersonaEntity> create(String dni);
}

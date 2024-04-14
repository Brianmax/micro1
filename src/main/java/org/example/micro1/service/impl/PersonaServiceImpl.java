package org.example.micro1.service.impl;

import org.example.micro1.client.ReniecClient;
import org.example.micro1.entity.PersonaEntity;
import org.example.micro1.service.PersonaService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService {
    private ReniecClient reniecClient;
    @Override
    public ResponseEntity<PersonaEntity> create(String dni) {
        return null;
    }
}

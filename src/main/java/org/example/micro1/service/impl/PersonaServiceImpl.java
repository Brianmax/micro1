package org.example.micro1.service.impl;

import org.example.micro1.client.ReniecClient;
import org.example.micro1.entity.PersonaEntity;
import org.example.micro1.mapper.PersonaMapper;
import org.example.micro1.repository.PersonaRepository;
import org.example.micro1.response.ResponseReniec;
import org.example.micro1.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService {
    @Autowired
    private ReniecClient reniecClient;
    @Autowired
    private PersonaRepository personaRepository;
    @Override
    public ResponseEntity<PersonaEntity> create(String dni) {
        Optional<ResponseReniec> responseReniecOptional = reniecClient.getInfoReniec(dni, "");
        if (responseReniecOptional.isPresent())
        {
            ResponseReniec responseReniec = responseReniecOptional.get();
            Optional<PersonaEntity> personaOptional = personaRepository.findById(dni);
            if(personaOptional.isPresent())
            {
                return ResponseEntity.badRequest().build();
            }
            else
            {
                PersonaEntity personaEntity = PersonaMapper.INSTANCE.toPersonaEntity(responseReniec);
                personaRepository.save(personaEntity);
            }
        }
        return ResponseEntity.ok().build();
    }
}

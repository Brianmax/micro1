package org.example.micro1.repository;

import org.example.micro1.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface PersonaRepository extends JpaRepository<PersonaEntity, String>{
}

package org.example.micro1.controller;

import org.example.micro1.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/persona")
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @GetMapping("/create")
    public String create(@RequestParam String dni) {
        personaService.create(dni);
        return "Persona creada";
    }
}

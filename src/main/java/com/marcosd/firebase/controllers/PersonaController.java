package com.marcosd.firebase.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcosd.firebase.beans.Persona;
import com.marcosd.firebase.service.PersonaService;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {
	
	@Autowired
    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping("/crear")
    public String crearPersonas() {
        personaService.guardarPersonas();
        return "Se han guardado 5 personas en Firebase";
    }
    
    
    @GetMapping("/obtener")
    public Map<String, Persona> obtenerPersonas() {
        return personaService.obtenerPersonas();
    }    
    
    
    @GetMapping("/{id}")
    public Persona obtenerPersonas(String id) {
    	
    	return personaService.obtenerPersonaPorId(id);
    }  
    
}

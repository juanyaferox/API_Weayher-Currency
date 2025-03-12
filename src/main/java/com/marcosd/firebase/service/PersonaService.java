package com.marcosd.firebase.service;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.marcosd.firebase.beans.Persona;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersonaService {

	//la bbdd no se puede inyectar con el autowired aunque sea un bean, osea que se pasa como parametro al constructor y listo.
	
    private final DatabaseReference databaseReference;
    //el /persona es como el schema que vamos a usar.
    private static final String FIREBASE_URL = "https://prueba1-11f3c-default-rtdb.europe-west1.firebasedatabase.app/personas";
    
    public PersonaService(FirebaseDatabase firebaseDatabase) {
        this.databaseReference = firebaseDatabase.getReference("iyg16260"); // Nombre del nodo en Firebase //esto tiene que hacer match con el esquema de arriba
    }

    
    public void guardarPersonas() {
    	
        Map<String, Persona> personas = new HashMap<>();

        for (int i = 1; i <= 5; i++) {
            Persona persona = new Persona(i);
            personas.put("persona" + i, persona);
        }

        databaseReference.setValueAsync(personas);
    }
    public void guardarPersonasList() {
    	
        List<Persona> personas = new ArrayList();

        for (int i = 1; i <= 5; i++) {
            Persona persona = new Persona(i);
            personas.add(persona);
        }

        databaseReference.setValueAsync(personas);
    }

   

    public Map<String, Persona> obtenerPersonas() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Persona> personas = null;
        
        return restTemplate.getForObject(FIREBASE_URL+".json", Map.class);

    }
    
    public Persona obtenerPersonaPorId(String personaId) {
        
    	String url = FIREBASE_URL+"/" + personaId + ".json";
        System.out.println(url);
        
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, Persona.class);
    }
    
    
}
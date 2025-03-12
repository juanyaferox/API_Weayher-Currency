package com.marcosd.firebase.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @Bean
     FirebaseApp firebaseApp() throws IOException {
    	
        // Evita inicializar Firebase más de una vez
        if (FirebaseApp.getApps().isEmpty()) {
            // Cargar el archivo serviceAccountKey.json desde resources
            ClassLoader classLoader = getClass().getClassLoader();
            try (InputStream serviceAccount = classLoader.getResourceAsStream("serviceAccountKey-mine.json")) {
                
                // Construir las opciones de Firebase
                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .setDatabaseUrl("https://prueba-893ed-default-rtdb.europe-west1.firebasedatabase.app")
                        .build();

                // Inicializar la aplicación de Firebase
                return FirebaseApp.initializeApp(options);
            }
        } else {
            // Si ya está inicializado, lo reutilizamos
            return FirebaseApp.getInstance();
        }
    }

    @Bean
     FirebaseDatabase firebaseDatabase(FirebaseApp firebaseApp) {
        // Obtenemos la instancia de la base de datos a partir del FirebaseApp
        return FirebaseDatabase.getInstance(firebaseApp);
    }
}

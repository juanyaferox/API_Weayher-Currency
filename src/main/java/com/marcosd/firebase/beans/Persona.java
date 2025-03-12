package com.marcosd.firebase.beans;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Persona {
	
		private Integer id;
		private String nombre;
		private String apellidos;
		private Direccion direccion;
		private String telefono;
		private String hoy;
		
		
		public Persona(int i) {
			
			id = i;
			nombre = "nombre"+i;
			apellidos = "apellidos"+i;
			direccion = new Direccion(i);
			telefono = "telefono"+i;
			hoy = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

		}		
		
}

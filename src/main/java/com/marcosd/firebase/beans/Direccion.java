package com.marcosd.firebase.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class Direccion {

	private String calle;
	private Integer numero;
	private String poblacion;
	private String cp;
	
	public Direccion() {
		
	}
	
	public Direccion(int i) {
		
		calle = "calle"+i;
		numero = i;
		poblacion = "poblacion"+i;	
		cp = "cp"+i;	
	}
	

}

package com.marcosd.firebase.beans;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Convrates {
	
	@JsonProperty("USD")
	String USD;
	
	@JsonProperty("AUD")
	String AUD;
	
	@JsonProperty("BRL")
	String BRL;
	  
	@JsonProperty("EUR")
	String EUR;
}

package com.marcosd.firebase.beans;

import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Currency {
	String hour = LocalDateTime.now().toString();
	String base_code;
	Convrates conversion_rates;
}

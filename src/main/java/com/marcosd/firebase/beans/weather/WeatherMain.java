package com.marcosd.firebase.beans.weather;

import lombok.Data;

@Data
public class WeatherMain {
	String temp;
	String feels_like;
	String temp_min;
	String temp_max;
	String pressure;
}

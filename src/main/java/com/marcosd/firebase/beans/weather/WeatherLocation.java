package com.marcosd.firebase.beans.weather;

import lombok.Data;

@Data
public class WeatherLocation {
	String name;
	String lat;
	String lon;
	String country;
	String state;
}

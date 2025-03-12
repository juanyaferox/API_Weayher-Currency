package com.marcosd.firebase.beans.weather;

import java.util.List;

import lombok.Data;

@Data
public class Weather {
	List<WeatherWeather> weather;
	WeatherMain main;
	String name;
	String cod;
}

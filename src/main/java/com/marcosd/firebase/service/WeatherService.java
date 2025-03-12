package com.marcosd.firebase.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.marcosd.firebase.beans.weather.Weather;
import com.marcosd.firebase.beans.weather.WeatherLocation;

@Service
public class WeatherService {

	@Value("${openweather.api.key}")
	private String apiKey;
	
	private final DatabaseReference dbRef;
	private final String FDB_URL = "https://prueba-893ed-default-rtdb.europe-west1.firebasedatabase.app/weather";
	
	public WeatherService(FirebaseDatabase fDb) {
		this.dbRef = fDb.getReference("weather");
	}
	
	// Crea un nodo para la temperatura de la ciudad, si ya existia justo de esa ciudad la aplasta
	public void saveWeather(Weather weather) {
		 dbRef.child("weather" + weather.getName())
         .setValueAsync(weather);
	}
	
	// Crea un nodo para la localizacion de la ciudad, si ya existia justo de esa ciudad la aplasta
	public void saveLocation(WeatherLocation location) {
		dbRef.child("location"+location.getName()).setValueAsync(location);
	}
	
	// Se conecta a la api devuelve un mapeado del json a la clase WeatherLocation (es un array debido a que devuelve varios) 
	public WeatherLocation[] getGeoLocation(String cityName, String country) {
		
		String query = cityName;
		query = country == null ? query : query+","+country;
		
		String url = "https://api.openweathermap.org/geo/1.0/direct?q="+query
				+"&limit="+5+"&appid="+apiKey;
		
		RestTemplate rt = new RestTemplate();
		
		return rt.getForObject(url, WeatherLocation[].class);
	}
	
	// Se conecta a la api devuelve un mapeado del json a la clase Weather
	public Weather getWeather(WeatherLocation wl) {
		
		//https://api.openweathermap.org/data/3.0/onecall?lat={lat}&lon={lon}&exclude={part}&appid={API key}
		String query="lat="+wl.getLat()+"&lon="+wl.getLon()+"&units=metric"+"&lang=es&appid="+apiKey;
		String url = "https://api.openweathermap.org/data/2.5/weather?"+query;
		
		RestTemplate rt = new RestTemplate();
		
		return rt.getForObject(url, Weather.class);
	}
	
	// Para obtener todos los elementos de la bbdd
	public Map<String, Weather> getWeathersDB(){
		RestTemplate rt = new RestTemplate();
		return rt.getForObject(FDB_URL+".json", Map.class);
	}
	
	// Retorna la ciudad
	public Weather getWeatherDB(String cityName) {
		RestTemplate rt = new RestTemplate();
		String url = FDB_URL+"/weather"+cityName+".json";
		return rt.getForObject(url, Weather.class);
	}
}

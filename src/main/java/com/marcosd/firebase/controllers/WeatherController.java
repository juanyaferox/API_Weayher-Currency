package com.marcosd.firebase.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.marcosd.firebase.beans.weather.Weather;
import com.marcosd.firebase.beans.weather.WeatherLocation;
import com.marcosd.firebase.service.WeatherService;

@RestController
@RequestMapping("api/weather")
public class WeatherController {
	
	@Autowired
	WeatherService ws;
	
	/**
	 * REQUEST API
	 */

	/**
	 * 
	 * @param cityName
	 * @param country opcional debido a la sobrecarga de metodo
	 * @return cordenadas en json de la ciudad
	 */
	@GetMapping("/location/{cityName}/{country}")
	public WeatherLocation[] getLoc(@PathVariable String cityName, @PathVariable String country) {
		var location = ws.getGeoLocation(cityName, country);
		ws.saveLocation(location[0]);
		return location;
	}
	
	@GetMapping("/location/{cityName}")
	public WeatherLocation[] getLoc(@PathVariable String cityName) {
		var location = ws.getGeoLocation(cityName, null);
		ws.saveLocation(location[0]);
		return location;
	}
	
	/**
	 * 
	 * @param cityName 
	 * @param country opcional debido a la sobrecarga de metodo
	 * @return datos de temperatura (en base a la clase Weather) en json de la ciudad
	 */
	@GetMapping("/{cityName}/{country}")
	public Weather getWeth(@PathVariable String cityName, @PathVariable String country) {
		var weather = ws.getWeather(ws.getGeoLocation(cityName, country)[0]);
		ws.saveWeather(weather);
		return weather;
	}
	
	@GetMapping("/{cityName}")
	public Weather getWeth(@PathVariable String cityName) {
		var weather = ws.getWeather(ws.getGeoLocation(cityName, null)[0]);
		ws.saveWeather(weather);
		return weather;
	}
	
	@GetMapping("/db")
	public Map<String, Weather> getWethDB() {
		return ws.getWeathersDB();
	}
	
	@GetMapping("/db/{cityName}")
	public Weather getWethDB(@PathVariable String cityName){
		return ws.getWeatherDB(cityName);
	}
	
	/**
	 * MVC
	 */
	
	/**
	 * 
	 * @param cityName
	 * @param country
	 * @return añade a la vista los datos de la temperatura
	 */
	@PostMapping
	public ModelAndView setWeth(@RequestParam String cityName, @RequestParam String country) {
		var view = new ModelAndView("weather");
		var weather = ws.getWeather(ws.getGeoLocation(cityName, country)[0]);
		view.addObject(weather);
		ws.saveWeather(weather);
		return view;
	}
	
	/**
	 * Pagina formulario
	 * @return
	 */
	@GetMapping
	public ModelAndView getPage() {
		return new ModelAndView("weather");
	}
}

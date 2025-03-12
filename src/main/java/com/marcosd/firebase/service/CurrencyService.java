package com.marcosd.firebase.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.marcosd.firebase.beans.Convrates;
import com.marcosd.firebase.beans.Currency;
import com.marcosd.firebase.beans.Persona;

@Service
public class CurrencyService {
	
	@Value("${exchangerate.api.key}")
	private String apiKey;
	
	private final DatabaseReference databaseReference;
	
	private final RestTemplate rt;
	
	private final String FDB_URL = "https://prueba-893ed-default-rtdb.europe-west1.firebasedatabase.app/monedas";
	
	public CurrencyService(FirebaseDatabase fd) {
		this.databaseReference = fd.getReference("monedas");
		this.rt = new RestTemplate();
	}
	
	// Crea un nodo para la moneda actual, si ya existia justo esa conversion la aplasta
	public void saveCurrency(Currency currency) {
	    databaseReference.child("currency" + currency.getBase_code()).setValueAsync(currency);
	}
	
	// Se conecta a la api devuelvo un mapeado del json a la clase Currency 
	public Currency setCurrency(String currency) {
		String url = "https://v6.exchangerate-api.com/v6/"+apiKey+"/latest/";
		String urlAPI = url+currency;
		return rt.getForObject(urlAPI, Currency.class);
	}
	
	// Se conecta a la bbdd y retorna todos los items
	public Map<String, Currency> getCurrency() {
        Map<String, Currency> currencies = null;
        return rt.getForObject(FDB_URL+".json", Map.class);

    }
    
	// No funciona debido a la diferencia de mayuscula en los currencyName, la api requiere que esten en mayuscula pero firedb
	// los guarda en minuscula. Se soluciona usando un mapper y asignado los nombres de las propiedades manualmente
    public Currency getCurrencyByCode(String currencyName) {
    	String url = FDB_URL+"/" + "currency"+currencyName + ".json";        
        var currency = rt.getForObject(url, Currency.class);
        return currency;
    }
	
}

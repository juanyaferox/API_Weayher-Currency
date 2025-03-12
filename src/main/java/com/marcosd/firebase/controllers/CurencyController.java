package com.marcosd.firebase.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.marcosd.firebase.beans.Currency;
import com.marcosd.firebase.service.CurrencyService;

@RestController
@RequestMapping("/api/currency")
public class CurencyController {
	
	@Autowired
	CurrencyService cs;
	
	@GetMapping("/get/{base_code}")
	public Currency getCurrencies(@PathVariable String base_code) {
		var currency = cs.setCurrency(base_code);
		cs.saveCurrency(currency);
		return currency;
	}
	
	@GetMapping
	public ModelAndView getPage() {
		return new ModelAndView("currency");
	}
	
	@PostMapping
	public ModelAndView setCurrencies(@RequestParam String base_code) {
		var view = new ModelAndView("currency");
		var currency = cs.setCurrency(base_code);
		cs.saveCurrency(currency);
		view.addObject(currency);
		return view;
	}
	
	@GetMapping("/getDB")
	public Map<String,Currency> getCurrenciesDB() {
		return cs.getCurrency();
	}
	
	@GetMapping("/getDB/{base_code}")
	public Currency getCurrenciesDB(@PathVariable String base_code) {
		return cs.getCurrencyByCode(base_code);
	}
}

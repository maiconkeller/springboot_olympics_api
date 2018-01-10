package com.ciandt.olympics.controllers;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ciandt.olympics.model.Country;
import com.ciandt.olympics.response.Response;
import com.ciandt.olympics.services.CountryService;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
	
	@Autowired
	private CountryService countryService;
	
	private final static Logger logger = Logger.getLogger(CountryController.class);
	
	@GetMapping
	public ResponseEntity<Response<List<Country>>> findAll() {
		logger.info("GET: find all countries...");
		
		Response<List<Country>> response = new Response<List<Country>>();

		List<Country> countries = countryService.findAll();
		if (countries == null || countries.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		response.setData(countries);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("{code}")
	public ResponseEntity<Response<Country>> findOneByCode(@PathVariable("code") String code) {
		logger.info("GET/{code}: find one country by country code: " + code);
		
		Response<Country> response = new Response<Country>();

		Country country = countryService.findByCode(code);
		if (country == null) {
			response.getErrors().add("Country code not found");
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(country);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<Response<Country>> save(@Valid @RequestBody Country country, BindingResult result) {
		logger.info("POST: save one new country...");
		
		Response<Country> response = new Response<Country>();
		
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		try {
			response.setData(countryService.save(country));
			return ResponseEntity.ok(response);
		} catch (DataIntegrityViolationException e) {
			response.getErrors().add(e.getMostSpecificCause().getMessage());
			return ResponseEntity.badRequest().body(response);
		} catch (Exception ex) {
			response.getErrors().add(ex.getCause().getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@PutMapping("{code}")
	public ResponseEntity<Response<Country>> updateByCode(@PathVariable("code") String code, @Valid @RequestBody Country country, BindingResult result) {
		logger.info("GET/{code}: find one country by country code: " + code);
		
		Response<Country> response = new Response<Country>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		try {
			Country updCountry = countryService.findByCode(code);
			if (updCountry == null) {
				response.getErrors().add("Country code not found");
				return ResponseEntity.badRequest().body(response);
			}
			
			updCountry.setCode(country.getCode());
			updCountry.setName(country.getName());
			response.setData(countryService.save(updCountry));			
			return ResponseEntity.ok(response);
			
		} catch (DataIntegrityViolationException e) {
			response.getErrors().add(e.getMostSpecificCause().getMessage());
			return ResponseEntity.badRequest().body(response);
		} catch (Exception ex) {
			response.getErrors().add(ex.getCause().getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@DeleteMapping("{code}")
	public ResponseEntity<Response<Country>> deleteByCode(@PathVariable("code") String code) {
		logger.info("DELETE/{code}: delete one country by country code: " + code);
		
		Response<Country> response = new Response<Country>();

		try {
			boolean deleted = countryService.deleteByCode(code);
			if (!deleted) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok().build();			
		} catch (Exception e) {
			response.getErrors().add(e.getCause().getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
	

}

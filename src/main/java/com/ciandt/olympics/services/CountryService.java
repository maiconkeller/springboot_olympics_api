package com.ciandt.olympics.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciandt.olympics.model.Country;
import com.ciandt.olympics.repositories.CountryRepository;

@Service
public class CountryService {

	@Autowired
	private CountryRepository countryRepository;
	
	public List<Country> findAll() {
		return countryRepository.findAll();
	}

	public Country save(Country country) {
		return countryRepository.save(country);
	}

	public Country findByCode(String code) {
		return countryRepository.findByCode(code);
	}

	public boolean deleteByCode(String code) {
		return countryRepository.deleteByCode(code) > 0;
	}
	
}

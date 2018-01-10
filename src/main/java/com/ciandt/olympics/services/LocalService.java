package com.ciandt.olympics.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciandt.olympics.model.Local;
import com.ciandt.olympics.repositories.LocalRepository;

@Service
public class LocalService {

	@Autowired
	private LocalRepository localRepository;
	
	public List<Local> findAll() {
		return localRepository.findAll();
	}

	public Local save(Local local) {
		return localRepository.save(local);
	}

	public Local findById(Long id) {
		return localRepository.findOne(id);
	}

	public boolean deleteById(Long id) {
		return localRepository.deleteById(id) > 0;
	}
	
}

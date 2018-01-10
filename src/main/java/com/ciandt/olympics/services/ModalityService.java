package com.ciandt.olympics.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciandt.olympics.model.Modality;
import com.ciandt.olympics.repositories.ModalityRepository;

@Service
public class ModalityService {

	@Autowired
	private ModalityRepository modalityRepository;
	
	public List<Modality> findAll() {
		return modalityRepository.findAll();
	}

	public Modality save(Modality modality) {
		return modalityRepository.save(modality);
	}

	public Modality findById(Long id) {
		return modalityRepository.findOne(id);
	}

	public boolean deleteById(Long id) {
		return modalityRepository.deleteById(id) > 0;
	}
	
}

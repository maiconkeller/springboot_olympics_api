package com.ciandt.olympics.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciandt.olympics.model.Competition;
import com.ciandt.olympics.model.Modality;
import com.ciandt.olympics.repositories.CompetitionRepository;

@Service
public class CompetitionService {

	@Autowired
	private CompetitionRepository competitionRepository;
	
	public List<Competition> findAll(Optional<Long> modalityId) {
		if (modalityId.isPresent()) {
			Modality modality = new Modality(modalityId.get(), null);
			return competitionRepository.findByModality(modality);
		}
		return competitionRepository.findAll();
	}

}

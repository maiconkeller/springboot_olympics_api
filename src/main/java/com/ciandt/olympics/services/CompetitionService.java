package com.ciandt.olympics.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciandt.olympics.model.Competition;
import com.ciandt.olympics.model.Local;
import com.ciandt.olympics.model.Modality;
import com.ciandt.olympics.model.enums.Phase;
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

	public Competition save(Competition competition) throws Exception {
		// proibir competições simultaneas da mesma modalidade no mesmo local
		if (this.existsByModalityAndLocalAndDateAndTimeStartLessThanEqualAndTimeEndGreaterThanEqual(competition.getModality(), competition.getLocal(), competition.getDate(), competition.getTimeEnd(), competition.getTimeStart())) {
			throw new Exception("There is already a competition scheduled at this location and for this period"); //Já existe uma competição agendada neste local e para este período
		}
		
		// proibir competições entre o mesmo país nas fases iniciais
		if (competition.getCountry1().getId().equals(competition.getCountry2().getId()) && 
				competition.getPhase() != Phase.SEMIFINALS && competition.getPhase() != Phase.FINAL) {
			throw new Exception("Competitions between the same countries are not permitted at this stage of the competition");
		}
		
		// limite mínimo de 30 minutos por competição
		if (competition.getTimeStart().plusMinutes(30).isAfter(competition.getTimeEnd())) {
			throw new Exception("This competition does not reach the mandatory time of 30 minutes");
		}
		
		// limitar no máximo 4 competições por local
		if (this.countByLocalAndDate(competition.getLocal(), competition.getDate()) >= 4) {
			throw new Exception("No more than 4 competitions are allowed on the same place and date");
		}

		return competitionRepository.save(competition);
	}

	private boolean existsByModalityAndLocalAndDateAndTimeStartLessThanEqualAndTimeEndGreaterThanEqual(Modality modality, Local local, LocalDate date, LocalTime timeEnd, LocalTime timeStart) {
		return competitionRepository.existsByModalityAndLocalAndDateAndTimeStartLessThanEqualAndTimeEndGreaterThanEqual(modality, local, date, timeEnd, timeStart);
	}

	public int countByLocalAndDate(Local local, LocalDate date) {
		return competitionRepository.countByLocalAndDate(local, date);
	}

}

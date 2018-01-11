package com.ciandt.olympics.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ciandt.olympics.dto.CompetitionDto;
import com.ciandt.olympics.model.Competition;
import com.ciandt.olympics.response.Response;
import com.ciandt.olympics.services.CompetitionService;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/competitions")
public class CompetitionController {
	
	@Autowired
	private CompetitionService competitionService;
	
	private final static Logger logger = Logger.getLogger(CompetitionController.class);

	@GetMapping
	public ResponseEntity<Response<List<Competition>>> findAll(
			@ApiParam(name="modalityId", value="Modality id", required=false)
			@RequestParam(name="modalityId", required=false) 
			Optional<Long> modalityId) {
		logger.info("GET: find all competitions...");
		
		Response<List<Competition>> response = new Response<List<Competition>>();

		List<Competition> competitions = competitionService.findAll(modalityId);
		if (competitions == null || competitions.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		response.setData(competitions);
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<Response<Competition>> save(@Valid @RequestBody CompetitionDto competitionDto, BindingResult result) {
		logger.info("POST: save one new competition...");
		
		Response<Competition> response = new Response<Competition>();
		
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		try {
			response.setData(competitionService.save(this.dtoToCompetition(competitionDto)));
			return ResponseEntity.ok(response);
		} catch (DataIntegrityViolationException e) {
			response.getErrors().add(e.getMostSpecificCause().getMessage());
			return ResponseEntity.badRequest().body(response);
		} catch (Exception ex) {
			logger.error(ex);
			response.getErrors().add(ex.getCause()!=null?ex.getCause().getLocalizedMessage() : ex.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	private Competition dtoToCompetition(CompetitionDto dto) {
		Competition competition = new Competition(dto.getModalityId(), dto.getLocalId(), dto.getDate(), dto.getTimeStart(), 
				dto.getTimeEnd(), dto.getCountryId1(), dto.getCountryId2(), dto.getPhase());
		return competition;
	}
	
	
}

package com.ciandt.olympics.controllers;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	
	
}

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

import com.ciandt.olympics.model.Modality;
import com.ciandt.olympics.response.Response;
import com.ciandt.olympics.services.ModalityService;

@RestController
@RequestMapping("/api/modalities")
public class ModalityController {
	
	@Autowired
	private ModalityService modalityService;
	
	private final static Logger logger = Logger.getLogger(ModalityController.class);
	
	@GetMapping
	public ResponseEntity<Response<List<Modality>>> findAll() {
		logger.info("GET: find all modalities...");
		
		Response<List<Modality>> response = new Response<List<Modality>>();

		List<Modality> modalities = modalityService.findAll();
		if (modalities == null || modalities.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		response.setData(modalities);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Response<Modality>> findById(@PathVariable("id") Long id) {
		logger.info("GET/{id}: find one modality by modality id: " + id);
		
		Response<Modality> response = new Response<Modality>();

		Modality modality = modalityService.findById(id);
		if (modality == null) {
			response.getErrors().add("Modality id not found");
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(modality);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<Response<Modality>> save(@Valid @RequestBody Modality modality, BindingResult result) {
		logger.info("POST: save one new modality...");
		
		Response<Modality> response = new Response<Modality>();
		
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		try {
			response.setData(modalityService.save(modality));
			return ResponseEntity.ok(response);
		} catch (DataIntegrityViolationException e) {
			response.getErrors().add(e.getMostSpecificCause().getMessage());
			return ResponseEntity.badRequest().body(response);
		} catch (Exception ex) {
			response.getErrors().add(ex.getCause().getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Response<Modality>> updateById(@PathVariable("id") Long id, @Valid @RequestBody Modality modality, BindingResult result) {
		logger.info("GET/{id}: find one modality by modality id: " + id);
		
		Response<Modality> response = new Response<Modality>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		try {
			Modality updModality = modalityService.findById(id);
			if (updModality == null) {
				response.getErrors().add("Modality id not found");
				return ResponseEntity.badRequest().body(response);
			}
			
			updModality.setName(modality.getName());
			response.setData(modalityService.save(updModality));			
			return ResponseEntity.ok(response);
			
		} catch (DataIntegrityViolationException e) {
			response.getErrors().add(e.getMostSpecificCause().getMessage());
			return ResponseEntity.badRequest().body(response);
		} catch (Exception ex) {
			response.getErrors().add(ex.getCause().getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Response<Modality>> deleteById(@PathVariable("id") Long id) {
		logger.info("DELETE/{id}: delete one modality by modality id: " + id);
		
		Response<Modality> response = new Response<Modality>();

		try {
			boolean deleted = modalityService.deleteById(id);
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

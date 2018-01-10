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

import com.ciandt.olympics.model.Local;
import com.ciandt.olympics.response.Response;
import com.ciandt.olympics.services.LocalService;

@RestController
@RequestMapping("/api/locals")
public class LocalController {
	
	@Autowired
	private LocalService localService;
	
	private final static Logger logger = Logger.getLogger(LocalController.class);
	
	@GetMapping
	public ResponseEntity<Response<List<Local>>> findAll() {
		logger.info("GET: find all locals...");
		
		Response<List<Local>> response = new Response<List<Local>>();

		List<Local> locals = localService.findAll();
		if (locals == null || locals.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		response.setData(locals);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Response<Local>> findById(@PathVariable("id") Long id) {
		logger.info("GET/{id}: find one local by local id: " + id);
		
		Response<Local> response = new Response<Local>();

		Local local = localService.findById(id);
		if (local == null) {
			response.getErrors().add("Local id not found");
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(local);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<Response<Local>> save(@Valid @RequestBody Local local, BindingResult result) {
		logger.info("POST: save one new local...");
		
		Response<Local> response = new Response<Local>();
		
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		try {
			response.setData(localService.save(local));
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
	public ResponseEntity<Response<Local>> updateById(@PathVariable("id") Long id, @Valid @RequestBody Local local, BindingResult result) {
		logger.info("GET/{id}: find one local by local id: " + id);
		
		Response<Local> response = new Response<Local>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		try {
			Local updLocal = localService.findById(id);
			if (updLocal == null) {
				response.getErrors().add("Local id not found");
				return ResponseEntity.badRequest().body(response);
			}
			
			updLocal.setName(local.getName());
			response.setData(localService.save(updLocal));			
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
	public ResponseEntity<Response<Local>> deleteById(@PathVariable("id") Long id) {
		logger.info("DELETE/{id}: delete one local by local id: " + id);
		
		Response<Local> response = new Response<Local>();

		try {
			boolean deleted = localService.deleteById(id);
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

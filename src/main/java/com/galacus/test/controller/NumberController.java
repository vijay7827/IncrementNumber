package com.galacus.test.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galacus.test.model.Numbers;
import com.galacus.test.service.NumberService;

@RestController
@RequestMapping("/api/v1/numbers")
public class NumberController {
	
	@Autowired
	private NumberService numberService;
	
	@PostMapping
	private ResponseEntity<?> createNumber() {
		numberService.addNewNumber();
		return ResponseEntity.ok().build();
		
	}
	
	@PutMapping("/{id}")
	private ResponseEntity<Numbers> getNumber(@PathVariable Long id) {
		Numbers numbers = numberService.increaseExistingNumberById(id);
		if(Objects.isNull(numbers)) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.ok().body(numbers);
	}
	

}

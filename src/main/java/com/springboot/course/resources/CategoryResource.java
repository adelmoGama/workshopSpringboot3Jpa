package com.springboot.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.course.entities.Category;
import com.springboot.course.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
	
	@Autowired
	private CategoryService service;
	
	@GetMapping
	public ResponseEntity<List<Category>> findAll() {
		List<Category> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	// PARA RECEBER PARÂMETROS DE URL É NECESSÁRIO DECLARAR O @PathVariable.
	// SEM TRATAR A EXCEÇÃO ELE RETORNA UM ERRO 500
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		Category category = service.findById(id);
		return ResponseEntity.ok().body(category);
	}
}

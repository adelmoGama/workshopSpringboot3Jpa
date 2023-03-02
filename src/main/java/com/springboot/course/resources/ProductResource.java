package com.springboot.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.course.entities.Product;
import com.springboot.course.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
	
	@Autowired
	private ProductService service;
	
	@GetMapping
	public ResponseEntity<List<Product>> findAll() {
		List<Product> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	// PARA RECEBER PARÂMETROS DE URL É NECESSÁRIO DECLARAR O @PathVariable.
	// SEM TRATAR A EXCEÇÃO ELE RETORNA UM ERRO 500
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		Product product = service.findById(id);
		return ResponseEntity.ok().body(product);
	}
}

package com.springboot.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.springboot.course.entities.User;
import com.springboot.course.repositories.UserRepository;
import com.springboot.course.services.exceptions.DatabaseException;
import com.springboot.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> user = repository.findById(id);
		return user.orElseThrow(()-> new ResourceNotFoundException(id));
	}
	
	public User insert(User user) {
		return repository.save(user);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		// TRATAMENTO DE ERROR PARA ID NÃO EXISTENTE - RETORNANDO UM 404.
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		// TRATAMENTO DE ERROR PARA ID QUE TENHA DADOS, ORDENS, NO BANCO DE DADOS - RETORNANDO UM 400.
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public User update(Long id, User user) {
		try {
			User userEntity = repository.getReferenceById(id);
			updateData(userEntity, user);
			return repository.save(userEntity);
		// TRATAMENTO DE ERROR PARA ID NÃO EXISTENTE - RETORNANDO UM 404. DIFERENTE DO CASO ANTERIOR AQUI CAPTURAMOS UMA 'EntityNotFoundException'.
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(User userEntity, User user) {
		userEntity.setName(user.getName());
		userEntity.setEmail(user.getEmail());
		userEntity.setPhone(user.getPhone());
	}
}
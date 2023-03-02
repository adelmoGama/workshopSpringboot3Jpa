package com.springboot.course.services.exceptions;

/*
 * CLASSE PARA TRATAR OS ERROS DO PROGRAMA E IMPEDIR QUE CAUSEM ERROS DO TIPO 500 E QUEBREM O SISTEMA.
 */

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(Object id) {
		super("Resource not found. Id " + id);
	}

}

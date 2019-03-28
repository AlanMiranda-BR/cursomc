package com.cursospring.cursomc.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
	private static final long serialVersionUID = 1L;
	
	//Atributos
	private List<FieldMessage> errors = new ArrayList<>();
	
	//Construtor de SuperClasse
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
	}

	
	//Getters e Setters  (obs. setList() alterado para addErrors() e modificado conteúdo)
	public List<FieldMessage> getErrors() {
		return errors; 
	}

	public void addErrors(String fieldName, String message){
		errors.add(new FieldMessage(fieldName, message));
	}

}

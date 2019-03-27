package com.cursospring.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursospring.cursomc.domain.Categoria;
import com.cursospring.cursomc.repositories.CategoriaRepository;
import com.cursospring.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	// Método de busca de registros
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException( //Expressão lambda para lançar uma excepiton 
				"Objeto com ID: "+ id +" do TIPO: "+ Categoria.class.getName()+ " não foi encontrado!"));
	}
	// Método de insersão de novos registros	
	public Categoria insert(Categoria obj) {
		obj.setId(null); //Garante que o objeto novo tem ID nulo, assim o repo.save garante que é uma inserção e não atualização.
		return repo.save(obj); //Chama a função save do Repository
	}
	
	// Método de atualização de registros
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj); 	//Chama a função save do Repository
	}
	
	
}

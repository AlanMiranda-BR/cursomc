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
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException( //Expressão lambda para lançar uma excepiton 
				"Objeto com ID: "+ id +" do TIPO: "+ Categoria.class.getName()+ " não foi encontrado!"));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null); //Garante que o objeto novo tem ID nulo, assim o repo.save garante que é uma inserção e não atualização.
		return repo.saveAndFlush(obj); //Chama a função save do Repository
	}
}

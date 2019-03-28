package com.cursospring.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cursospring.cursomc.domain.Categoria;
import com.cursospring.cursomc.repositories.CategoriaRepository;
import com.cursospring.cursomc.services.exceptions.DataIntegrityException;
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
		find(obj.getId()); // Garante que o ID fornecido foi encontrado
		return repo.save(obj); 	//Chama a função save do Repository
	}
	
	// Método que deleta um registro da tabela
	public void delete(Integer id) { 
		find(id); // Garante que o ID fornecido foi encontrado
		try {
		repo.deleteById(id); // Chama a função delete do Repository
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
		}
	}
	
	// Método que Retorna todos os registros da tabela
	public List<Categoria> findAll(){
		return repo.findAll();
	}
	
}

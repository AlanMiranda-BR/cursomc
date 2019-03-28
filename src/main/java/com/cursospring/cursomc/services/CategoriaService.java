package com.cursospring.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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
	
	/**
	 * Busca os registros da tabela e realiza a paginação (Separa o resultado por quantidade e por ordem)
	 * @param page Informa qual a página que se quer mostrar.
	 * @param linesPerPage Determina quantas linhas se quer mostrar por págine
	 * @param orderBy Determina por qual atributo se quer ordenar os registros
	 * @param direction Determina qual a ordem da ordenação (Cresc ou Decr)
	 * @return Um objeto PageRequest com o conteúdo buscado
	 */
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
}

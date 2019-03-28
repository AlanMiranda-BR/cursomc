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
import com.cursospring.cursomc.dto.CategoriaDTO;
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
		Categoria newObj = find(obj.getId()); // Garante que o ID fornecido foi encontrado
		updateData(newObj, obj);
		return repo.save(newObj); 	//Chama a função save do Repository
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
	
	/**
	 * Método auxiliar que instancia um objeto da Classe principal (Categoria) a partir de um objetoDTO. Usado para o proceso de validação.
	 * @param objDTO 
	 * @return um objeto Categoria a partir dos atributos de um objeto CategoriaDTO.
	 */
	public Categoria fromDTO(CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(), objDTO.getNome());
	}
	
	/**
	 * Método auxiliar utilizado pelo método UPDATE para atualizar somente os atributos permitidos pelo CategoriaDTO
	 * @param newObj objeto com os dados novos
	 * @param obj objeto, buscado e monitorado pelo JPA, que será atualizado
	 */
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
		newObj.setProdutos(obj.getProdutos());
	}
}

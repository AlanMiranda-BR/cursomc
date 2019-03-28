package com.cursospring.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cursospring.cursomc.domain.Cliente;
import com.cursospring.cursomc.dto.ClienteDTO;
import com.cursospring.cursomc.repositories.ClienteRepository;
import com.cursospring.cursomc.services.exceptions.DataIntegrityException;
import com.cursospring.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	//FIND
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException( // Expressão lambda para lançar uma excepiton
				"Objeto com ID: " + id + " do TIPO: " + Cliente.class.getName() + " não foi encontrado!"));
	}
	//UPDATE
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	//DELETE
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um Cliente que possua pedidos");
		}
	}
	//FindALL
	public List<Cliente> findAll() {
		return repo.findAll();
	}
	//FindPAGE
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	//FromDTO
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}
	//UpdateDATA -> metódo auxiliar do UPDATE, usado para atualizar apenas os atributos do ClienteDTO.
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}

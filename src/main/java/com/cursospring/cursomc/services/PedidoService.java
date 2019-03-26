package com.cursospring.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursospring.cursomc.domain.Pedido;
import com.cursospring.cursomc.repositories.PedidoRepository;
import com.cursospring.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException( //Expressão lambda para lançar uma excepiton 
				"Objeto com ID: "+ id +" do TIPO: "+ Pedido.class.getName()+ " não foi encontrado!"));
	}
}

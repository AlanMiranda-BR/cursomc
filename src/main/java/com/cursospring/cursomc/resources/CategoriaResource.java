package com.cursospring.cursomc.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cursospring.cursomc.domain.Categoria;
import com.cursospring.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET) //Método de request GET que envia como argumento o ID de uma categoria.
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Categoria obj = service.buscar(id);
		
		return ResponseEntity.ok().body(obj); 
	}
	
	//@RequestMapping(method = RequestMethod.POST) //Método de request POST que adiciona uma nova categoria.
	@PostMapping
	//Método responsável por inserir uma nova categoria. O @RequestBody abaixo faz com que o objeto Categoria seja convertido para um Objeto Json automaticamente.
	public ResponseEntity<Void> insert(@RequestBody Categoria obj){ //A resposta retorna uma mensagem com corpo vazio(VOID) mas recebe como argumento um obj da classe Categoria
		obj = service.insert(obj);
		//Boa prática do Spring, retorna a resposta adequada ao request (201 _create successful) juntamente com a nova url e o respectivo ID do objeto criado.
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build(); //retorna a resposta com a URI criada acima.
	}
}

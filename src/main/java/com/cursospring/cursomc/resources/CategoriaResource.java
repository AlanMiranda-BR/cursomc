package com.cursospring.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cursospring.cursomc.domain.Categoria;
import com.cursospring.cursomc.dto.CategoriaDTO;
import com.cursospring.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;
	
	/**
	 * Método reponsável por retornar um registro do banco de dados
	 * @param id do registro que se quer encontrar
	 * @return Objeto com o registro da tabela de id igual ao passado pelo request
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.GET) //Método de request GET que envia como argumento o ID de uma categoria.
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		
		Categoria obj = service.find(id);
		
		return ResponseEntity.ok().body(obj); 
	}
	
	/**
	 * Método responsável por inserir uma nova categoria.
	 * @RequestBody faz com que o objeto Categoria seja convertido para um Objeto Json automaticamente.
	 */
	//@RequestMapping(method = RequestMethod.POST) //Método de request POST que adiciona uma nova categoria.
	@PostMapping //Alternativa mais curta para a notação comentada acima
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDTO){ //A resposta retorna uma mensagem com corpo vazio(VOID) mas recebe como argumento um obj da classe Categoria
		Categoria obj = service.fromDTO(objDTO); //Cria um objeto Categoria a partir de um objeto CategoriaDTO, pois a validação é feita em CategoriaDTO.
		obj = service.insert(obj);
		//Boa prática do Spring, retorna a resposta adequada ao request (201 _create successful) juntamente com a nova url e o respectivo ID do objeto criado.
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build(); //retorna a resposta com a URI criada acima.
	}
	
	/**
	 * Método responsável por atualizar uma categoria existente.
	 * @RequestBody faz com que o objeto Categoria seja convertido para um Objeto Json automaticamente.
	 * @PathVariable indica que um parâmetro de método deve ser ligado a uma variável do URI.
	 */
	//@RequestMapping(value = "/{id}", method = RequestMethod.PUT) //Método de request PUT que atualiza a categoria com Id igual ao argumento.
	@PutMapping(value = "/{id}") //Alternativa mais curta para a notação comentada acima
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDTO, @PathVariable Integer id){
		Categoria obj = service.fromDTO(objDTO); //Cria um objeto Categoria a partir de um objeto CategoriaDTO, pois a validação é feita em CategoriaDTO.
		obj.setId(id); //Garante que o objeto a ser atualizado é o objeto com o Id informado pelo PUT
		obj = service.update(obj);
		return ResponseEntity.noContent().build(); //Constroi e retorna a resposta do Request PUT sem conteúdo no corpo da mensagem.
	}
	
	/**
	 * @param id  ID do registro a ser deletado
	 * @PathVariable indica que um parâmetro de método deve ser ligado a uma variável do URI.
	 * @return Constroi e retorna a resposta do Request PUT sem conteúdo no corpo da mensagem.
	 */
	@DeleteMapping(value = "/{id}") //versão curta do Método de request PUT que deleta a categoria com Id igual ao argumento.
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Método responsável por retornar todos os registros de categoria.
	 * @return Objeto List contendo todos os registros encontrados em categoria
	 */
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAll(){
		List<Categoria> list = service.findAll();
		List<CategoriaDTO> listDTO = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	/**
	 * Método que retorna todos os registros da tabela separado por páginas, conforme os parâmetros informados
	 * @RequestParam indica que o argumento passado é um parametro de request (ex. uri/categoria?page=2&direction=ASC)
	 * @param page Informa qual página quer exibir
	 * @param linesPerPage Determina a quantidade de linhas por página que quer exibir
	 * @param orderBy Determina por qual parâmetro se deseja ordenar a Lista de registros
	 * @param direction Determina em que direção a lista sera ordenada (values = ASC, DESC)
	 * @return retorna como corpo da mensagem de resposta do Request uma lista conforme os parametros.
	 */
	@GetMapping(value = "/page")
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction){
		Page<Categoria> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> listDTO = list.map(obj -> new CategoriaDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
}

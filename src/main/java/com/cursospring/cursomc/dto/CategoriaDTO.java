package com.cursospring.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.cursospring.cursomc.domain.Categoria;

//Classe utilizada para definir os dados que se quer trafegar quando fizer operações CRUD
public class CategoriaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//Apenas os atributos puros da entidade Categoria são criados no DTO
	private Integer id;
	
	@NotEmpty(message = "Preenchimento Obrigtatório") //Constraint (Restrição) de campo vazio, usado pelo @valid no @RequestBody do resource(Controller)
	@Length(min = 5,max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres") //Constrain (Restrição) de quantidade de caracteres usado no @valid no @RequestBody do resource(Controller)
	private String nome;
	
	//Construtor de classes DTO devem ser sempre vazio pois são usados pelas bibliotecas do Java
	public CategoriaDTO() {}
	
	public CategoriaDTO(Categoria obj) {
		id = obj.getId();
		nome = obj.getNome();
	}

	//Getters e Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}

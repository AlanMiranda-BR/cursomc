package com.cursospring.cursomc.domain;

import javax.persistence.Entity;

import com.cursospring.cursomc.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("pagamentoComCartao") //Envia, ta requisição, um campo @type com o nome pagamentoComCartao, para que o Json possa instanciar um objeto PagamentoComCartao
public class PagamentoComCartao extends Pagamento{
	private static final long serialVersionUID = 1L;
	
	//Atributos da classe
	private Integer numeroDeParcelas;
	
	//Construtor vazio
	public PagamentoComCartao() {}

	//Construtor da super-classe
	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
		super(id, estado, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}

	//Getters e Setters
	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
	
	
}

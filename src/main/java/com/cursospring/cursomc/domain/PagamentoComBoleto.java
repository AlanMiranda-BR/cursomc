package com.cursospring.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.cursospring.cursomc.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("pagamentoComBoleto") //Envia, ta requisição, um campo @type com o nome pagamentoComBoleto, para que o Json possa instanciar um objeto PagamentoComBoleto 
public class PagamentoComBoleto extends Pagamento{
	private static final long serialVersionUID = 1L;
	
	//Atributos da classe
	@JsonFormat(pattern = "dd/MM/yyyy") //Fomata a resposta Json para o Pattern escolhido, evitando que este dado seja exibido em milissegundos//Fomata a resposta Json para o Pattern escolhido, evitando que este dado seja exibido em milissegundos
	private Date dataVencimento;
	@JsonFormat(pattern = "dd/MM/yyyy") //Fomata a resposta Json para o Pattern escolhido, evitando que este dado seja exibido em milissegundos//Fomata a resposta Json para o Pattern escolhido, evitando que este dado seja exibido em milissegundos
	private Date dataPagamento;
	
	//Construtor vazio
	public PagamentoComBoleto() {}

	//Construtor de super-classe
	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estado, pedido);
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
	}

	//Getters e Setters
	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
}

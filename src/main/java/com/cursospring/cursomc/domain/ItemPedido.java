package com.cursospring.cursomc.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedido implements Serializable{
	private static final long serialVersionUID = 1L;

	//Atributos da classe associativa
	@JsonIgnore //Impede a Serialização (ver aula 27 da seção 2) (Observer que os gets também precisam ser ignorados)
	@EmbeddedId //Notação que informa que o atributo é um ID embutido em um tipo auxiliar.
	private ItemPedidoPK id = new ItemPedidoPK(); //atributo composto para criar PK/FK
	
	
	private Double desconto;
	private Integer quantidade;
	private Double preco;
	
	//Construtor vazio
	public ItemPedido() {}

	//Construtor com primeiro argumento modificado 
	public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
		super();
		id.setPedido(pedido);
		id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	//Getters e Setters
	public double getSubTotal() { //Criado manualmente para calcular o valor total de cada item do pedido
		return (preco - desconto) * quantidade;
	}
	
	@JsonIgnore
	public Pedido getPedido() { //Criado manualmente, precisa impedir a Serialização com o JsonIgnore para evitar referência cíclica
		return id.getPedido();
	}
	
	public Produto getProduto() { //Criado manualmente, permite a Serialização pois é desejavel que os produtos sejam apresentados no Request
		return id.getProduto();
	}
	
	public ItemPedidoPK getId() {
		return id;
	}

	public void setId(ItemPedidoPK id) {
		this.id = id;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	//HashCode e Equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}

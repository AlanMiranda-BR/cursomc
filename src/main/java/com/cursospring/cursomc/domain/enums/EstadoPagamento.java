package com.cursospring.cursomc.domain.enums;

public enum EstadoPagamento {
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
/*Implementação para indices prédefinidos*/
	
	//Atributos da Enum 
	private int cod;
	private String descricao;
	
	//Construtor
	private EstadoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	//Getters
	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	//Função para retornar o Enum
	public static EstadoPagamento toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		for(EstadoPagamento x : EstadoPagamento.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id Inválido " + cod);
	}
}

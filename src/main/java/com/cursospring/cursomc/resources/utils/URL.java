package com.cursospring.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

	// Método para transformar uma String de IDs separado por vírgula (ex. "1,2,4,6") em uma lista de Inteiros
	public static List<Integer> decodeIntList(String s){
		String[] vet = s.split(",");
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < vet.length; i++) {
			list.add(Integer.parseInt(vet[i]));
		}
		return list;
		/*
		A linha abaixo é outra forma de implementar o método decodeIntList através de expressão Lambda (não faço ideia do que é isso e não sei se funciona)
		
		return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
		
		*/
	}
	
	//Método para realizar o decodeURIComponent() do java scrip, ou seja, transformar a string passada como parametro da requisição GET (ex. "Cabe%C3%A7a%20de%20Guid%C3%A3o") em uma String que pode ser utilizada na consulta JPQL do JPA.
	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}

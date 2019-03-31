package com.cursospring.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cursospring.cursomc.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	@Transactional(readOnly = true) //Informa que é somente uma consulta, ou seja, impede que seja realizado transações nete método
	Cliente findByEmail(String email); // habilita busca por email (obs. basta escrever a sintaxe corretamente que o Spring já implementa o método de busca sozinho)
	
}

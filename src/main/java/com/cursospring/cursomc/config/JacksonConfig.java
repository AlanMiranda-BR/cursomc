package com.cursospring.cursomc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.cursospring.cursomc.domain.PagamentoComBoleto;
import com.cursospring.cursomc.domain.PagamentoComCartao;
import com.fasterxml.jackson.databind.ObjectMapper;

//Código padrão, exigencia do Jackson, mais info abaixo.
// fonte: // https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-of-interfaceclass-without-hinting-the-pare
@Configuration
public class JacksonConfig {
	
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(PagamentoComCartao.class); //Subclasse registrada
				objectMapper.registerSubtypes(PagamentoComBoleto.class); //Subclasse registrada
				super.configure(objectMapper);
			}
		};
		return builder;
	}
}

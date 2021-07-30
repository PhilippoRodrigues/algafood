package com.philippo.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.philippo.algafood.AlgafoodApiApplication;
import com.philippo.algafood.domain.model.Cidade;
import com.philippo.algafood.domain.repository.CidadeRepository;

public class ConsultaCidadeMain {
	
	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CidadeRepository cidades = applicationContext.getBean(CidadeRepository.class);
		
		List<Cidade> todasCidades = cidades.listar(); 
		
		for(Cidade cidade: todasCidades)
			System.out.printf("%s - %s\n", cidade.getNome(), cidade.getEstado().getNome());
	}
}

	
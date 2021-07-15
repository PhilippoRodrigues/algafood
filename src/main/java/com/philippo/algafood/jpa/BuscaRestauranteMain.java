package com.philippo.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.philippo.algafood.AlgafoodApiApplication;
import com.philippo.algafood.domain.model.Restaurante;
import com.philippo.algafood.domain.repository.RestauranteRepository;

public class BuscaRestauranteMain {
	
	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
		
		Restaurante restaurante = restauranteRepository.buscar(1L);
		
		System.out.printf("Restaurante: %s - frete: %.2f\n", restaurante.getNome(), restaurante.getTaxaFrete());
	}
}

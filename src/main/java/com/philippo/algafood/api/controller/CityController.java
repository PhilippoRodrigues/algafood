package com.philippo.algafood.api.controller;

import java.util.List;

import com.philippo.algafood.domain.exception.BusinessException;
import com.philippo.algafood.domain.exception.StateNotFoundException;
import com.philippo.algafood.domain.model.City;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.philippo.algafood.domain.repository.CityRepository;
import com.philippo.algafood.domain.service.RegisterCityService;

import javax.validation.Valid;

@RestController
@RequestMapping("/cities")
public class CityController {
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private RegisterCityService registerCity;
	
	@GetMapping
	public List<City> listAllCities() {
		return cityRepository.findAll();
	}
	
	@GetMapping("/{cityId}")
	public City findCity(@PathVariable Long cityId) {
		return registerCity.findOrFail(cityId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public City addCity(@RequestBody @Valid City city) {
		try {
			return registerCity.save(city);
		}catch (StateNotFoundException e){
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@PutMapping("/{cityId}")
	public City update(@PathVariable Long cityId, @RequestBody @Valid City city){

		try {
			City currentCity = registerCity.findOrFail(cityId);

			BeanUtils.copyProperties(city, currentCity, "id");

			return registerCity.save(currentCity);
		}catch (StateNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@DeleteMapping("/{cityId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long cityId){
		registerCity.delete(cityId);
	}
}

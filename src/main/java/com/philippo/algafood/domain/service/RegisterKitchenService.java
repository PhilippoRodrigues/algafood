package com.philippo.algafood.domain.service;

import com.philippo.algafood.domain.exception.KitchenNotFoundException;
import com.philippo.algafood.domain.model.Kitchen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.philippo.algafood.domain.exception.EntityInUseException;
import com.philippo.algafood.domain.repository.KitchenRepository;

@Service
public class RegisterKitchenService {

	public static final String KITCHEN_NOT_FOUND = "There is no kitchen register with code %d";
	public static final String KITCHEN_IN_USE = "The kitchen with code %d could not be deleted because it is in use";

	@Autowired
	private KitchenRepository kitchenRepository;
	
	public Kitchen save(Kitchen kitchen) {
		return kitchenRepository.save(kitchen);
	}
	
	public void delete(Long kitchenId) {
		try {
			kitchenRepository.deleteById(kitchenId);
		} catch (EmptyResultDataAccessException e) {
			throw new KitchenNotFoundException(kitchenId);
		} catch (DataIntegrityViolationException e) {
			 throw new EntityInUseException(String.format(KITCHEN_IN_USE, kitchenId));
		}
	}

	public Kitchen findOrFail(Long kitchenId){
		return kitchenRepository
			.findById(kitchenId)
			.orElseThrow(() -> new KitchenNotFoundException(kitchenId));
	}
}

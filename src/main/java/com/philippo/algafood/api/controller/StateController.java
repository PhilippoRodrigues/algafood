package com.philippo.algafood.api.controller;

import java.util.List;

import com.philippo.algafood.api.assembler.StateInputDisassembler;
import com.philippo.algafood.api.assembler.StateModelAssembler;
import com.philippo.algafood.api.model.StateModel;
import com.philippo.algafood.api.model.input.StateInput;
import com.philippo.algafood.domain.model.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.philippo.algafood.domain.repository.StateRepository;
import com.philippo.algafood.domain.service.RegisterStateService;

import javax.validation.Valid;

@RestController
@RequestMapping("/states")
public class StateController {
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private RegisterStateService registerState;

	@Autowired
	private StateModelAssembler stateModelAssembler;

	@Autowired
	private StateInputDisassembler stateInputDisassembler;
	
	@GetMapping
	public List<StateModel> listAllStates(){
		return stateModelAssembler.toCollectionModel(stateRepository.findAll());
	}
	
	@GetMapping("/{stateId}")
	public StateModel findState(@PathVariable Long stateId){
		State state = registerState.findOrFail(stateId);

		return stateModelAssembler.toModel(state);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public StateModel addState(@RequestBody @Valid StateInput stateInput){
		State state = stateInputDisassembler.toDomainObject(stateInput);

		return stateModelAssembler.toModel(registerState.save(state));
	}
	
	@PutMapping("/{stateId}")
	public StateModel updateState(@PathVariable Long stateId, @RequestBody @Valid StateInput stateInput){
		State currentState = registerState.findOrFail(stateId);

		stateInputDisassembler.copyToDomainObject(stateInput, currentState);

		currentState =  registerState.save(currentState);

		return stateModelAssembler.toModel(currentState);
	}
	
	@DeleteMapping("/{stateId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long stateId){
		registerState.delete(stateId);
	}
}

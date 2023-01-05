package com.devsuperior.bds04.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.EventRepository;
 
@Service
@Transactional(readOnly = true) 
public class EventService {

	@Autowired
	private EventRepository repository;

	@Autowired
	private ModelMapper modelMapper;

 
	public Page<EventDTO> findAllPaged(Pageable pageRequest) {
		Page<Event> list = repository.findAll(pageRequest);
		// Page already is an stream since Java 8.X, noo need to convert
		return list.map(p -> modelMapper.map(p, EventDTO.class));

	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)	 
	public EventDTO insert(EventDTO eventDTO) {
		Event entity = new Event();
		entity = modelMapper.map(eventDTO, Event.class);
 		entity = repository.save(entity);  
		return modelMapper.map(entity, EventDTO.class);
	}
	
 
 
}
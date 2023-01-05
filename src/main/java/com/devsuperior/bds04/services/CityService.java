package com.devsuperior.bds04.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.repositories.CityRepository;

@Service
@Transactional(readOnly = true)
public class CityService {

	@Autowired
	private CityRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;

	public List<CityDTO> findAll() {
		List<City> list = repository.findAll(Sort.by("name"));
		return list.stream().map(x -> new CityDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)	
	public CityDTO insert(CityDTO cityDTO) {
		City entity = new City();
		entity = modelMapper.map(cityDTO, City.class);
		entity = repository.save(entity); // reposity.save() returns a reference to object saved in DB
		return modelMapper.map(entity, CityDTO.class);
	}

}

package com.devsuperior.bds04.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	public ModelMapperConfig() {
		// TODO Auto-generated constructor stub
	}
	
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

}

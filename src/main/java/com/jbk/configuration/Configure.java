package com.jbk.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Configure {

	@Bean
	public ModelMapper modelMapper() 
	{
		return new ModelMapper();
	}
	
	@Bean
	public RestTemplate restTemp() 
	{
		return new RestTemplate();
	}
}

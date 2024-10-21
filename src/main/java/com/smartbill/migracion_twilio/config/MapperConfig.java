package com.smartbill.migracion_twilio.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean(name = "defaultMapper")
    public ModelMapper defaultMapper() {
        return new ModelMapper();
    }

    @Bean(name = "medicMapper")
    public ModelMapper medicMapper() {
        ModelMapper modelMapper = new ModelMapper();
        // Aquí puedes hacer configuraciones adicionales a `modelMapper` si lo necesitas
        return modelMapper; // Es necesario devolver el objeto
    }

}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.setting;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author alextc6
 */
@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper buildObjectMapper() {
       return new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
}
